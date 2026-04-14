(ns clojure-course.macros-and-code-as-data.ex-03-when-positive-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-03-when-positive :as sut]))

(deftest positive-test
  (testing "evaluates body when positive"
    (is (= 10 (sut/when-positive 5 (+ 5 5))))))

(deftest zero-test
  (testing "returns nil when zero"
    (is (nil? (sut/when-positive 0 :never)))))

(deftest negative-test
  (testing "returns nil when negative"
    (is (nil? (sut/when-positive -3 :never)))))

(deftest multiple-body-forms-test
  (testing "evaluates multiple body forms, returns last"
    (let [a (atom 0)]
      (is (= :done (sut/when-positive 1
                      (swap! a inc)
                      (swap! a inc)
                      :done)))
      (is (= 2 @a)))))

(deftest no-eval-when-not-positive-test
  (testing "does not evaluate body when not positive"
    (let [a (atom 0)]
      (sut/when-positive -1 (swap! a inc))
      (is (= 0 @a)))))
