(ns clojure-course.data-and-abstraction.ex-02-assoc-update-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-02-assoc-update :as sut]))

(def ^:private alice {:name "Alice" :age 30 :email "alice@example.com"})

(deftest set-name-test
  (testing "replaces name"
    (is (= "Bob" (:name (sut/set-name alice "Bob")))))
  (testing "preserves other fields"
    (let [result (sut/set-name alice "Bob")]
      (is (= 30 (:age result)))
      (is (= "alice@example.com" (:email result)))))
  (testing "original unchanged"
    (sut/set-name alice "Bob")
    (is (= "Alice" (:name alice)))))

(deftest birthday-test
  (testing "increments age"
    (is (= 31 (:age (sut/birthday alice)))))
  (testing "preserves other fields"
    (is (= "Alice" (:name (sut/birthday alice)))))
  (testing "stacks"
    (is (= 32 (:age (sut/birthday (sut/birthday alice)))))))

(deftest update-email-test
  (testing "replaces email"
    (is (= "bob@example.com" (:email (sut/update-email alice "bob@example.com")))))
  (testing "preserves other fields"
    (let [result (sut/update-email alice "new@email.com")]
      (is (= "Alice" (:name result)))
      (is (= 30 (:age result))))))
