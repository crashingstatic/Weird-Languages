(ns clojure-course.state-identity-concurrency.ex-02-ref-balance)

(defn make-account [initial-balance]
  (ref initial-balance))

(defn deposit [account amount]
  (dosync (alter account + amount)))

(defn get-balance [account]
  @account)
