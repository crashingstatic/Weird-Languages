(ns clojure-course.functional-foundations.ex-06-power-recursive)

;; Straightforward linear recursion — O(n) time and O(n) stack.
;; Exercise 08 will revisit this with the O(log n) approach.
(defn power [b n]
  (if (zero? n)
    1
    (* b (power b (dec n)))))
