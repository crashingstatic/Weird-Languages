(ns clojure-course.state-identity-concurrency.ex-02-ref-balance)

;; TASK: Fill in the ___ to complete the ref-based balance.
;; - `make-account` creates a ref holding the initial balance
;; - `deposit` uses dosync + alter to add amount
;; - `get-balance` dereferences the ref

(def ^:private ___ nil)

(defn make-account [initial-balance]
  (ref ___))

(defn deposit [account amount]
  ___)

(defn get-balance [account]
  ___)
