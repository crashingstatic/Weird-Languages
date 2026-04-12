(ns clojure-course.functional-foundations.ex-03-absolute-value)

(defn my-abs [n]
  (if (neg? n)
    (- n)
    n))

(defn sign [n]
  (cond
    (neg? n)  -1
    (zero? n)  0
    :else      1))

(defn clamp [n lo hi]
  (cond
    (< n lo) lo
    (> n hi) hi
    :else    n))
