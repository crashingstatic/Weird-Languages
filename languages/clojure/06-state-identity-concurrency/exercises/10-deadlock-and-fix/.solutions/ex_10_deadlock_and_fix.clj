(ns clojure-course.state-identity-concurrency.ex-10-deadlock-and-fix)

;; The fix: use refs + dosync instead of locking.
;; Clojure's STM prevents deadlock by design — transactions
;; retry rather than block on locks.

(defn make-account [initial-balance]
  (ref initial-balance))

(defn get-balance [account]
  @account)

(defn transfer [from to amount]
  (dosync
    (when (>= @from amount)
      (alter from - amount)
      (alter to + amount)
      true)))
