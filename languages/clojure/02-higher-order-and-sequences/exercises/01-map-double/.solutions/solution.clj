(ns clojure-course.higher-order-seqs.ex-01-map-double)

(defn double-all
  [xs]
  (map #(* % 2) xs))
