(ns clojure-course.data-and-abstraction.ex-06-deck-of-cards-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-06-deck-of-cards :as sut]))

(deftest make-deck-test
  (testing "52 cards"
    (is (= 52 (count (sut/make-deck)))))
  (testing "all unique"
    (is (= 52 (count (set (sut/make-deck))))))
  (testing "13 of each suit"
    (let [deck (sut/make-deck)]
      (doseq [suit [:hearts :diamonds :clubs :spades]]
        (is (= 13 (count (filter #(= suit (:suit %)) deck)))))))
  (testing "ranks 1-13 in each suit"
    (let [deck (sut/make-deck)]
      (doseq [suit [:hearts :diamonds :clubs :spades]]
        (is (= (set (range 1 14))
               (set (map :rank (filter #(= suit (:suit %)) deck)))))))))

(deftest shuffle-deck-test
  (testing "same count after shuffle"
    (is (= 52 (count (sut/shuffle-deck (sut/make-deck))))))
  (testing "same cards after shuffle"
    (is (= (set (sut/make-deck))
           (set (sut/shuffle-deck (sut/make-deck)))))))

(deftest deal-test
  (testing "deal 5 from 52"
    (let [deck (sut/make-deck)
          [hand remaining] (sut/deal deck 5)]
      (is (= 5 (count hand)))
      (is (= 47 (count remaining)))))
  (testing "deal 0"
    (let [[hand remaining] (sut/deal (sut/make-deck) 0)]
      (is (= 0 (count hand)))
      (is (= 52 (count remaining)))))
  (testing "hand + remaining = original"
    (let [deck (sut/make-deck)
          [hand remaining] (sut/deal deck 10)]
      (is (= (set deck) (set (concat hand remaining)))))))

(deftest cards-of-suit-test
  (testing "13 hearts"
    (is (= 13 (count (sut/cards-of-suit (sut/make-deck) :hearts)))))
  (testing "all returned cards match suit"
    (let [clubs (sut/cards-of-suit (sut/make-deck) :clubs)]
      (is (every? #(= :clubs (:suit %)) clubs))))
  (testing "empty deck returns empty"
    (is (= [] (sut/cards-of-suit [] :hearts)))))
