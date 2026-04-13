(ns clojure-course.recursion-and-recur.ex-02-tail-recur-sum-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-02-tail-recur-sum :as sut]))

(deftest running-sum-test
  (testing "empty list"
    (is (= 0 (sut/running-sum []))))
  (testing "single element"
    (is (= 42 (sut/running-sum [42]))))
  (testing "several elements"
    (is (= 15 (sut/running-sum [1 2 3 4 5]))))
  (testing "negative numbers"
    (is (= -6 (sut/running-sum [-1 -2 -3]))))
  (testing "mixed positive and negative"
    (is (= 0 (sut/running-sum [10 -10 20 -20]))))
  (testing "large input does not blow the stack"
    (is (= 500000500000 (sut/running-sum (range 1 1000001))))))
