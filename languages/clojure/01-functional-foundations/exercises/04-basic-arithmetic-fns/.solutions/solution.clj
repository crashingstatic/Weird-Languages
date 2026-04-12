(ns clojure-course.functional-foundations.ex-04-basic-arithmetic-fns)

(defn square [n]
  (* n n))

(defn cube [n]
  (* n n n))

(defn average [a b]
  (/ (+ a b) 2.0))

;; Composing square inside distance-2d — functions as building blocks.
(defn distance-2d [x1 y1 x2 y2]
  (Math/sqrt (+ (square (- x2 x1))
                (square (- y2 y1)))))
