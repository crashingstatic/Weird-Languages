(ns clojure-course.state-identity-concurrency.ex-01-atom-counter-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-01-atom-counter :as sut]))

(deftest make-counter-test
  (testing "new counter starts at 0"
    (is (= 0 (sut/get-count (sut/make-counter))))))

(deftest increment-test
  (testing "increment adds 1"
    (let [c (sut/make-counter)]
      (sut/increment c)
      (is (= 1 (sut/get-count c)))))
  (testing "multiple increments"
    (let [c (sut/make-counter)]
      (dotimes [_ 5] (sut/increment c))
      (is (= 5 (sut/get-count c))))))

(deftest independent-counters-test
  (testing "counters are independent"
    (let [c1 (sut/make-counter)
          c2 (sut/make-counter)]
      (sut/increment c1)
      (sut/increment c1)
      (sut/increment c2)
      (is (= 2 (sut/get-count c1)))
      (is (= 1 (sut/get-count c2))))))
