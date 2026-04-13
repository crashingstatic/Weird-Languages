(ns clojure-course.data-and-abstraction.ex-01-person-map-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-01-person-map :as sut]))

(deftest person-test
  (testing "person is a map"
    (is (map? sut/person)))
  (testing "has :name"
    (is (string? (:name sut/person))))
  (testing "has :age"
    (is (integer? (:age sut/person))))
  (testing "has :email"
    (is (string? (:email sut/person)))))

(deftest greet-test
  (testing "greets by name"
    (is (= "Hello, Alice!" (sut/greet {:name "Alice"}))))
  (testing "greets different name"
    (is (= "Hello, Bob!" (sut/greet {:name "Bob"}))))
  (testing "uses the person var"
    (is (= (str "Hello, " (:name sut/person) "!")
           (sut/greet sut/person)))))
