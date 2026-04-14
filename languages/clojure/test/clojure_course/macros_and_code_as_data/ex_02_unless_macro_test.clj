(ns clojure-course.macros-and-code-as-data.ex-02-unless-macro-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-02-unless-macro :as sut]))

(deftest unless-false-test
  (testing "when condition is false, evaluates the then branch"
    (is (= :then (sut/unless false :then :else)))))

(deftest unless-true-test
  (testing "when condition is true, evaluates the else branch"
    (is (= :else (sut/unless true :then :else)))))

(deftest unless-nil-test
  (testing "nil is falsy"
    (is (= :then (sut/unless nil :then :else)))))

(deftest unless-truthy-test
  (testing "non-nil non-false is truthy"
    (is (= :else (sut/unless 42 :then :else)))))

(deftest unless-side-effects-test
  (testing "only evaluates the chosen branch"
    (let [counter (atom 0)]
      (sut/unless true (swap! counter inc) :skipped)
      (is (= 0 @counter)))
    (let [counter (atom 0)]
      (sut/unless false (swap! counter inc) :skipped)
      (is (= 1 @counter)))))
