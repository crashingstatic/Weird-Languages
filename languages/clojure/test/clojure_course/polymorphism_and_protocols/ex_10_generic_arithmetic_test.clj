(ns clojure-course.polymorphism-and-protocols.ex-10-generic-arithmetic-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-10-generic-arithmetic :as sut]))

(deftest constructors-test
  (testing "constructors and accessors"
    (is (= :integer (sut/type-of (sut/make-integer 5))))
    (is (= 5 (sut/value-of (sut/make-integer 5))))
    (is (= :rational (sut/type-of (sut/make-rational 1 2))))
    (is (= [1 2] (sut/value-of (sut/make-rational 1 2))))
    (is (= :real (sut/type-of (sut/make-real 3.14))))))

(deftest rational-reduces-test
  (testing "rationals are reduced to lowest terms"
    (is (= [1 2] (sut/value-of (sut/make-rational 2 4))))
    (is (= [1 3] (sut/value-of (sut/make-rational 3 9))))))

(deftest add-same-type-test
  (testing "integer + integer"
    (let [result (sut/add (sut/make-integer 3) (sut/make-integer 5))]
      (is (= :integer (sut/type-of result)))
      (is (= 8 (sut/value-of result)))))
  (testing "rational + rational: 1/2 + 1/3 = 5/6"
    (let [result (sut/add (sut/make-rational 1 2) (sut/make-rational 1 3))]
      (is (= :rational (sut/type-of result)))
      (is (= [5 6] (sut/value-of result)))))
  (testing "real + real"
    (let [result (sut/add (sut/make-real 1.5) (sut/make-real 2.5))]
      (is (= :real (sut/type-of result)))
      (is (= 4.0 (sut/value-of result))))))

(deftest add-coercion-test
  (testing "integer + rational coerces to rational: 2 + 1/3 = 7/3"
    (let [result (sut/add (sut/make-integer 2) (sut/make-rational 1 3))]
      (is (= :rational (sut/type-of result)))
      (is (= [7 3] (sut/value-of result)))))
  (testing "integer + real coerces to real"
    (let [result (sut/add (sut/make-integer 2) (sut/make-real 0.5))]
      (is (= :real (sut/type-of result)))
      (is (= 2.5 (sut/value-of result))))))

(deftest mul-test
  (testing "integer * integer"
    (let [result (sut/mul (sut/make-integer 3) (sut/make-integer 4))]
      (is (= :integer (sut/type-of result)))
      (is (= 12 (sut/value-of result)))))
  (testing "rational * rational: 2/3 * 3/4 = 1/2"
    (let [result (sut/mul (sut/make-rational 2 3) (sut/make-rational 3 4))]
      (is (= :rational (sut/type-of result)))
      (is (= [1 2] (sut/value-of result)))))
  (testing "integer * rational coerces: 3 * 1/2 = 3/2"
    (let [result (sut/mul (sut/make-integer 3) (sut/make-rational 1 2))]
      (is (= :rational (sut/type-of result)))
      (is (= [3 2] (sut/value-of result))))))

(deftest equ-test
  (testing "same-type equality"
    (is (true? (sut/equ? (sut/make-integer 5) (sut/make-integer 5))))
    (is (false? (sut/equ? (sut/make-integer 5) (sut/make-integer 6))))
    (is (true? (sut/equ? (sut/make-rational 1 2) (sut/make-rational 2 4)))))
  (testing "cross-type equality via coercion"
    (is (true? (sut/equ? (sut/make-integer 2) (sut/make-rational 2 1))))))

(deftest zero-test
  (testing "zero? for each type"
    (is (true? (sut/zero? (sut/make-integer 0))))
    (is (false? (sut/zero? (sut/make-integer 1))))
    (is (true? (sut/zero? (sut/make-rational 0 5))))
    (is (false? (sut/zero? (sut/make-rational 1 5))))
    (is (true? (sut/zero? (sut/make-real 0.0))))
    (is (false? (sut/zero? (sut/make-real 0.1))))))
