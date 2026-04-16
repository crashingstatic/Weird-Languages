(ns clojure-course.building-a-language.ex-08-let-desugaring-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-08-let-desugaring :as sut]))

(deftest desugar-let-structure-test
  (testing "desugars to lambda application"
    (let [form (sut/desugar-let '(let ((x 1) (y 2)) (+ x y)))]
      (is (seq? form))
      (is (seq? (first form)))
      (is (= 'lambda (ffirst form))))))

(deftest let-basic-test
  (testing "let binds variables"
    (is (= 3 (sut/tiny-eval '(let ((x 1) (y 2)) (+ x y))
                             (sut/make-global-env))))))

(deftest let-single-binding-test
  (testing "let with one binding"
    (is (= 10 (sut/tiny-eval '(let ((x 10)) x)
                              (sut/make-global-env))))))

(deftest let-body-uses-outer-test
  (testing "let body can access outer variables"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(define z 100) env)
      (is (= 110 (sut/tiny-eval '(let ((x 10)) (+ x z)) env))))))

(deftest let-does-not-leak-test
  (testing "let bindings do not leak to outer scope"
    (let [env (sut/make-global-env)]
      (sut/tiny-eval '(let ((x 42)) x) env)
      (is (thrown? clojure.lang.ExceptionInfo
                   (sut/tiny-eval 'x env))))))

(deftest let-star-basic-test
  (testing "let* allows sequential bindings"
    (is (= 3 (sut/tiny-eval '(let* ((x 1) (y (+ x 2))) y)
                              (sut/make-global-env))))))

(deftest let-star-chain-test
  (testing "let* bindings can reference earlier ones"
    (is (= 3 (sut/tiny-eval '(let* ((a 1) (b (+ a 1)) (c (+ a b))) c)
                              (sut/make-global-env))))))

(deftest desugar-let-star-structure-test
  (testing "let* desugars to nested lets"
    (let [form (sut/desugar-let* '(let* ((x 1) (y 2)) body))]
      (is (= 'let (first form))))))

(deftest let-star-empty-bindings-test
  (testing "let* with no bindings evaluates body"
    (is (= 42 (sut/tiny-eval '(let* () 42)
                               (sut/make-global-env))))))
