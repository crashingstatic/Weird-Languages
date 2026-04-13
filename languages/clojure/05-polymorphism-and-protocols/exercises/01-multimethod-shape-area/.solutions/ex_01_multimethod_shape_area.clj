(ns clojure-course.polymorphism-and-protocols.ex-01-multimethod-shape-area)

(defmulti area :shape)

(defmethod area :circle [{:keys [radius]}]
  (* Math/PI radius radius))

(defmethod area :rectangle [{:keys [width height]}]
  (* width height))

(defmethod area :triangle [{:keys [base height]}]
  (* 0.5 base height))
