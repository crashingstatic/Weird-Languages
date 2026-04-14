(ns clojure-course.state-identity-concurrency.ex-06-bank-transfer-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-06-bank-transfer :as sut]))

(deftest basic-transfer-test
  (testing "successful transfer"
    (let [a (sut/make-account 100)
          b (sut/make-account 50)]
      (is (true? (sut/transfer a b 30)))
      (is (= 70 (sut/get-balance a)))
      (is (= 80 (sut/get-balance b)))))
  (testing "rejected transfer — insufficient funds"
    (let [a (sut/make-account 10)
          b (sut/make-account 50)]
      (is (false? (sut/transfer a b 20)))
      (is (= 10 (sut/get-balance a)))
      (is (= 50 (sut/get-balance b))))))

(deftest total-balance-test
  (testing "total balance is sum of all accounts"
    (let [accounts (mapv #(sut/make-account %) [100 200 300])]
      (is (= 600 (sut/total-balance accounts))))))

(deftest concurrent-transfer-test
  (testing "total balance is invariant under concurrent transfers"
    (let [a (sut/make-account 500)
          b (sut/make-account 500)
          accounts [a b]
          initial-total (sut/total-balance accounts)
          futures (doall
                    (concat
                      (for [_ (range 100)]
                        (future (sut/transfer a b 1)))
                      (for [_ (range 100)]
                        (future (sut/transfer b a 1)))))]
      (doseq [f futures] @f)
      (is (= initial-total (sut/total-balance accounts))))))

(deftest many-accounts-test
  (testing "round-robin transfers preserve total"
    (let [n 5
          accounts (mapv #(sut/make-account (* % 100)) (range 1 (inc n)))
          initial-total (sut/total-balance accounts)
          futures (doall
                    (for [i (range 50)]
                      (future
                        (let [from (nth accounts (mod i n))
                              to (nth accounts (mod (inc i) n))]
                          (sut/transfer from to 10)))))]
      (doseq [f futures] @f)
      (is (= initial-total (sut/total-balance accounts))))))
