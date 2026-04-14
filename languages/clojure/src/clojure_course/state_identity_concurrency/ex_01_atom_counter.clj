(ns clojure-course.state-identity-concurrency.ex-01-atom-counter)

;; TASK: Fill in the ___ to complete the atom-based counter.
;; - `make-counter` creates an atom holding 0
;; - `increment` uses swap! to add 1 to the counter
;; - `get-count` dereferences the counter

(def ^:private ___ nil)

(defn make-counter []
  (atom ___))

(defn increment [counter]
  ___)

(defn get-count [counter]
  ___)
