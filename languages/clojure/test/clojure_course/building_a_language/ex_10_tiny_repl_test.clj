(ns clojure-course.building-a-language.ex-10-tiny-repl-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-10-tiny-repl :as sut]))

(deftest simple-expression-test
  (testing "evaluates a simple expression"
    (is (= [3] (sut/run-program ["(+ 1 2)"])))))

(deftest multi-line-define-test
  (testing "defines persist across lines"
    (let [results (sut/run-program ["(define x 10)" "(+ x 5)"])]
      (is (= 10 (first results)))
      (is (= 15 (second results))))))

(deftest recursive-function-test
  (testing "define and call a recursive function"
    (let [results (sut/run-program
                    ["(define (fact n) (if (= n 0) 1 (* n (fact (- n 1)))))"
                     "(fact 5)"])]
      (is (= 120 (last results))))))

(deftest higher-order-program-test
  (testing "define and use a higher-order function"
    (let [results (sut/run-program
                    ["(define (twice f x) (f (f x)))"
                     "(define (add1 x) (+ x 1))"
                     "(twice add1 5)"])]
      (is (= 7 (last results))))))

(deftest closure-program-test
  (testing "closures work across program lines"
    (let [results (sut/run-program
                    ["(define (make-adder n) (lambda (x) (+ n x)))"
                     "(define add10 (make-adder 10))"
                     "(add10 5)"])]
      (is (= 15 (last results))))))

(deftest let-in-program-test
  (testing "let expressions work"
    (is (= [30] (sut/run-program ["(let ((x 10) (y 20)) (+ x y))"])))))

(deftest boolean-program-test
  (testing "boolean expressions"
    (is (= [true] (sut/run-program ["(< 1 2)"])))
    (is (= [false] (sut/run-program ["(> 1 2)"])))))

(deftest string-program-test
  (testing "string literals"
    (is (= ["hello"] (sut/run-program ["\"hello\""])))))

(deftest multi-step-computation-test
  (testing "a small multi-step program"
    (let [results (sut/run-program
                    ["(define (square x) (* x x))"
                     "(define (sum-of-squares a b) (+ (square a) (square b)))"
                     "(sum-of-squares 3 4)"])]
      (is (= 25 (last results))))))
