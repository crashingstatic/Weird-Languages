(ns clojure-course.state-identity-concurrency.ex-04-thread-safe-counter)

;; Implement a thread-safe counter using an atom.
;; The test will spawn 100 futures each incrementing 1000 times.
;; The final value must be exactly 100000.

(defn make-counter []
  (throw (ex-info "not implemented" {})))

(defn increment [counter]
  (throw (ex-info "not implemented" {})))

(defn increment-by [counter n]
  (throw (ex-info "not implemented" {})))

(defn get-count [counter]
  (throw (ex-info "not implemented" {})))

(defn reset-counter [counter]
  (throw (ex-info "not implemented" {})))
