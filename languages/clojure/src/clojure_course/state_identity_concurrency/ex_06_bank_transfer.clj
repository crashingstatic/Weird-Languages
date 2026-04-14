(ns clojure-course.state-identity-concurrency.ex-06-bank-transfer)

;; Implement a bank transfer system using refs and STM.
;; Transfers between accounts must be atomic — money never
;; disappears or appears mid-transfer.

(defn make-account [initial-balance]
  (throw (ex-info "not implemented" {})))

(defn get-balance [account]
  (throw (ex-info "not implemented" {})))

(defn transfer [from to amount]
  (throw (ex-info "not implemented" {})))

(defn total-balance [accounts]
  (throw (ex-info "not implemented" {})))
