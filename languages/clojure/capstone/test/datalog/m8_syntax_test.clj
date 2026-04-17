(ns datalog.m8-syntax-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [datalog.facts :as facts]
            [datalog.rules :as r]
            [datalog.syntax :as s :refer [defrule ?-]]))

(use-fixtures :each (fn [t] (s/reset-rules!) (t)))

(defn family-fb []
  (facts/fact-base
   [[:alice :parent :bob]
    [:bob   :parent :carol]
    [:carol :parent :dave]]))

(deftest defrule-registers-rule
  (defrule (parent-of ?p ?c) [?p :parent ?c])
  (is (= 1 (count @s/*rules*))))

(deftest macro-query-equals-data-query
  (defrule (ancestor ?a ?d) [?a :parent ?d])
  (defrule (ancestor ?a ?d) [?a :parent ?x] (ancestor ?x ?d))
  (let [data-rules '[[(ancestor ?a ?d) [?a :parent ?d]]
                     [(ancestor ?a ?d) [?a :parent ?x] (ancestor ?x ?d)]]
        macro-result (?- (family-fb) [?a] (ancestor ?a :dave))
        data-result  (r/query (family-fb) data-rules
                              '{:find  [?a]
                                :where [(ancestor ?a :dave)]})]
    (is (= data-result macro-result))
    (is (= #{[:alice] [:bob] [:carol]} macro-result))))

(deftest macro-query-with-multi-var-find
  (defrule (grandparent ?g ?c)
    [?g :parent ?p]
    [?p :parent ?c])
  (is (= #{[:alice :carol] [:bob :dave]}
         (?- (family-fb) [?g ?c] (grandparent ?g ?c)))))

(deftest macro-query-with-data-clause-only
  (is (= #{[:alice] [:bob] [:carol]}
         (?- (family-fb) [?p] [?p :parent ?_]))))

(deftest reset-rules-clears-registry
  (defrule (foo ?x) [?x :rel :y])
  (is (= 1 (count @s/*rules*)))
  (s/reset-rules!)
  (is (= 0 (count @s/*rules*))))
