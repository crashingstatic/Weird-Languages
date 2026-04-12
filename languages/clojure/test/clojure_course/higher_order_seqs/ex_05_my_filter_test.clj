(ns clojure-course.higher-order-seqs.ex-05-my-filter-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-05-my-filter :as sut]))

(deftest my-filter-test
  (testing "empty collection"
    (is (= () (sut/my-filter even? []))))
  (testing "all match"
    (is (= '(2 4 6) (sut/my-filter even? [2 4 6]))))
  (testing "none match"
    (is (= () (sut/my-filter even? [1 3 5]))))
  (testing "mixed"
    (is (= '(2 4 6) (sut/my-filter even? [1 2 3 4 5 6]))))
  (testing "with pos?"
    (is (= '(1 2 3) (sut/my-filter pos? [-2 -1 0 1 2 3]))))
  (testing "with string predicate"
    (is (= '("ab" "abc") (sut/my-filter #(> (count %) 1) ["a" "ab" "abc"]))))
  (testing "preserves order"
    (is (= '(1 3 5 7 9) (sut/my-filter odd? (range 10))))))
