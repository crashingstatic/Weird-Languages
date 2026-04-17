(ns datalog.m7-recursive-rules-test
  (:require [clojure.test :refer [deftest is testing]]
            [datalog.facts :as facts]
            [datalog.rules :as r]))

(def ^:private timeout-ms 10000)

(defn- with-timeout
  "Run `thunk` in a future, deref with a hard timeout. Returns
   the result, or ::timeout if the future didn't finish in time.
   Cancels the future on timeout so a buggy implementation that
   infinite-loops doesn't hang the test runner."
  [thunk]
  (let [f      (future (thunk))
        result (deref f timeout-ms ::timeout)]
    (when (= result ::timeout) (future-cancel f))
    result))

(defn family-fb []
  (facts/fact-base
   [[:alice :parent :bob]
    [:bob   :parent :carol]
    [:carol :parent :dave]
    [:dave  :parent :eve]]))

(def ancestor-rules
  '[[(ancestor ?a ?d)
     [?a :parent ?d]]
    [(ancestor ?a ?d)
     [?a :parent ?x]
     (ancestor ?x ?d)]])

(deftest ancestor-of-leaf
  (let [result (with-timeout
                 #(r/query (family-fb) ancestor-rules
                           '{:find  [?a]
                             :where [(ancestor ?a :eve)]}))]
    (is (not= ::timeout result))
    (is (= #{[:alice] [:bob] [:carol] [:dave]} result))))

(deftest descendants-of-root
  (let [result (with-timeout
                 #(r/query (family-fb) ancestor-rules
                           '{:find  [?d]
                             :where [(ancestor :alice ?d)]}))]
    (is (not= ::timeout result))
    (is (= #{[:bob] [:carol] [:dave] [:eve]} result))))

(defn graph-fb []
  (facts/fact-base
   [[:a :edge :b]
    [:b :edge :c]
    [:c :edge :d]
    [:d :edge :e]
    [:e :edge :a]]))           ; cycle a -> b -> c -> d -> e -> a

(def reachable-rules
  '[[(reachable ?x ?y)
     [?x :edge ?y]]
    [(reachable ?x ?y)
     [?x :edge ?z]
     (reachable ?z ?y)]])

(deftest reachable-on-cyclic-graph-terminates
  (testing "fixed-point detection prevents infinite loop on a cycle"
    (let [result (with-timeout
                   #(r/query (graph-fb) reachable-rules
                             '{:find  [?y]
                               :where [(reachable :a ?y)]}))]
      (is (not= ::timeout result))
      (is (= #{[:a] [:b] [:c] [:d] [:e]} result)))))

(def left-recursive-rules
  ;; ancestor defined left-recursively. A naive top-down evaluator that
  ;; expands the recursive call before checking the base case will
  ;; infinite-loop here. Bottom-up saturation handles it fine.
  '[[(ancestor ?a ?d)
     (ancestor ?a ?x)
     [?x :parent ?d]]
    [(ancestor ?a ?d)
     [?a :parent ?d]]])

(deftest left-recursive-rule-terminates
  (let [result (with-timeout
                 #(r/query (family-fb) left-recursive-rules
                           '{:find  [?a ?d]
                             :where [(ancestor ?a ?d)]}))]
    (is (not= ::timeout result))
    (is (contains? result [:alice :eve]))
    (is (contains? result [:alice :bob]))))

(deftest mutual-recursion-terminates
  ;; even / odd path lengths via mutual recursion
  (let [fb (facts/fact-base
            [[:n0 :next :n1]
             [:n1 :next :n2]
             [:n2 :next :n3]
             [:n3 :next :n4]])
        rules '[[(even-path ?a ?b) [?a :next ?b] (odd-step)]
                [(odd-step)] ; trivial fact; unused, here to ensure
                              ; rules with no body work
                [(even-path ?a ?c)
                 [?a :next ?b]
                 (odd-path ?b ?c)]
                [(odd-path ?a ?c)
                 [?a :next ?b]
                 (even-path ?b ?c)]]
        result (with-timeout
                 #(r/query fb rules
                           '{:find  [?a ?b]
                             :where [(even-path ?a ?b)]}))]
    (is (not= ::timeout result))))
