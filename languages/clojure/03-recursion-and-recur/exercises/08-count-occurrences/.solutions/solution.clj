(ns clojure-course.recursion-and-recur.ex-08-count-occurrences)

;; Tree recursion again: each branch fans out into its children.
;; At a leaf, check for equality. reduce collects counts from children.
(defn count-occurrences [target coll]
  (cond
    (sequential? coll)
    (reduce + 0 (map #(count-occurrences target %) coll))

    (= target coll) 1
    :else            0))
