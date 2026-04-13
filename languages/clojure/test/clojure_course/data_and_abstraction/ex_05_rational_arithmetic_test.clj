(ns clojure-course.data-and-abstraction.ex-05-rational-arithmetic-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-05-rational-arithmetic :as sut]))

(deftest make-rat-test
  (testing "simple fraction"
    (let [r (sut/make-rat 1 2)]
      (is (= 1 (sut/numer r)))
      (is (= 2 (sut/denom r)))))
  (testing "reduces to lowest terms"
    (let [r (sut/make-rat 4 6)]
      (is (= 2 (sut/numer r)))
      (is (= 3 (sut/denom r)))))
  (testing "negative denominator normalized"
    (let [r (sut/make-rat 3 -4)]
      (is (= -3 (sut/numer r)))
      (is (= 4 (sut/denom r)))))
  (testing "both negative"
    (let [r (sut/make-rat -3 -4)]
      (is (= 3 (sut/numer r)))
      (is (= 4 (sut/denom r)))))
  (testing "zero numerator"
    (let [r (sut/make-rat 0 5)]
      (is (= 0 (sut/numer r))))))

(deftest add-rat-test
  (testing "1/2 + 1/3 = 5/6"
    (let [r (sut/add-rat (sut/make-rat 1 2) (sut/make-rat 1 3))]
      (is (= 5 (sut/numer r)))
      (is (= 6 (sut/denom r)))))
  (testing "1/4 + 1/4 = 1/2"
    (let [r (sut/add-rat (sut/make-rat 1 4) (sut/make-rat 1 4))]
      (is (= 1 (sut/numer r)))
      (is (= 2 (sut/denom r)))))
  (testing "adding zero"
    (let [r (sut/add-rat (sut/make-rat 3 7) (sut/make-rat 0 1))]
      (is (= 3 (sut/numer r)))
      (is (= 7 (sut/denom r))))))

(deftest mul-rat-test
  (testing "2/3 * 3/4 = 1/2"
    (let [r (sut/mul-rat (sut/make-rat 2 3) (sut/make-rat 3 4))]
      (is (= 1 (sut/numer r)))
      (is (= 2 (sut/denom r)))))
  (testing "multiply by zero"
    (let [r (sut/mul-rat (sut/make-rat 5 6) (sut/make-rat 0 1))]
      (is (= 0 (sut/numer r))))))

(deftest equal-rat-test
  (testing "equal"
    (is (true? (sut/equal-rat? (sut/make-rat 1 2) (sut/make-rat 2 4)))))
  (testing "not equal"
    (is (false? (sut/equal-rat? (sut/make-rat 1 2) (sut/make-rat 1 3)))))
  (testing "negative equals"
    (is (true? (sut/equal-rat? (sut/make-rat -1 2) (sut/make-rat 1 -2))))))
