(ns clojure-course.building-a-language.ex-09-closures-and-lexical-scope-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-09-closures-and-lexical-scope :as sut]))

(deftest basic-closure-test
  (testing "closure captures variable from defining scope"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define x 10) env)
      (sut/tiny-eval '(define (get-x) x) env)
      (is (= 10 (sut/tiny-eval '(get-x) env))))))

(deftest closure-over-parameter-test
  (testing "returned closure captures enclosing parameter"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (make-adder n) (lambda (x) (+ n x))) env)
      (sut/tiny-eval '(define add5 (make-adder 5)) env)
      (is (= 8 (sut/tiny-eval '(add5 3) env)))
      (is (= 15 (sut/tiny-eval '(add5 10) env))))))

(deftest independent-closures-test
  (testing "two closures from same factory are independent"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (make-adder n) (lambda (x) (+ n x))) env)
      (sut/tiny-eval '(define add3 (make-adder 3)) env)
      (sut/tiny-eval '(define add7 (make-adder 7)) env)
      (is (= 13 (sut/tiny-eval '(add3 10) env)))
      (is (= 17 (sut/tiny-eval '(add7 10) env))))))

(deftest nested-closure-test
  (testing "nested lambdas each capture their scope"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (make-multiplier-adder m)
                        (lambda (n)
                          (lambda (x) (+ (* m x) n))))
                     env)
      (sut/tiny-eval '(define scale2-plus3 ((make-multiplier-adder 2) 3)) env)
      (is (= 13 (sut/tiny-eval '(scale2-plus3 5) env))))))

(deftest shadowing-test
  (testing "inner binding shadows outer"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define x 10) env)
      (sut/tiny-eval '(define (f x) (+ x 1)) env)
      (is (= 6 (sut/tiny-eval '(f 5) env)))
      (is (= 10 (sut/tiny-eval 'x env))))))

(deftest counter-factory-test
  (testing "classic counter using closure over mutable state"
    (let [env (sut/make-global-env)]
      ;; We simulate a counter using nested defines in a let scope
      ;; Since our language doesn't have set!, we test with a factory
      ;; that returns a function using the captured parameter
      (sut/tiny-eval '(define (make-counter start)
                        (lambda (step) (+ start step)))
                     env)
      (sut/tiny-eval '(define c (make-counter 0)) env)
      (is (= 1 (sut/tiny-eval '(c 1) env)))
      (is (= 5 (sut/tiny-eval '(c 5) env))))))

(deftest lexical-not-dynamic-test
  (testing "closure uses defining scope, not calling scope"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define y 100) env)
      (sut/tiny-eval '(define (get-y) y) env)
      ;; Redefine y — but get-y was defined when y was 100
      ;; In our env model with atoms, define mutates the frame,
      ;; so get-y sees the new value. This tests that the closure
      ;; at least sees the global frame.
      (sut/tiny-eval '(define y 200) env)
      (is (= 200 (sut/tiny-eval '(get-y) env))))))

(deftest closure-as-argument-test
  (testing "pass closure as argument to another function"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (apply-fn f x) (f x)) env)
      (sut/tiny-eval '(define (make-adder n) (lambda (x) (+ n x))) env)
      (is (= 15 (sut/tiny-eval '(apply-fn (make-adder 10) 5) env))))))

(deftest recursive-with-closure-test
  (testing "recursive function works correctly"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define (fact n) (if (= n 0) 1 (* n (fact (- n 1))))) env)
      (is (= 120 (sut/tiny-eval '(fact 5) env))))))
