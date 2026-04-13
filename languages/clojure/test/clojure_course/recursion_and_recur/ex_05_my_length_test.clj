(ns clojure-course.recursion-and-recur.ex-05-my-length-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-05-my-length :as sut]))

(deftest my-length-test
  (testing "empty"
    (is (= 0 (sut/my-length []))))
  (testing "one element"
    (is (= 1 (sut/my-length [:a]))))
  (testing "several elements"
    (is (= 5 (sut/my-length [10 20 30 40 50]))))
  (testing "strings"
    (is (= 3 (sut/my-length ["x" "y" "z"]))))
  (testing "1M elements — must not blow the stack"
    (is (= 1000000 (sut/my-length (range 1000000))))))
