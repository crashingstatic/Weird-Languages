(ns clojure-course.state-identity-concurrency.ex-08-thread-safe-queue)

;; Implement a thread-safe FIFO queue using an atom.
;; Concurrent producers and consumers must not corrupt it.

(defn make-queue []
  (throw (ex-info "not implemented" {})))

(defn enqueue [q item]
  (throw (ex-info "not implemented" {})))

(defn dequeue [q]
  (throw (ex-info "not implemented" {})))

(defn queue-size [q]
  (throw (ex-info "not implemented" {})))

(defn queue-empty? [q]
  (throw (ex-info "not implemented" {})))
