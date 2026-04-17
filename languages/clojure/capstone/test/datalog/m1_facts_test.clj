(ns datalog.m1-facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [datalog.facts :as facts]))

(deftest empty-fact-base
  (is (empty? (facts/all-facts (facts/fact-base)))))

(deftest seeded-fact-base
  (let [fb (facts/fact-base [[:alice :name "Alice"]
                             [:bob   :name "Bob"]])]
    (is (= 2 (count (facts/all-facts fb))))))

(deftest add-fact-roundtrip
  (let [fb (-> (facts/fact-base)
               (facts/add-fact [:alice :name "Alice"])
               (facts/add-fact [:alice :age 30]))]
    (is (= #{[:alice :name "Alice"]
             [:alice :age 30]}
           (set (facts/all-facts fb))))))

(deftest add-facts-bulk
  (let [fb (facts/add-facts (facts/fact-base)
                            [[:a :rel :b]
                             [:b :rel :c]
                             [:c :rel :d]])]
    (is (= 3 (count (facts/all-facts fb))))))

(deftest duplicates-are-deduplicated
  (let [fb (-> (facts/fact-base)
               (facts/add-fact [:alice :name "Alice"])
               (facts/add-fact [:alice :name "Alice"])
               (facts/add-fact [:alice :name "Alice"]))]
    (is (= 1 (count (facts/all-facts fb))))))

(deftest remove-fact-works
  (let [fb (-> (facts/fact-base)
               (facts/add-fact [:alice :name "Alice"])
               (facts/add-fact [:bob   :name "Bob"])
               (facts/remove-fact [:alice :name "Alice"]))]
    (is (= [[:bob :name "Bob"]] (facts/all-facts fb)))))

(deftest remove-missing-fact-is-noop
  (let [fb (-> (facts/fact-base)
               (facts/add-fact [:alice :name "Alice"])
               (facts/remove-fact [:nonexistent :foo :bar]))]
    (is (= 1 (count (facts/all-facts fb))))))
