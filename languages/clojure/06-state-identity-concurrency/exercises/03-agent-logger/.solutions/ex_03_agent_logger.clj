(ns clojure-course.state-identity-concurrency.ex-03-agent-logger)

(defn make-logger []
  (agent []))

(defn log-message [logger msg]
  (send logger conj msg))

(defn get-logs [logger]
  @logger)
