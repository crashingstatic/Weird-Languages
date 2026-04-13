(ns clojure-course.recursion-and-recur.ex-01-convert-to-recur-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-01-convert-to-recur :as sut]))

(deftest factorial-test
  (testing "base cases"
    (is (= 1 (sut/factorial 0)))
    (is (= 1 (sut/factorial 1))))
  (testing "small inputs"
    (is (= 6 (sut/factorial 3)))
    (is (= 120 (sut/factorial 5)))
    (is (= 3628800 (sut/factorial 10))))
  (testing "larger input"
    (is (= 2432902008176640000 (sut/factorial 20)))))
