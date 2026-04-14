(ns clojure-course.state-identity-concurrency.ex-09-agent-counter-ring)

;; Build a ring of N agents that pass a token around.
;; Each agent increments a shared counter when it receives the token,
;; then passes the token to the next agent in the ring.

(defn make-ring [n counter]
  (throw (ex-info "not implemented" {})))

(defn start-token [ring hops]
  (throw (ex-info "not implemented" {})))
