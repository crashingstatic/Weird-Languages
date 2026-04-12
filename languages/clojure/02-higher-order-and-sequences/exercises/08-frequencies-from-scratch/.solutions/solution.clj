(ns clojure-course.higher-order-seqs.ex-08-frequencies-from-scratch)

;; reduce with a map accumulator — the natural pattern for building
;; an index or summary from a flat collection.
(defn my-frequencies
  [coll]
  (reduce (fn [counts elem]
            (update counts elem (fnil inc 0)))
          {}
          coll))
