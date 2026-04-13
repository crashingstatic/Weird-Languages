(ns clojure-course.data-and-abstraction.ex-04-point-2d-abstract-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-04-point-2d-abstract :as sut]))

(defn- close? [a b]
  (< (Math/abs (- (double a) (double b))) 1e-6))

(deftest constructor-selector-test
  (testing "make-point and selectors round-trip"
    (let [p (sut/make-point 3 4)]
      (is (= 3 (sut/point-x p)))
      (is (= 4 (sut/point-y p)))))
  (testing "negative coordinates"
    (let [p (sut/make-point -1 -2)]
      (is (= -1 (sut/point-x p)))
      (is (= -2 (sut/point-y p)))))
  (testing "zero coordinates"
    (let [p (sut/make-point 0 0)]
      (is (= 0 (sut/point-x p)))
      (is (= 0 (sut/point-y p))))))

(deftest distance-test
  (testing "same point"
    (let [p (sut/make-point 1 1)]
      (is (close? 0.0 (sut/distance p p)))))
  (testing "3-4-5 triangle"
    (let [p1 (sut/make-point 0 0)
          p2 (sut/make-point 3 4)]
      (is (close? 5.0 (sut/distance p1 p2)))))
  (testing "horizontal distance"
    (let [p1 (sut/make-point 0 0)
          p2 (sut/make-point 10 0)]
      (is (close? 10.0 (sut/distance p1 p2)))))
  (testing "vertical distance"
    (let [p1 (sut/make-point 0 0)
          p2 (sut/make-point 0 7)]
      (is (close? 7.0 (sut/distance p1 p2)))))
  (testing "symmetric"
    (let [p1 (sut/make-point 1 2)
          p2 (sut/make-point 4 6)]
      (is (close? (sut/distance p1 p2) (sut/distance p2 p1))))))
