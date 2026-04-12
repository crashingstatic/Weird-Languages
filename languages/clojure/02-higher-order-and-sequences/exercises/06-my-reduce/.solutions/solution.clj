(ns clojure-course.higher-order-seqs.ex-06-my-reduce)

(defn my-reduce
  ([f init coll]
   (if (empty? coll)
     init
     (recur f (f init (first coll)) (rest coll))))
  ([f coll]
   (if (empty? coll)
     (f)
     (my-reduce f (first coll) (rest coll)))))
