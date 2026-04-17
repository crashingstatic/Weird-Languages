(ns datalog.m6-rules-test
  (:require [clojure.test :refer [deftest is testing]]
            [datalog.facts :as facts]
            [datalog.rules :as r]))

(defn family-fb []
  (facts/fact-base
   [[:alice :parent :bob]
    [:bob   :parent :carol]
    [:carol :parent :dave]]))

(def grandparent-rules
  '[[(grandparent ?g ?c)
     [?g :parent ?p]
     [?p :parent ?c]]])

(deftest non-recursive-rule
  (is (= #{[:alice :carol] [:bob :dave]}
         (r/query (family-fb) grandparent-rules
                  '{:find  [?g ?c]
                    :where [(grandparent ?g ?c)]}))))

(deftest rule-with-fixed-arg
  (is (= #{[:alice]}
         (r/query (family-fb) grandparent-rules
                  '{:find  [?g]
                    :where [(grandparent ?g :carol)]}))))

(deftest rule-joined-with-data-clause
  (let [fb (facts/add-facts (family-fb)
                            [[:alice :name "Alice"]
                             [:bob   :name "Bob"]])]
    (is (= #{["Alice"]}
           (r/query fb grandparent-rules
                    '{:find  [?n]
                      :where [(grandparent ?g :carol)
                              [?g :name ?n]]})))))

(deftest no-rules-runs-data-only-query
  (is (= #{[:alice] [:bob] [:carol]}
         (r/query (family-fb) []
                  '{:find  [?p]
                    :where [[?p :parent ?_]]}))))

(deftest empty-facts-empty-result
  (is (= #{}
         (r/query (facts/fact-base) grandparent-rules
                  '{:find  [?g ?c]
                    :where [(grandparent ?g ?c)]}))))

(deftest unknown-rule-returns-empty
  (is (= #{}
         (r/query (family-fb) []
                  '{:find  [?x]
                    :where [(unknown-relation ?x)]}))))
