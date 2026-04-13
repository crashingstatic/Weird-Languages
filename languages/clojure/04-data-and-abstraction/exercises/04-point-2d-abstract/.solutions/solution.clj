(ns clojure-course.data-and-abstraction.ex-04-point-2d-abstract)

;; Representation: a two-element vector [x y].
;; The abstraction barrier means callers never depend on this choice.
(defn make-point [x y] [x y])
(defn point-x [p] (first p))
(defn point-y [p] (second p))

(defn distance [p1 p2]
  (let [dx (- (point-x p2) (point-x p1))
        dy (- (point-y p2) (point-y p1))]
    (Math/sqrt (+ (* dx dx) (* dy dy)))))
