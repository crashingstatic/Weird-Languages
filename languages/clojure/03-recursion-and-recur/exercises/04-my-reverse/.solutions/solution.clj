(ns clojure-course.recursion-and-recur.ex-04-my-reverse)

;; conj onto a vector accumulator naturally reverses: we peel elements
;; from the front of the input and push them onto the back of the output.
;; Actually, conj onto a list prepends — which also reverses. Either works.
;; Using a list here to mirror how cons-based reversal works.
(defn my-reverse [xs]
  (loop [remaining xs
         acc       ()]
    (if (empty? remaining)
      acc
      (recur (rest remaining) (conj acc (first remaining))))))
