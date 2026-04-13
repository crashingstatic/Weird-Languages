(ns clojure-course.polymorphism-and-protocols.ex-04-shape-protocol)

;; Implement a Shape protocol with `area` and `perimeter`.
;; Create Circle, Rectangle, and Triangle records.
;; Also implement `make-composite` that uses reify to create
;; a shape whose area is the sum of its parts.

(defprotocol Shape
  (area [this])
  (perimeter [this]))

(defrecord Circle [radius]
  Shape
  (area [this] (throw (ex-info "not implemented" {})))
  (perimeter [this] (throw (ex-info "not implemented" {}))))

(defrecord Rectangle [width height]
  Shape
  (area [this] (throw (ex-info "not implemented" {})))
  (perimeter [this] (throw (ex-info "not implemented" {}))))

(defrecord Triangle [a b c]
  Shape
  (area [this] (throw (ex-info "not implemented" {})))
  (perimeter [this] (throw (ex-info "not implemented" {}))))

(defn make-composite
  "Returns a Shape whose area is the sum of the given shapes' areas
   and whose perimeter is the sum of the given shapes' perimeters."
  [shapes]
  (throw (ex-info "not implemented" {})))
