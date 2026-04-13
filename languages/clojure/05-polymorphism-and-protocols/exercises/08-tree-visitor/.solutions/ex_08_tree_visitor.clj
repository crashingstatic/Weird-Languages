(ns clojure-course.polymorphism-and-protocols.ex-08-tree-visitor)

(defprotocol Expr
  (evaluate [this])
  (visit [this]))

(defrecord Num [value]
  Expr
  (evaluate [this] value)
  (visit [this] {:result value :ops []}))

(defrecord Add [left right]
  Expr
  (evaluate [this] (+ (evaluate left) (evaluate right)))
  (visit [this]
    (let [l (visit left)
          r (visit right)]
      {:result (+ (:result l) (:result r))
       :ops (into [] (concat (:ops l) (:ops r) ["add"]))})))

(defrecord Mul [left right]
  Expr
  (evaluate [this] (* (evaluate left) (evaluate right)))
  (visit [this]
    (let [l (visit left)
          r (visit right)]
      {:result (* (:result l) (:result r))
       :ops (into [] (concat (:ops l) (:ops r) ["mul"]))})))
