(ns clojure-course.polymorphism-and-protocols.ex-04-shape-protocol)

(defprotocol Shape
  (area [this])
  (perimeter [this]))

(defrecord Circle [radius]
  Shape
  (area [this] (* Math/PI radius radius))
  (perimeter [this] (* 2 Math/PI radius)))

(defrecord Rectangle [width height]
  Shape
  (area [this] (* width height))
  (perimeter [this] (* 2 (+ width height))))

(defrecord Triangle [a b c]
  Shape
  (area [this]
    (let [s (/ (+ a b c) 2.0)]
      (Math/sqrt (* s (- s a) (- s b) (- s c)))))
  (perimeter [this] (+ a b c)))

(defn make-composite
  [shapes]
  (reify Shape
    (area [this] (reduce + 0 (map area shapes)))
    (perimeter [this] (reduce + 0 (map perimeter shapes)))))
