(ns clojure-course.recursion-and-recur.ex-03-accumulator-pattern-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-03-accumulator-pattern :as sut]))

(deftest sum-to-n-test
  (testing "zero"
    (is (= 0 (sut/sum-to-n 0))))
  (testing "one"
    (is (= 1 (sut/sum-to-n 1))))
  (testing "small values"
    (is (= 10 (sut/sum-to-n 4)))
    (is (= 55 (sut/sum-to-n 10))))
  (testing "formula check: n*(n+1)/2"
    (is (= 5050 (sut/sum-to-n 100))))
  (testing "large input does not blow the stack"
    (is (= 500000500000 (sut/sum-to-n 1000000)))))
