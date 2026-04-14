(ns clojure-course.state-identity-concurrency.ex-04-thread-safe-counter)

(defn make-counter []
  (atom 0))

(defn increment [counter]
  (swap! counter inc))

(defn increment-by [counter n]
  (swap! counter + n))

(defn get-count [counter]
  @counter)

(defn reset-counter [counter]
  (reset! counter 0))
