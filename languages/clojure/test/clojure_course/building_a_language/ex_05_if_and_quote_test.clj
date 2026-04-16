(ns clojure-course.building-a-language.ex-05-if-and-quote-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-05-if-and-quote :as sut]))

(defn- make-env []
  (let [env (list (atom {}))]
    (sut/define-var! env "x" 10)
    env))

(deftest quote-symbol-test
  (testing "quote returns a symbol without evaluating"
    (is (= 'hello (sut/tiny-eval '(quote hello) (make-env))))))

(deftest quote-list-test
  (testing "quote returns a list without evaluating"
    (is (= '(1 2 3) (sut/tiny-eval '(quote (1 2 3)) (make-env))))))

(deftest quote-nested-test
  (testing "quote preserves nested structure"
    (is (= '(a (b c)) (sut/tiny-eval '(quote (a (b c))) (make-env))))))

(deftest if-true-test
  (testing "evaluates consequent when predicate is true"
    (is (= 1 (sut/tiny-eval '(if true 1 2) (make-env))))))

(deftest if-false-test
  (testing "evaluates alternative when predicate is false"
    (is (= 2 (sut/tiny-eval '(if false 1 2) (make-env))))))

(deftest if-no-alternative-test
  (testing "returns nil when predicate is false and no alternative"
    (is (nil? (sut/tiny-eval '(if false 1) (make-env))))))

(deftest if-truthy-test
  (testing "non-false non-nil values are truthy"
    (is (= 1 (sut/tiny-eval '(if 42 1 2) (make-env))))
    (is (= 1 (sut/tiny-eval '(if "yes" 1 2) (make-env))))))

(deftest if-with-variable-test
  (testing "if works with variable in predicate"
    (is (= 1 (sut/tiny-eval '(if x 1 2) (make-env))))))
