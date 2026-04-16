(ns clojure-course.building-a-language.ex-06-primitive-procedures-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-06-primitive-procedures :as sut]))

(deftest arithmetic-test
  (testing "addition"
    (is (= 5 (sut/tiny-eval '(+ 2 3) (sut/make-global-env)))))
  (testing "subtraction"
    (is (= 7 (sut/tiny-eval '(- 10 3) (sut/make-global-env)))))
  (testing "multiplication"
    (is (= 12 (sut/tiny-eval '(* 3 4) (sut/make-global-env)))))
  (testing "division"
    (is (= 5 (sut/tiny-eval '(/ 10 2) (sut/make-global-env))))))

(deftest comparison-test
  (testing "equality"
    (is (true? (sut/tiny-eval '(= 1 1) (sut/make-global-env))))
    (is (false? (sut/tiny-eval '(= 1 2) (sut/make-global-env)))))
  (testing "less than"
    (is (true? (sut/tiny-eval '(< 1 2) (sut/make-global-env))))
    (is (false? (sut/tiny-eval '(< 2 1) (sut/make-global-env)))))
  (testing "greater than"
    (is (true? (sut/tiny-eval '(> 3 1) (sut/make-global-env)))))
  (testing "not"
    (is (true? (sut/tiny-eval '(not false) (sut/make-global-env))))
    (is (false? (sut/tiny-eval '(not true) (sut/make-global-env))))))

(deftest nested-expressions-test
  (testing "nested arithmetic"
    (is (= 10 (sut/tiny-eval '(+ (* 2 3) (- 7 3)) (sut/make-global-env))))))

(deftest define-and-use-test
  (testing "define a variable and use it in an expression"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define x 10) env)
      (is (= 15 (sut/tiny-eval '(+ x 5) env))))))

(deftest conditional-with-comparison-test
  (testing "if with comparison predicate"
    (is (= "yes" (sut/tiny-eval '(if (< 1 2) "yes" "no") (sut/make-global-env))))))
