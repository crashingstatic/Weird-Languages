(ns clojure-course.data-and-abstraction.ex-01-person-map)

;; Maps are the default way to represent entities in Clojure.
;; No class definition needed — just data.
(def person {:name "Alice" :age 30 :email "alice@example.com"})

(defn greet [p]
  (str "Hello, " (:name p) "!"))
