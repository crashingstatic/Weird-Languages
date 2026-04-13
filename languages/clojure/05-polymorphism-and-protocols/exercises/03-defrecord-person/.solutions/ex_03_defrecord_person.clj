(ns clojure-course.polymorphism-and-protocols.ex-03-defrecord-person)

(defprotocol Describable
  (describe [this]))

(defrecord Employee [name role salary]
  Describable
  (describe [this] (str name " is a " role " earning $" salary)))

(defrecord Product [name price]
  Describable
  (describe [this] (str name " costs $" price)))
