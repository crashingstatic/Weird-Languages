(ns clojure-course.higher-order-seqs.ex-04-my-map)

(defn my-map
  [f coll]
  (if (empty? coll)
    ()
    (cons (f (first coll))
          (my-map f (rest coll)))))
