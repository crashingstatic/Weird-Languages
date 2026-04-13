(ns clojure-course.polymorphism-and-protocols.ex-05-animal-multimethods)

;; Implement an animal taxonomy using multimethods with `derive`.
;;
;; Hierarchy:
;;   ::animal
;;     ::mammal
;;       ::cat
;;       ::dog
;;     ::bird
;;       ::sparrow
;;       ::parrot
;;
;; Implement:
;; - `sound` multimethod dispatching on :species
;; - `locomotion` multimethod dispatching on :species
;; - `describe` function returning "<name> the <species> says <sound>"

(defn sound [animal]
  (throw (ex-info "not implemented" {})))

(defn locomotion [animal]
  (throw (ex-info "not implemented" {})))

(defn describe [animal]
  (throw (ex-info "not implemented" {})))
