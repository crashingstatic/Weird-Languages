(ns clojure-course.polymorphism-and-protocols.ex-07-expr-eval-multi-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-07-expr-eval-multi :as sut]))

(def five {:op :number :value 5})
(def three {:op :number :value 3})
(def two {:op :number :value 2})

(deftest eval-number-test
  (testing "number evaluates to its value"
    (is (= 5 (sut/eval-expr five)))
    (is (= 0 (sut/eval-expr {:op :number :value 0})))))

(deftest eval-sum-test
  (testing "sum adds left and right"
    (is (= 8 (sut/eval-expr {:op :sum :left five :right three})))))

(deftest eval-product-test
  (testing "product multiplies left and right"
    (is (= 15 (sut/eval-expr {:op :product :left five :right three})))))

(deftest eval-nested-test
  (testing "nested expression: (5 + 3) * 2"
    (is (= 16 (sut/eval-expr {:op :product
                               :left {:op :sum :left five :right three}
                               :right two}))))
  (testing "deeply nested: (5 * (3 + 2))"
    (is (= 25 (sut/eval-expr {:op :product
                               :left five
                               :right {:op :sum :left three :right two}})))))

(deftest stringify-number-test
  (testing "number stringifies to its value"
    (is (= "5" (sut/stringify five)))))

(deftest stringify-sum-test
  (testing "sum stringifies with parens"
    (is (= "(5 + 3)" (sut/stringify {:op :sum :left five :right three})))))

(deftest stringify-product-test
  (testing "product stringifies with parens"
    (is (= "(5 * 3)" (sut/stringify {:op :product :left five :right three})))))

(deftest stringify-nested-test
  (testing "nested expression stringifies correctly"
    (is (= "((5 + 3) * 2)"
           (sut/stringify {:op :product
                           :left {:op :sum :left five :right three}
                           :right two})))))
