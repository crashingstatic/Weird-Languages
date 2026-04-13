(ns clojure-course.recursion-and-recur.ex-06-my-nth-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-06-my-nth :as sut]))

(deftest my-nth-test
  (testing "first element"
    (is (= :a (sut/my-nth [:a :b :c] 0))))
  (testing "middle element"
    (is (= :b (sut/my-nth [:a :b :c] 1))))
  (testing "last element"
    (is (= :c (sut/my-nth [:a :b :c] 2))))
  (testing "single-element sequence"
    (is (= 42 (sut/my-nth [42] 0))))
  (testing "negative index throws"
    (is (thrown? IndexOutOfBoundsException (sut/my-nth [:a :b] -1))))
  (testing "index beyond end throws"
    (is (thrown? IndexOutOfBoundsException (sut/my-nth [:a :b] 2))))
  (testing "index way beyond end throws"
    (is (thrown? IndexOutOfBoundsException (sut/my-nth [:a :b] 100))))
  (testing "empty sequence throws"
    (is (thrown? IndexOutOfBoundsException (sut/my-nth [] 0)))))
