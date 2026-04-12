(ns clojure-course.functional-foundations.ex-05-newton-sqrt)

(defn good-enough? [guess n]
  (< (Math/abs (- (* guess guess) n)) 0.00001))

(defn improve [guess n]
  (/ (+ guess (/ n guess)) 2.0))

;; The recursive structure mirrors SICP 1.1.7: iterate until convergence.
(defn sqrt [n]
  (loop [guess 1.0]
    (if (good-enough? guess n)
      guess
      (recur (improve guess n)))))
