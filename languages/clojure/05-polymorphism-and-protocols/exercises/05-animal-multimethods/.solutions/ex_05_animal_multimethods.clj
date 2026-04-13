(ns clojure-course.polymorphism-and-protocols.ex-05-animal-multimethods)

(derive ::cat ::mammal)
(derive ::dog ::mammal)
(derive ::sparrow ::bird)
(derive ::parrot ::bird)
(derive ::mammal ::animal)
(derive ::bird ::animal)

(defmulti sound :species)
(defmethod sound ::cat [_] "meow")
(defmethod sound ::dog [_] "woof")
(defmethod sound ::sparrow [_] "chirp")
(defmethod sound ::parrot [_] "squawk")

(defmulti locomotion :species)
(defmethod locomotion ::mammal [_] "walk")
(defmethod locomotion ::bird [_] "fly")

(defn describe [animal]
  (str (:name animal) " the " (name (:species animal)) " says " (sound animal)))
