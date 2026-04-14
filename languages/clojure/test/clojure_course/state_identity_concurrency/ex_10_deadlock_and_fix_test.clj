(ns clojure-course.state-identity-concurrency.ex-10-deadlock-and-fix-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-10-deadlock-and-fix :as sut]))

(deftest basic-transfer-test
  (testing "successful transfer"
    (let [a (sut/make-account 100)
          b (sut/make-account 50)]
      (is (true? (sut/transfer a b 30)))
      (is (= 70 (sut/get-balance a)))
      (is (= 80 (sut/get-balance b)))))
  (testing "rejected transfer"
    (let [a (sut/make-account 10)
          b (sut/make-account 50)]
      (is (nil? (sut/transfer a b 20)))
      (is (= 10 (sut/get-balance a)))
      (is (= 50 (sut/get-balance b))))))

(deftest no-deadlock-test
  (testing "opposing transfers complete without deadlock"
    (let [a (sut/make-account 1000)
          b (sut/make-account 1000)
          initial-total (+ (sut/get-balance a) (sut/get-balance b))
          futures (doall
                    (concat
                      (for [_ (range 100)]
                        (future (sut/transfer a b 1)))
                      (for [_ (range 100)]
                        (future (sut/transfer b a 1)))))]
      ;; If this deadlocks, the test will time out
      (doseq [f futures] (deref f 5000 :timeout))
      (is (= initial-total
             (+ (sut/get-balance a) (sut/get-balance b)))))))

(deftest stress-test
  (testing "many concurrent opposing transfers preserve total"
    (let [accounts (mapv sut/make-account [500 500 500])
          initial-total (reduce + (map sut/get-balance accounts))
          futures (doall
                    (for [_ (range 200)]
                      (future
                        (let [i (rand-int 3)
                              j (mod (inc i) 3)]
                          (sut/transfer (nth accounts i) (nth accounts j) 1)))))]
      (doseq [f futures] (deref f 5000 :timeout))
      (is (= initial-total (reduce + (map sut/get-balance accounts)))))))
