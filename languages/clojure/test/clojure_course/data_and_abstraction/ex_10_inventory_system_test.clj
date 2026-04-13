(ns clojure-course.data-and-abstraction.ex-10-inventory-system-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-10-inventory-system :as sut]))

(def ^:private stocked
  (-> sut/empty-inventory
      (sut/add-stock :widget 500 10 "Acme")
      (sut/add-stock :gadget 1200 3 "Globex")
      (sut/add-stock :doohickey 250 20 "Initech")))

(deftest add-stock-test
  (testing "add new item"
    (let [inv (sut/add-stock sut/empty-inventory :widget 500 10 "Acme")]
      (is (= 5000 (sut/total-value inv)))))
  (testing "add more stock to existing item"
    (let [inv (sut/add-stock stocked :widget 500 5 "Acme")]
      (is (= (+ (* 500 15) (* 1200 3) (* 250 20))
             (sut/total-value inv))))))

(deftest sell-item-test
  (testing "sell decrements quantity"
    (let [inv (sut/sell-item stocked :widget)]
      (is (= (+ (* 500 9) (* 1200 3) (* 250 20))
             (sut/total-value inv)))))
  (testing "sell missing item — no change"
    (is (= (sut/total-value stocked)
           (sut/total-value (sut/sell-item stocked :nonexistent)))))
  (testing "sell out-of-stock item — no change"
    (let [inv (reduce (fn [i _] (sut/sell-item i :gadget))
                      stocked (range 3))]
      ;; gadget qty is now 0
      (is (= (sut/total-value inv)
             (sut/total-value (sut/sell-item inv :gadget)))))))

(deftest low-stock-items-test
  (testing "threshold 5"
    (is (= #{:gadget} (sut/low-stock-items stocked 5))))
  (testing "threshold 10"
    (is (= #{:widget :gadget} (sut/low-stock-items stocked 10))))
  (testing "threshold 0 — none below"
    (is (= #{} (sut/low-stock-items stocked 0))))
  (testing "threshold 100 — all below"
    (is (= #{:widget :gadget :doohickey}
           (sut/low-stock-items stocked 100)))))

(deftest total-value-test
  (testing "empty inventory"
    (is (= 0 (sut/total-value sut/empty-inventory))))
  (testing "stocked inventory"
    ;; 500*10 + 1200*3 + 250*20 = 5000 + 3600 + 5000 = 13600
    (is (= 13600 (sut/total-value stocked))))
  (testing "after selling"
    (let [inv (-> stocked
                  (sut/sell-item :widget)
                  (sut/sell-item :gadget))]
      ;; 500*9 + 1200*2 + 250*20 = 4500 + 2400 + 5000 = 11900
      (is (= 11900 (sut/total-value inv))))))
