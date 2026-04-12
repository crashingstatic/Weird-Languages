(ns clojure-course.higher-order-seqs.ex-06-my-reduce-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-06-my-reduce :as sut]))

(deftest my-reduce-three-arity-test
  (testing "sum with init"
    (is (= 10 (sut/my-reduce + 0 [1 2 3 4]))))
  (testing "string concatenation"
    (is (= "abc" (sut/my-reduce str "" ["a" "b" "c"]))))
  (testing "empty coll returns init"
    (is (= 42 (sut/my-reduce + 42 []))))
  (testing "single element"
    (is (= 5 (sut/my-reduce + 0 [5]))))
  (testing "building a vector"
    (is (= [1 2 3] (sut/my-reduce conj [] [1 2 3])))))

(deftest my-reduce-two-arity-test
  (testing "sum without init"
    (is (= 10 (sut/my-reduce + [1 2 3 4]))))
  (testing "single element returns it"
    (is (= 7 (sut/my-reduce + [7]))))
  (testing "empty coll calls (f)"
    (is (= 0 (sut/my-reduce + [])))
    (is (= 1 (sut/my-reduce * []))))
  (testing "multiplication"
    (is (= 24 (sut/my-reduce * [1 2 3 4])))))
