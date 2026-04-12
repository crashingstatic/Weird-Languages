(ns clojure-course.higher-order-seqs.ex-02-filter-evens)

(defn evens-only
  [xs]
  (filter even? xs))
