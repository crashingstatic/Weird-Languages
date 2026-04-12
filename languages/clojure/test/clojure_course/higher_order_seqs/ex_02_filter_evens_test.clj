(ns clojure-course.higher-order-seqs.ex-02-filter-evens-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-02-filter-evens :as sut]))

(deftest evens-only-test
  (testing "empty collection"
    (is (= () (sut/evens-only []))))
  (testing "all even"
    (is (= '(2 4 6) (sut/evens-only [2 4 6]))))
  (testing "all odd"
    (is (= () (sut/evens-only [1 3 5]))))
  (testing "mixed"
    (is (= '(2 4 6) (sut/evens-only [1 2 3 4 5 6]))))
  (testing "negatives"
    (is (= '(-2 0 4) (sut/evens-only [-2 -1 0 3 4]))))
  (testing "single even"
    (is (= '(42) (sut/evens-only [42])))))
