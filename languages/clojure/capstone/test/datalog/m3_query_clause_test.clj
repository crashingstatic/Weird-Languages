(ns datalog.m3-query-clause-test
  (:require [clojure.test :refer [deftest is testing]]
            [datalog.facts :as facts]
            [datalog.query :as q]))

(defn people-fb []
  (facts/fact-base
   [[:alice :name "Alice"]
    [:alice :age 30]
    [:bob   :name "Bob"]
    [:bob   :age 25]
    [:carol :name "Carol"]
    [:carol :age 30]]))

(deftest single-lvar-finds-all-matches
  (let [results (q/query-clause (people-fb) '[?e :name ?n])]
    (is (= 3 (count results)))
    (is (= #{{'?e :alice '?n "Alice"}
             {'?e :bob   '?n "Bob"}
             {'?e :carol '?n "Carol"}}
           (set results)))))

(deftest fixed-attribute-and-value
  (let [results (q/query-clause (people-fb) '[?e :age 30])]
    (is (= #{{'?e :alice} {'?e :carol}}
           (set results)))))

(deftest no-matches-returns-empty
  (let [results (q/query-clause (people-fb) '[?e :name "Nobody"])]
    (is (empty? results))))

(deftest extends-existing-bindings
  (let [results (q/query-clause (people-fb) '{?e :alice} '[?e :age ?a])]
    (is (= [{'?e :alice '?a 30}] results))))

(deftest existing-bindings-filter-out-conflicts
  (let [results (q/query-clause (people-fb) '{?e :alice} '[?e :name "Bob"])]
    (is (empty? results))))
