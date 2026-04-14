(ns clojure-course.macros-and-code-as-data.ex-04-when-not-from-scratch-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-04-when-not-from-scratch :as sut]))

(deftest returns-body-when-falsy-test
  (testing "returns body value when condition is false"
    (is (= :yes (sut/my-when-not false :yes))))
  (testing "returns body value when condition is nil"
    (is (= :yes (sut/my-when-not nil :yes)))))

(deftest returns-nil-when-truthy-test
  (testing "returns nil when condition is true"
    (is (nil? (sut/my-when-not true :nope))))
  (testing "returns nil when condition is truthy"
    (is (nil? (sut/my-when-not 42 :nope)))))

(deftest does-not-evaluate-body-when-truthy-test
  (testing "body is NOT evaluated when condition is truthy"
    (let [counter (atom 0)]
      (sut/my-when-not true (swap! counter inc))
      (is (= 0 @counter) "body was evaluated when it shouldn't have been"))))

(deftest evaluates-multiple-body-forms-test
  (testing "evaluates all body forms and returns last"
    (let [a (atom 0)]
      (is (= :done (sut/my-when-not false
                      (swap! a inc)
                      (swap! a inc)
                      :done)))
      (is (= 2 @a)))))
