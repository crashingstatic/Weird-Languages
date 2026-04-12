(ns clojure-course.functional-foundations.ex-09-fib-two-ways)

;; Tree-recursive: elegant but exponential. Each call branches into two.
;; This is the definition, not a practical implementation.
(defn fib-tree [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else   (+ (fib-tree (- n 1))
               (fib-tree (- n 2)))))

;; Linear iterative: carries two accumulators forward.
;; The process is O(n) time, O(1) stack — same result, fundamentally
;; different resource usage. This is SICP's key point about processes.
(defn fib-iter [n]
  (loop [a 0 b 1 count n]
    (if (zero? count)
      a
      (recur b (+' a b) (dec count)))))
