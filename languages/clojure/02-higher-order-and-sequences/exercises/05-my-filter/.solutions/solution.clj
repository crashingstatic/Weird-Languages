(ns clojure-course.higher-order-seqs.ex-05-my-filter)

(defn my-filter
  [pred coll]
  (if (empty? coll)
    ()
    (if (pred (first coll))
      (cons (first coll) (my-filter pred (rest coll)))
      (my-filter pred (rest coll)))))
