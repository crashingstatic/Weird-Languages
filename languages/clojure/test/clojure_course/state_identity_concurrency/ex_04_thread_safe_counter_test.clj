(ns clojure-course.state-identity-concurrency.ex-04-thread-safe-counter-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-04-thread-safe-counter :as sut]))

(deftest basic-operations-test
  (testing "make, increment, get"
    (let [c (sut/make-counter)]
      (is (= 0 (sut/get-count c)))
      (sut/increment c)
      (is (= 1 (sut/get-count c)))))
  (testing "increment-by"
    (let [c (sut/make-counter)]
      (sut/increment-by c 10)
      (is (= 10 (sut/get-count c)))))
  (testing "reset"
    (let [c (sut/make-counter)]
      (sut/increment-by c 50)
      (sut/reset-counter c)
      (is (= 0 (sut/get-count c))))))

(deftest concurrent-increment-test
  (testing "100 futures x 1000 increments = 100000"
    (let [c (sut/make-counter)
          futures (doall (for [_ (range 100)]
                           (future (dotimes [_ 1000] (sut/increment c)))))]
      (doseq [f futures] @f)
      (is (= 100000 (sut/get-count c))))))

(deftest concurrent-increment-by-test
  (testing "concurrent increment-by is correct"
    (let [c (sut/make-counter)
          futures (doall (for [_ (range 50)]
                           (future (dotimes [_ 100] (sut/increment-by c 3)))))]
      (doseq [f futures] @f)
      (is (= 15000 (sut/get-count c))))))
