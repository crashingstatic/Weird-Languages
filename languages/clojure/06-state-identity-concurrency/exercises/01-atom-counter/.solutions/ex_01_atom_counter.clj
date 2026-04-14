(ns clojure-course.state-identity-concurrency.ex-01-atom-counter)

(defn make-counter []
  (atom 0))

(defn increment [counter]
  (swap! counter inc))

(defn get-count [counter]
  @counter)
