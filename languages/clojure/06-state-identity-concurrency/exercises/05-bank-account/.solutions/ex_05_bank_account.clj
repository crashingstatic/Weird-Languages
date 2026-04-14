(ns clojure-course.state-identity-concurrency.ex-05-bank-account)

(defn make-account [initial-balance]
  (atom initial-balance))

(defn get-balance [account]
  @account)

(defn deposit [account amount]
  (swap! account + amount))

(defn withdraw [account amount]
  (loop []
    (let [balance @account]
      (if (>= balance amount)
        (if (compare-and-set! account balance (- balance amount))
          true
          (recur))
        false))))
