(ns clojure-course.functional-foundations.ex-04-basic-arithmetic-fns-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-04-basic-arithmetic-fns :as sut]))

(defn- close? [a b]
  (< (Math/abs (- (double a) (double b))) 1e-6))

(deftest square-test
  (testing "positive"
    (is (= 25 (sut/square 5))))
  (testing "negative"
    (is (= 9 (sut/square -3))))
  (testing "zero"
    (is (= 0 (sut/square 0))))
  (testing "one"
    (is (= 1 (sut/square 1)))))

(deftest cube-test
  (testing "positive"
    (is (= 27 (sut/cube 3))))
  (testing "negative"
    (is (= -8 (sut/cube -2))))
  (testing "zero"
    (is (= 0 (sut/cube 0)))))

(deftest average-test
  (testing "two positives"
    (is (close? 3.0 (sut/average 2 4))))
  (testing "same values"
    (is (close? 5.0 (sut/average 5 5))))
  (testing "positive and negative"
    (is (close? 0.0 (sut/average -3 3))))
  (testing "fractions"
    (is (close? 1.75 (sut/average 1.5 2.0)))))

(deftest distance-2d-test
  (testing "horizontal distance"
    (is (close? 3.0 (sut/distance-2d 0 0 3 0))))
  (testing "vertical distance"
    (is (close? 4.0 (sut/distance-2d 0 0 0 4))))
  (testing "3-4-5 triangle"
    (is (close? 5.0 (sut/distance-2d 0 0 3 4))))
  (testing "same point"
    (is (close? 0.0 (sut/distance-2d 1 1 1 1))))
  (testing "negative coordinates"
    (is (close? 5.0 (sut/distance-2d -1 -1 2 3)))))
