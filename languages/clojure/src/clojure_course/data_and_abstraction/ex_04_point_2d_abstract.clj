(ns clojure-course.data-and-abstraction.ex-04-point-2d-abstract)

(defn make-point
  "Construct a 2D point from x and y coordinates."
  [x y]
  (throw (ex-info "not implemented" {})))

(defn point-x
  "Return the x coordinate of a point."
  [p]
  (throw (ex-info "not implemented" {})))

(defn point-y
  "Return the y coordinate of a point."
  [p]
  (throw (ex-info "not implemented" {})))

(defn distance
  "Return the Euclidean distance between two points."
  [p1 p2]
  (throw (ex-info "not implemented" {})))
