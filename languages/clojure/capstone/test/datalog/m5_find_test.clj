(ns datalog.m5-find-test
  (:require [clojure.test :refer [deftest is testing]]
            [datalog.facts :as facts]
            [datalog.query :as q]))

(defn people-fb []
  (facts/fact-base
   [[:alice :name "Alice"] [:alice :age 30]
    [:bob   :name "Bob"]   [:bob   :age 25]
    [:carol :name "Carol"] [:carol :age 30]
    [:dave  :name "Dave"]  [:dave  :age 25]]))

(deftest single-var-find
  (is (= #{["Alice"] ["Bob"] ["Carol"] ["Dave"]}
         (q/query (people-fb)
                  '{:find [?n]
                    :where [[?e :name ?n]]}))))

(deftest multi-var-find
  (is (= #{[:alice "Alice"] [:bob "Bob"] [:carol "Carol"] [:dave "Dave"]}
         (q/query (people-fb)
                  '{:find [?e ?n]
                    :where [[?e :name ?n]]}))))

(deftest find-collapses-duplicates-via-set
  (testing "two people share an age; projecting just the age dedupes"
    (is (= #{[30] [25]}
           (q/query (people-fb)
                    '{:find [?a]
                      :where [[?e :age ?a]]})))))

(deftest find-with-join
  (is (= #{["Alice"] ["Carol"]}
         (q/query (people-fb)
                  '{:find [?n]
                    :where [[?e :age 30]
                            [?e :name ?n]]}))))

(deftest empty-result-set
  (is (= #{}
         (q/query (people-fb)
                  '{:find [?n]
                    :where [[?e :age 99]
                            [?e :name ?n]]}))))
