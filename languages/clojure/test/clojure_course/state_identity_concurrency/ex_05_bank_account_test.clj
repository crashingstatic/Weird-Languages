(ns clojure-course.state-identity-concurrency.ex-05-bank-account-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-05-bank-account :as sut]))

(deftest basic-operations-test
  (testing "initial balance"
    (is (= 100 (sut/get-balance (sut/make-account 100)))))
  (testing "deposit"
    (let [acct (sut/make-account 100)]
      (sut/deposit acct 50)
      (is (= 150 (sut/get-balance acct)))))
  (testing "successful withdraw"
    (let [acct (sut/make-account 100)]
      (is (true? (sut/withdraw acct 30)))
      (is (= 70 (sut/get-balance acct)))))
  (testing "withdraw exact balance"
    (let [acct (sut/make-account 50)]
      (is (true? (sut/withdraw acct 50)))
      (is (= 0 (sut/get-balance acct)))))
  (testing "reject overdraft"
    (let [acct (sut/make-account 50)]
      (is (false? (sut/withdraw acct 100)))
      (is (= 50 (sut/get-balance acct))))))

(deftest concurrent-test
  (testing "balance never goes negative under contention"
    (let [acct (sut/make-account 1000)
          futures (doall (for [_ (range 200)]
                           (future (sut/withdraw acct 10))))]
      (doseq [f futures] @f)
      (let [balance (sut/get-balance acct)
            successes (count (filter true? (map deref futures)))]
        ;; exactly 100 withdrawals should succeed (1000 / 10)
        (is (= 100 successes))
        (is (= 0 balance))))))
