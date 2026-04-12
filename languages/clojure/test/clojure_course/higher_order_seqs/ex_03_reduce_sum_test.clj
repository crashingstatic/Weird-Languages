(ns clojure-course.higher-order-seqs.ex-03-reduce-sum-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-03-reduce-sum :as sut]))

(deftest sum-all-test
  (testing "empty collection"
    (is (= 0 (sut/sum-all []))))
  (testing "single element"
    (is (= 5 (sut/sum-all [5]))))
  (testing "several elements"
    (is (= 15 (sut/sum-all [1 2 3 4 5]))))
  (testing "negatives"
    (is (= -6 (sut/sum-all [-1 -2 -3]))))
  (testing "mixed positive and negative"
    (is (= 0 (sut/sum-all [-3 -2 -1 0 1 2 3])))))
