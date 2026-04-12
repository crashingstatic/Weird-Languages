(ns clojure-course.higher-order-seqs.ex-03-reduce-sum)

(defn sum-all
  [xs]
  (reduce + 0 xs))
