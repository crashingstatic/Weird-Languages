(ns clojure-course.macros-and-code-as-data.ex-10-when-not-to-write-a-macro-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-10-when-not-to-write-a-macro :as sut]))

(deftest apply-transforms-basic-test
  (testing "applies a sequence of functions to a value"
    (is (= 4 (sut/apply-transforms 1 [inc inc inc])))
    (is (= 10 (sut/apply-transforms 5 [#(* % 2)])))))

(deftest apply-transforms-empty-test
  (testing "empty transforms returns the value"
    (is (= 42 (sut/apply-transforms 42 [])))))

(deftest apply-transforms-runtime-test
  (testing "works with runtime-computed transform sequences"
    (let [fns (map (fn [_] inc) (range 5))]
      (is (= 5 (sut/apply-transforms 0 fns))))))

(deftest apply-transforms-string-test
  (testing "works with string transforms"
    (is (= "HELLO!" (sut/apply-transforms "hello"
                       [clojure.string/upper-case
                        #(str % "!")])))))
