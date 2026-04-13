(ns clojure-course.polymorphism-and-protocols.ex-01-multimethod-shape-area)

;; TASK: A multimethod `area` dispatches on :shape.
;; Two methods are provided (circle, rectangle).
;; Fill in the ___ to add the :triangle method.
;; Triangle area = (* 0.5 base height), where base = :base, height = :height.

(def ^:private ___ nil)

(defmulti area :shape)

(defmethod area :circle [{:keys [radius]}]
  (* Math/PI radius radius))

(defmethod area :rectangle [{:keys [width height]}]
  (* width height))

;; TODO: fill in the dispatch value and body
(defmethod area ___ [___]
  ___)
