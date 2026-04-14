(ns clojure-course.macros-and-code-as-data.ex-05-infix-macro-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-05-infix-macro :as sut]))

(deftest addition-test
  (testing "infix addition"
    (is (= 3 (sut/infix 1 + 2)))
    (is (= 0 (sut/infix -1 + 1)))))

(deftest subtraction-test
  (testing "infix subtraction"
    (is (= 7 (sut/infix 10 - 3)))))

(deftest multiplication-test
  (testing "infix multiplication"
    (is (= 12 (sut/infix 3 * 4)))))

(deftest division-test
  (testing "infix division"
    (is (= 5 (sut/infix 10 / 2)))))

(deftest comparison-test
  (testing "infix comparison"
    (is (true? (sut/infix 1 < 2)))
    (is (false? (sut/infix 2 < 1)))
    (is (true? (sut/infix 5 = 5)))))

(deftest nested-test
  (testing "nested infix via expressions"
    (is (= 7 (sut/infix (sut/infix 1 + 2) + (sut/infix 2 * 2))))))
