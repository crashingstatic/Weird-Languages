(ns datalog.m2-unify-test
  (:require [clojure.test :refer [deftest is testing]]
            [datalog.unify :as u]))

(deftest lvar-recognition
  (is (u/lvar? '?x))
  (is (u/lvar? '?name))
  (is (not (u/lvar? 'x)))
  (is (not (u/lvar? :keyword)))
  (is (not (u/lvar? "?string")))
  (is (not (u/lvar? 42))))

(deftest exact-literal-match
  (is (= {} (u/match '[:alice :name "Alice"] [:alice :name "Alice"]))))

(deftest exact-literal-mismatch
  (is (nil? (u/match '[:alice :name "Alice"] [:bob :name "Bob"]))))

(deftest single-lvar-binds
  (is (= '{?e :alice}
         (u/match '[?e :name "Alice"] [:alice :name "Alice"]))))

(deftest multiple-lvars-bind
  (is (= '{?e :alice ?n "Alice"}
         (u/match '[?e :name ?n] [:alice :name "Alice"]))))

(deftest attribute-mismatch-fails
  (is (nil? (u/match '[?e :name ?n] [:alice :age 30]))))

(deftest repeated-lvar-must-be-consistent
  (testing "same value -> success"
    (is (= '{?x :alice}
           (u/match '[?x :friend ?x] [:alice :friend :alice]))))
  (testing "different values -> failure"
    (is (nil? (u/match '[?x :friend ?x] [:alice :friend :bob])))))

(deftest match-extends-existing-bindings
  (is (= '{?e :alice ?n "Alice"}
         (u/match '{?e :alice} '[?e :name ?n] [:alice :name "Alice"])))
  (testing "existing binding conflicts with literal in fact"
    (is (nil? (u/match '{?e :bob} '[?e :name "Alice"] [:alice :name "Alice"])))))

(deftest substitute-replaces-bound-lvars
  (is (= [:alice :name '?n]
         (u/substitute '{?e :alice} '[?e :name ?n]))))

(deftest substitute-leaves-unbound-alone
  (is (= '[?e :name ?n]
         (u/substitute {} '[?e :name ?n]))))
