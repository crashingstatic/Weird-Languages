(ns clojure-course.polymorphism-and-protocols.ex-02-defprotocol-greet)

(defprotocol Greetable
  (greet [this]))

(defrecord Person [name]
  Greetable
  (greet [this] (str "Hello, " name "!")))

(defrecord Robot [id]
  Greetable
  (greet [this] (str "BEEP BOOP, I am " id)))
