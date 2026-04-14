(ns clojure-course.state-identity-concurrency.ex-06-bank-transfer)

(defn make-account [initial-balance]
  (ref initial-balance))

(defn get-balance [account]
  @account)

(defn transfer [from to amount]
  (dosync
    (if (>= @from amount)
      (do
        (alter from - amount)
        (alter to + amount)
        true)
      false)))

(defn total-balance [accounts]
  (dosync (reduce + 0 (map deref accounts))))
