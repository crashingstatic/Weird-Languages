(ns clojure-course.recursion-and-recur.ex-01-convert-to-recur)

;; The accumulator carries the running product. Each step multiplies
;; the accumulator by i and decrements i. This is the canonical
;; linear-iterative process — constant stack, O(n) time.
(defn factorial [n]
  (loop [i   n
         acc 1]
    (if (<= i 1)
      acc
      (recur (dec i) (* acc i)))))
