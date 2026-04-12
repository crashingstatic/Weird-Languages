(ns clojure-course.higher-order-seqs.ex-09-compose-many-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-09-compose-many :as sut]))

(deftest compose-all-test
  (testing "empty list is identity"
    (is (= 42 ((sut/compose-all []) 42))))
  (testing "single function"
    (is (= 2 ((sut/compose-all [inc]) 1))))
  (testing "two functions — right-to-left"
    (is (= "2" ((sut/compose-all [str inc]) 1))))
  (testing "three increments"
    (is (= 3 ((sut/compose-all [inc inc inc]) 0))))
  (testing "multi-step transform"
    ;; (* 2) then inc then str => "11" for input 5
    (is (= "11" ((sut/compose-all [str inc #(* % 2)]) 5))))
  (testing "order matters — left is outermost"
    ;; inc then (* 2) => 12 for input 5; (* 2) then inc => 11
    (is (= 12 ((sut/compose-all [#(* % 2) inc]) 5)))
    (is (= 11 ((sut/compose-all [inc #(* % 2)]) 5)))))
