(ns clojure-course.recursion-and-recur.ex-02-tail-recur-sum)

;; Classic tail-recursive sum: peel off the head, add it to the
;; accumulator, recur on the tail. Constant stack.
(defn running-sum [xs]
  (loop [remaining xs
         acc       0]
    (if (empty? remaining)
      acc
      (recur (rest remaining) (+ acc (first remaining))))))
