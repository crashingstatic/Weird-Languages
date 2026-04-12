(ns clojure-course.functional-foundations.ex-02-let-binding)

;; Replace every `___` with the correct expression.

(def ^:private ___ nil) ;; placeholder — do not modify this line

;; TASK: Compute the area of a circle with radius `r`.
;; Use `let` to bind `pi` to 3.14159 inside the function.
(defn circle-area [r]
  (let [pi ___]
    ___))

;; TASK: Compute the volume of a cylinder with radius `r` and height `h`.
;; Use `let` to bind `pi` and `base-area` (the area of the circular base).
;; Then return base-area times h.
(defn cylinder-volume [r h]
  (let [pi   ___
        base-area ___]
    ___))
