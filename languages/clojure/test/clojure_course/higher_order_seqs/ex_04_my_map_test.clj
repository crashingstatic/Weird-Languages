(ns clojure-course.higher-order-seqs.ex-04-my-map-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-04-my-map :as sut]))

(deftest my-map-test
  (testing "empty collection"
    (is (= () (sut/my-map inc []))))
  (testing "single element"
    (is (= '(2) (sut/my-map inc [1]))))
  (testing "several elements with inc"
    (is (= '(2 3 4 5) (sut/my-map inc [1 2 3 4]))))
  (testing "with str"
    (is (= '("1" "2" "3") (sut/my-map str [1 2 3]))))
  (testing "with anonymous function"
    (is (= '(1 4 9 16) (sut/my-map #(* % %) [1 2 3 4]))))
  (testing "preserves order"
    (is (= '(10 20 30) (sut/my-map #(* % 10) [1 2 3]))))
  (testing "works on lists"
    (is (= '(2 4 6) (sut/my-map #(* % 2) '(1 2 3))))))
