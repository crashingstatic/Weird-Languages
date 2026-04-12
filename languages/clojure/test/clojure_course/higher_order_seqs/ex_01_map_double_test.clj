(ns clojure-course.higher-order-seqs.ex-01-map-double-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-01-map-double :as sut]))

(deftest double-all-test
  (testing "empty collection"
    (is (= () (sut/double-all []))))
  (testing "single element"
    (is (= '(4) (sut/double-all [2]))))
  (testing "several elements"
    (is (= '(2 4 6 8 10) (sut/double-all [1 2 3 4 5]))))
  (testing "negative numbers"
    (is (= '(-2 -4 -6) (sut/double-all [-1 -2 -3]))))
  (testing "zeros"
    (is (= '(0 0 0) (sut/double-all [0 0 0])))))
