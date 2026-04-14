(ns clojure-course.state-identity-concurrency.ex-03-agent-logger)

;; TASK: Fill in the ___ to complete the agent-based logger.
;; - `make-logger` creates an agent holding an empty vector
;; - `log-message` uses send to conj a message onto the vector
;; - `get-logs` dereferences the agent

(def ^:private ___ nil)

(defn make-logger []
  (agent ___))

(defn log-message [logger msg]
  ___)

(defn get-logs [logger]
  ___)
