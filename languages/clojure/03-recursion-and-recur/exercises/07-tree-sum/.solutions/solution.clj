(ns clojure-course.recursion-and-recur.ex-07-tree-sum)

;; Tree recursion: at each node, decide whether it's a leaf (add it)
;; or a branch (sum its children). This can't be expressed with recur
;; because there are two recursive calls per branch node (one per child),
;; and recur only handles a single tail call.
;; Using reduce + recursion into children is the idiomatic approach.
(defn tree-sum [tree]
  (cond
    (number? tree) tree
    (sequential? tree) (reduce + 0 (map tree-sum tree))
    :else 0))
