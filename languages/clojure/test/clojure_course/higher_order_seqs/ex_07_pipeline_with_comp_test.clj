(ns clojure-course.higher-order-seqs.ex-07-pipeline-with-comp-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-07-pipeline-with-comp :as sut]))

(def ^:private customers
  [{:name "Alice"  :region "west"  :active true  :orders [{:total 120} {:total 80}]}
   {:name "Bob"    :region "east"  :active true  :orders [{:total 50}]}
   {:name "Carol"  :region "west"  :active false :orders [{:total 200}]}
   {:name "Dave"   :region "west"  :active true  :orders [{:total 30} {:total 70}]}
   {:name "Eve"    :region "east"  :active true  :orders [{:total 90} {:total 10}]}
   {:name "Frank"  :region "north" :active true  :orders [{:total 500}]}])

(deftest active-order-total-test
  (testing "west region — Alice + Dave, not Carol (inactive)"
    (is (= 300 (sut/active-order-total "west" customers))))
  (testing "east region — Bob + Eve"
    (is (= 150 (sut/active-order-total "east" customers))))
  (testing "north region — Frank only"
    (is (= 500 (sut/active-order-total "north" customers))))
  (testing "nonexistent region"
    (is (= 0 (sut/active-order-total "south" customers))))
  (testing "empty customer list"
    (is (= 0 (sut/active-order-total "west" []))))
  (testing "customer with no orders"
    (is (= 0 (sut/active-order-total "west"
               [{:name "Ghost" :region "west" :active true :orders []}])))))
