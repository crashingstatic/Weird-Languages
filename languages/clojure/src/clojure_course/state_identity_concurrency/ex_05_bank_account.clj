(ns clojure-course.state-identity-concurrency.ex-05-bank-account)

;; Implement a single bank account using an atom.
;; - withdraw must fail atomically if balance would go negative
;; - deposit always succeeds

(defn make-account [initial-balance]
  (throw (ex-info "not implemented" {})))

(defn get-balance [account]
  (throw (ex-info "not implemented" {})))

(defn deposit [account amount]
  (throw (ex-info "not implemented" {})))

(defn withdraw [account amount]
  (throw (ex-info "not implemented" {})))
