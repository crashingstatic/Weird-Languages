(ns clojure-course.data-and-abstraction.ex-09-interval-arithmetic-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-09-interval-arithmetic :as sut]))

(defn- close? [a b]
  (< (Math/abs (- (double a) (double b))) 1e-6))

(deftest constructor-selector-test
  (testing "round-trip"
    (let [i (sut/make-interval 2 5)]
      (is (= 2 (sut/lower-bound i)))
      (is (= 5 (sut/upper-bound i)))))
  (testing "negative bounds"
    (let [i (sut/make-interval -3 -1)]
      (is (= -3 (sut/lower-bound i)))
      (is (= -1 (sut/upper-bound i))))))

(deftest add-interval-test
  (testing "[2,5] + [1,3] = [3,8]"
    (let [r (sut/add-interval (sut/make-interval 2 5)
                              (sut/make-interval 1 3))]
      (is (= 3 (sut/lower-bound r)))
      (is (= 8 (sut/upper-bound r)))))
  (testing "adding negative intervals"
    (let [r (sut/add-interval (sut/make-interval -5 -2)
                              (sut/make-interval -3 -1))]
      (is (= -8 (sut/lower-bound r)))
      (is (= -3 (sut/upper-bound r))))))

(deftest mul-interval-test
  (testing "positive * positive"
    (let [r (sut/mul-interval (sut/make-interval 2 3)
                              (sut/make-interval 4 5))]
      (is (= 8 (sut/lower-bound r)))
      (is (= 15 (sut/upper-bound r)))))
  (testing "positive * negative"
    (let [r (sut/mul-interval (sut/make-interval 2 3)
                              (sut/make-interval -5 -4))]
      (is (= -15 (sut/lower-bound r)))
      (is (= -8 (sut/upper-bound r)))))
  (testing "spanning zero * positive"
    (let [r (sut/mul-interval (sut/make-interval -2 3)
                              (sut/make-interval 4 5))]
      (is (= -10 (sut/lower-bound r)))
      (is (= 15 (sut/upper-bound r)))))
  (testing "both spanning zero"
    (let [r (sut/mul-interval (sut/make-interval -2 3)
                              (sut/make-interval -4 5))]
      (is (= -12 (sut/lower-bound r)))
      (is (= 15 (sut/upper-bound r))))))

(deftest width-test
  (testing "[2,6] has width 2"
    (is (close? 2.0 (sut/width (sut/make-interval 2 6)))))
  (testing "[0,0] has width 0"
    (is (close? 0.0 (sut/width (sut/make-interval 0 0)))))
  (testing "[-3,3] has width 3"
    (is (close? 3.0 (sut/width (sut/make-interval -3 3))))))

(deftest contains-zero-test
  (testing "[2,5] does not contain zero"
    (is (false? (sut/contains-zero? (sut/make-interval 2 5)))))
  (testing "[-3,3] contains zero"
    (is (true? (sut/contains-zero? (sut/make-interval -3 3)))))
  (testing "[0,5] contains zero (boundary)"
    (is (true? (sut/contains-zero? (sut/make-interval 0 5)))))
  (testing "[-5,0] contains zero (boundary)"
    (is (true? (sut/contains-zero? (sut/make-interval -5 0)))))
  (testing "[-5,-1] does not contain zero"
    (is (false? (sut/contains-zero? (sut/make-interval -5 -1))))))
