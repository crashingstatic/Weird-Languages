(ns clojure-course.polymorphism-and-protocols.ex-03-defrecord-person)

;; TASK: A Describable protocol is defined. Fill in the blanks to:
;; 1. Give the Employee record the correct fields (name, role, salary)
;; 2. Implement describe for Employee: "<name> is a <role> earning $<salary>"
;; 3. Implement describe for Product: "<name> costs $<price>"

(defprotocol Describable
  (describe [this]))

;; TODO: replace the placeholder fields and implement describe
(defrecord Employee [a b c]
  Describable
  (describe [this] nil))

(defrecord Product [name price]
  Describable
  (describe [this] nil))
