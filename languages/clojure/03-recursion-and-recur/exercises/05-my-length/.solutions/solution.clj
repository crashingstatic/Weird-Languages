(ns clojure-course.recursion-and-recur.ex-05-my-length)

;; Walk the sequence, incrementing a counter each step. Constant stack.
(defn my-length [xs]
  (loop [remaining xs
         n         0]
    (if (empty? remaining)
      n
      (recur (rest remaining) (inc n)))))
