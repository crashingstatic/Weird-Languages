(ns clojure-course.polymorphism-and-protocols.ex-09-tagged-arithmetic-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-09-tagged-arithmetic :as sut]))

(deftest attach-tag-test
  (testing "attach-tag and accessors round-trip"
    (let [tagged (sut/attach-tag :integer 42)]
      (is (= :integer (sut/type-tag tagged)))
      (is (= 42 (sut/contents tagged)))))
  (testing "rational tag"
    (let [tagged (sut/attach-tag :rational [1 2])]
      (is (= :rational (sut/type-tag tagged)))
      (is (= [1 2] (sut/contents tagged))))))

(deftest add-integer-integer-test
  (testing "adding two integers"
    (let [a (sut/attach-tag :integer 3)
          b (sut/attach-tag :integer 5)
          result (sut/add a b)]
      (is (= :integer (sut/type-tag result)))
      (is (= 8 (sut/contents result))))))

(deftest add-rational-rational-test
  (testing "adding two rationals: 1/2 + 1/3 = 5/6"
    (let [a (sut/attach-tag :rational [1 2])
          b (sut/attach-tag :rational [1 3])
          result (sut/add a b)]
      (is (= :rational (sut/type-tag result)))
      (is (= [5 6] (sut/contents result))))))

(deftest add-integer-rational-test
  (testing "adding integer + rational: 2 + 1/3 = 7/3"
    (let [a (sut/attach-tag :integer 2)
          b (sut/attach-tag :rational [1 3])
          result (sut/add a b)]
      (is (= :rational (sut/type-tag result)))
      (is (= [7 3] (sut/contents result))))))

(deftest add-rational-integer-test
  (testing "adding rational + integer: 1/4 + 3 = 13/4"
    (let [a (sut/attach-tag :rational [1 4])
          b (sut/attach-tag :integer 3)
          result (sut/add a b)]
      (is (= :rational (sut/type-tag result)))
      (is (= [13 4] (sut/contents result))))))
