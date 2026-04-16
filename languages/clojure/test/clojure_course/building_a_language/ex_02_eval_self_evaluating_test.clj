(ns clojure-course.building-a-language.ex-02-eval-self-evaluating-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-02-eval-self-evaluating :as sut]))

(deftest eval-numbers-test
  (testing "integers evaluate to themselves"
    (is (= 42 (sut/tiny-eval 42)))
    (is (= 0 (sut/tiny-eval 0)))
    (is (= -7 (sut/tiny-eval -7)))))

(deftest eval-strings-test
  (testing "strings evaluate to themselves"
    (is (= "hello" (sut/tiny-eval "hello")))
    (is (= "" (sut/tiny-eval "")))))

(deftest eval-booleans-test
  (testing "true evaluates to true"
    (is (true? (sut/tiny-eval true))))
  (testing "false evaluates to false"
    (is (false? (sut/tiny-eval false)))))

(deftest self-evaluating-predicate-test
  (testing "numbers are self-evaluating"
    (is (true? (sut/self-evaluating? 42))))
  (testing "strings are self-evaluating"
    (is (true? (sut/self-evaluating? "hi"))))
  (testing "booleans are self-evaluating"
    (is (true? (sut/self-evaluating? true)))
    (is (true? (sut/self-evaluating? false))))
  (testing "symbols are not self-evaluating"
    (is (false? (sut/self-evaluating? 'x))))
  (testing "lists are not self-evaluating"
    (is (false? (sut/self-evaluating? '(+ 1 2))))))
