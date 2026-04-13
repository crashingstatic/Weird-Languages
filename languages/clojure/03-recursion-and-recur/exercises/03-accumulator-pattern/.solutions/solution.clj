(ns clojure-course.recursion-and-recur.ex-03-accumulator-pattern)

;; Linear-iterative sum: the accumulator carries the partial sum
;; forward. Each step adds i to acc and decrements i.
;; Compare with the linear-recursive version:
;;   (if (zero? n) 0 (+ n (sum-to-n (dec n))))
;; which builds up n pending additions on the stack.
(defn sum-to-n [n]
  (loop [i   n
         acc 0]
    (if (zero? i)
      acc
      (recur (dec i) (+ acc i)))))
