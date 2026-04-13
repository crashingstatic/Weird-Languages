(ns clojure-course.polymorphism-and-protocols.ex-07-expr-eval-multi)

(defmulti eval-expr :op)

(defmethod eval-expr :number [{:keys [value]}]
  value)

(defmethod eval-expr :sum [{:keys [left right]}]
  (+ (eval-expr left) (eval-expr right)))

(defmethod eval-expr :product [{:keys [left right]}]
  (* (eval-expr left) (eval-expr right)))

(defmulti stringify :op)

(defmethod stringify :number [{:keys [value]}]
  (str value))

(defmethod stringify :sum [{:keys [left right]}]
  (str "(" (stringify left) " + " (stringify right) ")"))

(defmethod stringify :product [{:keys [left right]}]
  (str "(" (stringify left) " * " (stringify right) ")"))
