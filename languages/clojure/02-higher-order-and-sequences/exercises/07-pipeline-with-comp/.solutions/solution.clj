(ns clojure-course.higher-order-seqs.ex-07-pipeline-with-comp)

;; comp builds a predicate from keyword accessors and comparison functions,
;; and ->> threads the data through filter → mapcat → map → reduce.
(defn active-order-total
  [region customers]
  (let [in-region? (comp (partial = region) :region)]
    (->> customers
         (filter in-region?)
         (filter :active)
         (mapcat :orders)
         (map :total)
         (reduce + 0))))
