(ns clojure-course.recursion-and-recur.ex-04-my-reverse-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-04-my-reverse :as sut]))

(deftest my-reverse-test
  (testing "empty sequence"
    (is (= () (sut/my-reverse []))))
  (testing "single element"
    (is (= '(1) (seq (sut/my-reverse [1])))))
  (testing "several elements"
    (is (= '(5 4 3 2 1) (seq (sut/my-reverse [1 2 3 4 5])))))
  (testing "strings"
    (is (= '("c" "b" "a") (seq (sut/my-reverse ["a" "b" "c"])))))
  (testing "already reversed"
    (is (= '(1 2 3) (seq (sut/my-reverse [3 2 1])))))
  (testing "100k elements — must not blow the stack"
    (let [input (range 100000)
          result (sut/my-reverse input)]
      (is (= (first result) 99999))
      (is (= (count result) 100000)))))
