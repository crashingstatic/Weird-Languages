(ns clojure-course.recursion-and-recur.ex-09-my-flatten-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-09-my-flatten :as sut]))

(deftest my-flatten-test
  (testing "empty"
    (is (= () (sut/my-flatten []))))
  (testing "already flat"
    (is (= '(1 2 3) (seq (sut/my-flatten [1 2 3])))))
  (testing "one level of nesting"
    (is (= '(1 2 3 4) (seq (sut/my-flatten [1 [2 3] 4])))))
  (testing "deep nesting"
    (is (= '(1 2 3 4) (seq (sut/my-flatten [1 [2 [3 [4]]]])))))
  (testing "nested empty vectors"
    (is (= () (sut/my-flatten [[] [[] []] []]))))
  (testing "mixed types"
    (is (= '(:a 1 "b" :c) (seq (sut/my-flatten [:a [1 ["b"]] :c])))))
  (testing "preserves order"
    (is (= '(1 2 3 4 5 6) (seq (sut/my-flatten [[1 2] [3 [4 5]] [6]])))))
  (testing "matches clojure.core/flatten"
    (let [input [1 [2 [3 [4 [5]]]] [6 7] [[8] 9] 10]]
      (is (= (flatten input) (seq (sut/my-flatten input)))))))
