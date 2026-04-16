(ns clojure-course.building-a-language.ex-07-lambda-and-application-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-07-lambda-and-application :as sut]))

(deftest lambda-identity-test
  (testing "identity function"
    (is (= 42 (sut/tiny-eval '((lambda (x) x) 42) (sut/make-global-env))))))

(deftest lambda-with-body-test
  (testing "lambda that uses a primitive"
    (is (= 6 (sut/tiny-eval '((lambda (x y) (+ x y)) 2 4) (sut/make-global-env))))))

(deftest define-function-sugar-test
  (testing "(define (f x) body) sugar"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (double x) (* x 2)) env)
      (is (= 10 (sut/tiny-eval '(double 5) env))))))

(deftest recursive-function-test
  (testing "recursive factorial"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (fact n) (if (= n 0) 1 (* n (fact (- n 1))))) env)
      (is (= 120 (sut/tiny-eval '(fact 5) env))))))

(deftest higher-order-test
  (testing "function that takes a function"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (apply-twice f x) (f (f x))) env)
      (sut/tiny-eval '(define (inc x) (+ x 1)) env)
      (is (= 7 (sut/tiny-eval '(apply-twice inc 5) env))))))

(deftest closure-captures-env-test
  (testing "lambda captures its defining environment"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define x 10) env)
      (sut/tiny-eval '(define (add-x y) (+ x y)) env)
      (is (= 15 (sut/tiny-eval '(add-x 5) env))))))

(deftest make-procedure-structure-test
  (testing "make-procedure returns correct structure"
    (let [proc (sut/make-procedure ["x"] ['(+ x 1)] ())]
      (is (= :compound (:type proc)))
      (is (= ["x"] (:params proc)))
      (is (= ['(+ x 1)] (:body proc))))))

(deftest begin-test
  (testing "begin evaluates multiple forms, returns last"
    (let [env (sut/make-global-env)]
      (is (= 3 (sut/tiny-eval '(begin 1 2 3) env))))))

(deftest multi-body-lambda-test
  (testing "lambda with multiple body forms"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (f x) (define y (+ x 1)) y) env)
      (is (= 6 (sut/tiny-eval '(f 5) env))))))
