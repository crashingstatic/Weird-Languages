(ns clojure-course.recursion-and-recur.ex-01-convert-to-recur)

;; Here's the naive recursive version — it works but blows the stack on large n.
;; (defn factorial [n]
;;   (if (<= n 1)
;;     1
;;     (* n (factorial (dec n)))))

;; TASK: Rewrite factorial using loop/recur and an accumulator.
;; Fill in the three blanks (___) below.

(def ^:private ___ nil)

(defn factorial [n]
  (loop [i   n
         acc ___]          ;; <-- initial accumulator value
    (if (<= i 1)
      acc
      (recur ___ ___))))   ;; <-- new i, new acc
