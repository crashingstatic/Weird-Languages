(ns clojure-course.functional-foundations.ex-03-absolute-value-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-03-absolute-value :as sut]))

(deftest my-abs-test
  (testing "positive stays positive"
    (is (= 5 (sut/my-abs 5))))
  (testing "negative becomes positive"
    (is (= 3 (sut/my-abs -3))))
  (testing "zero stays zero"
    (is (= 0 (sut/my-abs 0))))
  (testing "large negative"
    (is (= 1000000 (sut/my-abs -1000000)))))

(deftest sign-test
  (testing "negative"
    (is (= -1 (sut/sign -42))))
  (testing "zero"
    (is (= 0 (sut/sign 0))))
  (testing "positive"
    (is (= 1 (sut/sign 99))))
  (testing "negative fraction"
    (is (= -1 (sut/sign -0.5))))
  (testing "positive fraction"
    (is (= 1 (sut/sign 0.001)))))

(deftest clamp-test
  (testing "value within range"
    (is (= 5 (sut/clamp 5 0 10))))
  (testing "value below range"
    (is (= 0 (sut/clamp -3 0 10))))
  (testing "value above range"
    (is (= 10 (sut/clamp 15 0 10))))
  (testing "value at lower bound"
    (is (= 0 (sut/clamp 0 0 10))))
  (testing "value at upper bound"
    (is (= 10 (sut/clamp 10 0 10))))
  (testing "negative range"
    (is (= -5 (sut/clamp -10 -5 -1)))))
