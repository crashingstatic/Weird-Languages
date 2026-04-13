(ns clojure-course.recursion-and-recur.ex-03-accumulator-pattern)

;; TASK: Fill in the accumulator initial value and the recur step.

(def ^:private ___ nil)

(defn sum-to-n [n]
  (loop [i   n
         acc ___]          ;; <-- what should the accumulator start at?
    (if (zero? i)
      acc
      (recur ___ ___))))   ;; <-- new i, new acc
