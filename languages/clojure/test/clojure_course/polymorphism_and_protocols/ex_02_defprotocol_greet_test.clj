(ns clojure-course.polymorphism-and-protocols.ex-02-defprotocol-greet-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-02-defprotocol-greet :as sut]))

(deftest person-greet-test
  (testing "Person greet returns Hello, <name>!"
    (is (= "Hello, Alice!" (sut/greet (sut/->Person "Alice"))))
    (is (= "Hello, Bob!" (sut/greet (sut/->Person "Bob"))))))

(deftest robot-greet-test
  (testing "Robot greet returns BEEP BOOP, I am <id>"
    (is (= "BEEP BOOP, I am R2D2" (sut/greet (sut/->Robot "R2D2"))))
    (is (= "BEEP BOOP, I am 42" (sut/greet (sut/->Robot 42))))))

(deftest protocol-exists-test
  (testing "Greetable protocol is satisfied by both types"
    (is (satisfies? sut/Greetable (sut/->Person "x")))
    (is (satisfies? sut/Greetable (sut/->Robot "x")))))
