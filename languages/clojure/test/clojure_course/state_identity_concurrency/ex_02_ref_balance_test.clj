(ns clojure-course.state-identity-concurrency.ex-02-ref-balance-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-02-ref-balance :as sut]))

(deftest make-account-test
  (testing "account starts with initial balance"
    (is (= 100 (sut/get-balance (sut/make-account 100))))
    (is (= 0 (sut/get-balance (sut/make-account 0))))))

(deftest deposit-test
  (testing "deposit adds to balance"
    (let [acct (sut/make-account 100)]
      (sut/deposit acct 50)
      (is (= 150 (sut/get-balance acct)))))
  (testing "multiple deposits"
    (let [acct (sut/make-account 0)]
      (sut/deposit acct 10)
      (sut/deposit acct 20)
      (sut/deposit acct 30)
      (is (= 60 (sut/get-balance acct))))))

(deftest independent-accounts-test
  (testing "accounts are independent"
    (let [a1 (sut/make-account 100)
          a2 (sut/make-account 200)]
      (sut/deposit a1 50)
      (is (= 150 (sut/get-balance a1)))
      (is (= 200 (sut/get-balance a2))))))
