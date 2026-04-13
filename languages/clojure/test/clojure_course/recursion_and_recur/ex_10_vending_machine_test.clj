(ns clojure-course.recursion-and-recur.ex-10-vending-machine-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-10-vending-machine :as sut]))

(def ^:private initial-state
  {:balance   0
   :inventory {:cola  {:price 150 :qty 3}
               :chips {:price 100 :qty 2}
               :water {:price 75  :qty 5}}
   :dispensed []})

(deftest run-machine-test
  (testing "no commands — state unchanged"
    (is (= initial-state (sut/run-machine initial-state []))))

  (testing "insert increases balance"
    (let [result (sut/run-machine initial-state [[:insert 100]])]
      (is (= 100 (:balance result)))))

  (testing "multiple inserts accumulate"
    (let [result (sut/run-machine initial-state [[:insert 50] [:insert 75]])]
      (is (= 125 (:balance result)))))

  (testing "successful purchase"
    (let [result (sut/run-machine initial-state
                   [[:insert 200] [:select :cola]])]
      (is (= 50 (:balance result)))
      (is (= [:cola] (:dispensed result)))
      (is (= 2 (get-in result [:inventory :cola :qty])))))

  (testing "insufficient balance — no purchase"
    (let [result (sut/run-machine initial-state
                   [[:insert 50] [:select :cola]])]
      (is (= 50 (:balance result)))
      (is (= [] (:dispensed result)))
      (is (= 3 (get-in result [:inventory :cola :qty])))))

  (testing "out of stock — no purchase"
    (let [result (sut/run-machine
                   (assoc-in initial-state [:inventory :chips :qty] 0)
                   [[:insert 200] [:select :chips]])]
      (is (= 200 (:balance result)))
      (is (= [] (:dispensed result)))))

  (testing "unknown item — no purchase"
    (let [result (sut/run-machine initial-state
                   [[:insert 200] [:select :candy]])]
      (is (= 200 (:balance result)))
      (is (= [] (:dispensed result)))))

  (testing "refund resets balance"
    (let [result (sut/run-machine initial-state
                   [[:insert 200] [:refund]])]
      (is (= 0 (:balance result)))))

  (testing "full scenario: insert, buy, buy, refund"
    (let [result (sut/run-machine initial-state
                   [[:insert 100]
                    [:insert 100]
                    [:insert 100]
                    [:select :cola]     ;; 300 - 150 = 150
                    [:select :water]    ;; 150 - 75 = 75
                    [:refund]])]
      (is (= 0 (:balance result)))
      (is (= [:cola :water] (:dispensed result)))
      (is (= 2 (get-in result [:inventory :cola :qty])))
      (is (= 4 (get-in result [:inventory :water :qty])))))

  (testing "buy until out of stock"
    (let [result (sut/run-machine initial-state
                   [[:insert 1000]
                    [:select :chips]    ;; qty 2 -> 1
                    [:select :chips]    ;; qty 1 -> 0
                    [:select :chips]])] ;; out of stock, ignored
      (is (= 800 (:balance result)))
      (is (= [:chips :chips] (:dispensed result)))
      (is (= 0 (get-in result [:inventory :chips :qty]))))))
