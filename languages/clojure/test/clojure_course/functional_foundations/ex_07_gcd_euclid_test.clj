(ns clojure-course.functional-foundations.ex-07-gcd-euclid-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-07-gcd-euclid :as sut]))

(deftest gcd-test
  (testing "basic cases"
    (is (= 6 (sut/gcd 12 18)))
    (is (= 4 (sut/gcd 8 12)))
    (is (= 1 (sut/gcd 7 13))))
  (testing "swapped argument order"
    (is (= 6 (sut/gcd 18 12)))
    (is (= 4 (sut/gcd 12 8))))
  (testing "identical inputs"
    (is (= 5 (sut/gcd 5 5)))
    (is (= 1 (sut/gcd 1 1))))
  (testing "one argument is zero"
    (is (= 7 (sut/gcd 7 0)))
    (is (= 7 (sut/gcd 0 7))))
  (testing "both zero"
    (is (= 0 (sut/gcd 0 0))))
  (testing "negative inputs"
    (is (= 6 (sut/gcd -12 18)))
    (is (= 6 (sut/gcd 12 -18)))
    (is (= 6 (sut/gcd -12 -18))))
  (testing "coprime"
    (is (= 1 (sut/gcd 17 31))))
  (testing "one divides the other"
    (is (= 5 (sut/gcd 5 25)))
    (is (= 5 (sut/gcd 25 5)))))
