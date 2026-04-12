(ns clojure-course.functional-foundations.ex-02-let-binding-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-02-let-binding :as sut]))

(defn- close? [a b]
  (< (Math/abs (- (double a) (double b))) 1e-6))

(deftest circle-area-test
  (testing "unit circle"
    (is (close? 3.14159 (sut/circle-area 1))))
  (testing "radius 0"
    (is (close? 0.0 (sut/circle-area 0))))
  (testing "radius 5"
    (is (close? 78.53975 (sut/circle-area 5))))
  (testing "radius 2.5"
    (is (close? 19.6349375 (sut/circle-area 2.5)))))

(deftest cylinder-volume-test
  (testing "unit cylinder"
    (is (close? 3.14159 (sut/cylinder-volume 1 1))))
  (testing "radius 2, height 3"
    (is (close? 37.69908 (sut/cylinder-volume 2 3))))
  (testing "zero height"
    (is (close? 0.0 (sut/cylinder-volume 5 0))))
  (testing "zero radius"
    (is (close? 0.0 (sut/cylinder-volume 0 10)))))
