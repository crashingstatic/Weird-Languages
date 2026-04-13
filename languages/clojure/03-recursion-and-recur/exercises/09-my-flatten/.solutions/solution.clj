(ns clojure-course.recursion-and-recur.ex-09-my-flatten)

;; mapcat over children: if a child is sequential, recursively flatten it;
;; otherwise wrap it in a one-element list. mapcat concatenates the results.
(defn my-flatten [coll]
  (mapcat (fn [x]
            (if (sequential? x)
              (my-flatten x)
              (list x)))
          coll))
