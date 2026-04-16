(ns clojure-course.building-a-language.ex-01-parse-atom-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-01-parse-atom :as sut]))

(deftest parse-atom-integers-test
  (testing "parses positive integers"
    (is (= 42 (sut/parse-atom "42")))
    (is (= 0 (sut/parse-atom "0"))))
  (testing "parses negative integers"
    (is (= -7 (sut/parse-atom "-7")))))

(deftest parse-atom-booleans-test
  (testing "parses true"
    (is (true? (sut/parse-atom "true"))))
  (testing "parses false"
    (is (false? (sut/parse-atom "false")))))

(deftest parse-atom-strings-test
  (testing "strips quotes from string literals"
    (is (= "hello" (sut/parse-atom "\"hello\"")))
    (is (= "" (sut/parse-atom "\"\"")))))

(deftest parse-atom-symbols-test
  (testing "returns symbols for identifiers"
    (is (= '+ (sut/parse-atom "+")))
    (is (= 'hello (sut/parse-atom "hello")))
    (is (symbol? (sut/parse-atom "define")))))

(deftest parse-full-expression-test
  (testing "parses a simple s-expression"
    (is (= '(+ 1 2) (sut/parse "(+ 1 2)"))))
  (testing "parses nested expressions"
    (is (= '(+ (* 2 3) 4) (sut/parse "(+ (* 2 3) 4)"))))
  (testing "parses quote sugar"
    (is (= '(quote hello) (sut/parse "'hello"))))
  (testing "parses booleans inside expressions"
    (is (= '(if true 1 0) (sut/parse "(if true 1 0)")))))
