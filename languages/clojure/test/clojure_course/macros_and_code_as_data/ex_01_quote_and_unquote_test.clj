(ns clojure-course.macros-and-code-as-data.ex-01-quote-and-unquote-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-01-quote-and-unquote :as sut]))

(deftest quoted-symbol-test
  (testing "returns the symbol hello"
    (is (symbol? (sut/quoted-symbol)))
    (is (= 'hello (sut/quoted-symbol)))))

(deftest make-inc-form-test
  (testing "returns a list starting with inc"
    (let [form (sut/make-inc-form 5)]
      (is (seq? form))
      (is (= 'clojure.core/inc (first form)))
      (is (= 5 (second form)))))
  (testing "works with a symbol"
    (let [form (sut/make-inc-form 'x)]
      (is (= 'x (second form))))))

(deftest make-sum-form-test
  (testing "splices args into a + form"
    (let [form (sut/make-sum-form [1 2 3])]
      (is (seq? form))
      (is (= 'clojure.core/+ (first form)))
      (is (= [1 2 3] (rest form)))))
  (testing "empty args"
    (let [form (sut/make-sum-form [])]
      (is (= '(clojure.core/+) form)))))
