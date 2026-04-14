(ns clojure-course.macros-and-code-as-data.ex-07-cond-thread-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-07-cond-thread :as sut]))

(deftest basic-threading-test
  (testing "threads through truthy conditions"
    (is (= 3 (sut/my-cond-> 1
               true (+ 1)
               true (+ 1))))))

(deftest skips-falsy-test
  (testing "skips forms with falsy conditions"
    (is (= 2 (sut/my-cond-> 1
               true (+ 1)
               false (+ 100))))))

(deftest all-falsy-test
  (testing "returns initial value when all conditions falsy"
    (is (= 1 (sut/my-cond-> 1
               false (+ 10)
               nil (+ 20))))))

(deftest map-threading-test
  (testing "works with map operations"
    (is (= {:a 1 :b 2}
           (sut/my-cond-> {}
             true (assoc :a 1)
             true (assoc :b 2)
             false (assoc :c 3))))))

(deftest no-clauses-test
  (testing "no clauses returns the value"
    (is (= 42 (sut/my-cond-> 42)))))
