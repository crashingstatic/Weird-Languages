(ns clojure-course.higher-order-seqs.ex-03-reduce-sum)

(def ^:private ___ nil) ;; placeholder — do not modify this line

;; TASK: Use `reduce` with `+` and an initial value to sum a collection.
(defn sum-all
  [xs]
  (reduce + ___ xs))
