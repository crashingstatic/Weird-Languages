(ns clojure-course.data-and-abstraction.ex-08-binary-tree-of-data)

;; A BST node is just a map. Insertion and lookup are recursive —
;; each call returns a new (or unchanged) subtree. The persistent
;; map sharing means this is efficient.
(defn bst-insert [tree val]
  (cond
    (nil? tree)
    {:value val :left nil :right nil}

    (< val (:value tree))
    (assoc tree :left (bst-insert (:left tree) val))

    (> val (:value tree))
    (assoc tree :right (bst-insert (:right tree) val))

    ;; duplicate — return unchanged
    :else tree))

(defn bst-contains? [tree val]
  (cond
    (nil? tree)            false
    (= val (:value tree))  true
    (< val (:value tree))  (bst-contains? (:left tree) val)
    :else                  (bst-contains? (:right tree) val)))

(defn bst-in-order [tree]
  (if (nil? tree)
    ()
    (concat (bst-in-order (:left tree))
            [(:value tree)]
            (bst-in-order (:right tree)))))
