(ns datalog.m4-query-where-test
  (:require [clojure.test :refer [deftest is testing]]
            [datalog.facts :as facts]
            [datalog.query :as q]))

(defn family-fb []
  (facts/fact-base
   [[:alice :parent :bob]
    [:bob   :parent :carol]
    [:carol :parent :dave]
    [:alice :name "Alice"]
    [:bob   :name "Bob"]
    [:carol :name "Carol"]
    [:dave  :name "Dave"]]))

(deftest single-clause-degenerates-to-clause-query
  (let [where '[[?e :parent :bob]]
        results (q/query-where (family-fb) where)]
    (is (= [{'?e :alice}] results))))

(deftest two-clauses-joined-on-shared-var
  (let [where '[[?p :parent :bob]
                [?p :name ?n]]
        results (set (q/query-where (family-fb) where))]
    (is (= #{{'?p :alice '?n "Alice"}} results))))

(deftest three-clauses-chained-shared-vars
  (let [where '[[?a :parent ?b]
                [?b :parent ?c]
                [?c :parent :dave]]
        results (set (q/query-where (family-fb) where))]
    (is (= #{{'?a :alice '?b :bob '?c :carol}} results))))

(deftest empty-result-when-no-join-satisfies
  (let [where '[[?p :parent :bob]
                [?p :name "Carol"]]
        results (q/query-where (family-fb) where)]
    (is (empty? results))))

(deftest cartesian-when-no-shared-vars
  (let [fb (facts/fact-base [[:a :rel 1] [:b :rel 2]])
        where '[[?x :rel ?v1]
                [?y :rel ?v2]]
        results (set (q/query-where fb where))]
    (is (= 4 (count results)))))
