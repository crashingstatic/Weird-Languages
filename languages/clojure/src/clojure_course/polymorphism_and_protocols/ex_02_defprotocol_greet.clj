(ns clojure-course.polymorphism-and-protocols.ex-02-defprotocol-greet)

;; TASK: Fill in the blanks to complete the Greetable protocol
;; and the two record implementations.
;;
;; The protocol has one method: (greet [this])
;; - For a Person, greet returns "Hello, <name>!"
;; - For a Robot, greet returns "BEEP BOOP, I am <id>"

(defprotocol Greetable
  ;; TODO: declare the greet method here
  (greet [this]))

;; TODO: implement greet for Person
(defrecord Person [name]
  Greetable
  (greet [this] nil))

;; TODO: implement greet for Robot
(defrecord Robot [id]
  Greetable
  (greet [this] nil))
