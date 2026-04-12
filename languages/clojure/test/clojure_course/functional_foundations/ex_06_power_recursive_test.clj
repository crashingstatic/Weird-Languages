(ns clojure-course.functional-foundations.ex-06-power-recursive-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-06-power-recursive :as sut]))

(deftest power-test
  (testing "anything to the zero is 1"
    (is (= 1 (sut/power 5 0)))
    (is (= 1 (sut/power 0 0)))
    (is (= 1 (sut/power 100 0))))
  (testing "anything to the one is itself"
    (is (= 7 (sut/power 7 1)))
    (is (= 1 (sut/power 1 1))))
  (testing "small inputs"
    (is (= 8 (sut/power 2 3)))
    (is (= 27 (sut/power 3 3)))
    (is (= 1024 (sut/power 2 10))))
  (testing "base zero"
    (is (= 0 (sut/power 0 5))))
  (testing "base one"
    (is (= 1 (sut/power 1 1000))))
  (testing "negative base"
    (is (= -8 (sut/power -2 3)))
    (is (= 16 (sut/power -2 4)))))
