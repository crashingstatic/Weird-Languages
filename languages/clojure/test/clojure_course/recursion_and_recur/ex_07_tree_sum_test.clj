(ns clojure-course.recursion-and-recur.ex-07-tree-sum-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-07-tree-sum :as sut]))

(deftest tree-sum-test
  (testing "empty tree"
    (is (= 0 (sut/tree-sum []))))
  (testing "flat list"
    (is (= 6 (sut/tree-sum [1 2 3]))))
  (testing "single nested level"
    (is (= 10 (sut/tree-sum [1 [2 3] 4]))))
  (testing "deeply nested"
    (is (= 10 (sut/tree-sum [1 [2 [3 [4]]]]))))
  (testing "single leaf"
    (is (= 42 (sut/tree-sum [42]))))
  (testing "nested empty vectors"
    (is (= 0 (sut/tree-sum [[] [[] []] []]))))
  (testing "mixed nesting depths"
    (is (= 21 (sut/tree-sum [1 [2 3] [4 [5 6]]]))))
  (testing "negative numbers"
    (is (= 0 (sut/tree-sum [10 [-5 [-5]]]))))
  (testing "large flat input"
    (is (= 4950 (sut/tree-sum (vec (range 100)))))))
