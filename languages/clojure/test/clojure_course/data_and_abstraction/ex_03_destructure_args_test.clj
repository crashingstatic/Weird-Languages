(ns clojure-course.data-and-abstraction.ex-03-destructure-args-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-03-destructure-args :as sut]))

(deftest full-name-test
  (testing "basic name"
    (is (= "Alice Smith" (sut/full-name {:first "Alice" :last "Smith"}))))
  (testing "different name"
    (is (= "Bob Jones" (sut/full-name {:first "Bob" :last "Jones"}))))
  (testing "extra keys ignored"
    (is (= "Eve Lee" (sut/full-name {:first "Eve" :last "Lee" :age 25})))))

(deftest midpoint-test
  (testing "origin and point"
    (is (= [1.0 1.0] (sut/midpoint [0 0] [2 2]))))
  (testing "same point"
    (is (= [3.0 4.0] (sut/midpoint [3 4] [3 4]))))
  (testing "negative coordinates"
    (is (= [0.0 0.0] (sut/midpoint [-5 -5] [5 5]))))
  (testing "fractional result"
    (is (= [1.5 2.5] (sut/midpoint [1 2] [2 3])))))
