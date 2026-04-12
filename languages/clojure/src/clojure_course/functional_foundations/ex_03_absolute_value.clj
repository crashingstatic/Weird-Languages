(ns clojure-course.functional-foundations.ex-03-absolute-value)

;; Replace every `___` with the correct expression.

(def ^:private ___ nil) ;; placeholder — do not modify this line

;; TASK: Return the absolute value of `n`.
;; Fill in the condition and the then-branch.
(defn my-abs [n]
  (if (___  n)
    ___
    n))

;; TASK: Return -1 if n is negative, 0 if zero, 1 if positive.
;; Fill in each result expression.
(defn sign [n]
  (cond
    (neg? n)  ___
    (zero? n) ___
    :else     ___))

;; TASK: Clamp `n` to the range [lo, hi].
;; If n < lo, return lo. If n > hi, return hi. Otherwise return n.
(defn clamp [n lo hi]
  (cond
    (< n lo) ___
    (> n hi) ___
    :else    ___))
