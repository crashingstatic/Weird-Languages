(ns clojure-course.functional-foundations.ex-01-hello-values-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-01-hello-values :as sut]))

(deftest pi-test
  (testing "pi is bound to 3.14159"
    (is (= 3.14159 sut/pi))))

(deftest e-test
  (testing "e is bound to 2.71828"
    (is (= 2.71828 sut/e))))

(deftest greeting-test
  (testing "greeting is the expected string"
    (is (= "Hello, Clojure!" sut/greeting))))

(deftest answer-test
  (testing "answer is 42"
    (is (= 42 sut/answer))))

(deftest golden-ratio-test
  (testing "golden-ratio is bound to 1.6180339887"
    (is (= 1.6180339887 sut/golden-ratio))))

(deftest circle-area-test
  (testing "circle-area computes pi * r * r"
    (is (< (Math/abs (- (sut/circle-area 1) 3.14159)) 1e-6))
    (is (< (Math/abs (- (sut/circle-area 0) 0.0)) 1e-6))
    (is (< (Math/abs (- (sut/circle-area 5) 78.53975)) 1e-4))
    (is (< (Math/abs (- (sut/circle-area 10) 314.159)) 1e-3))))
