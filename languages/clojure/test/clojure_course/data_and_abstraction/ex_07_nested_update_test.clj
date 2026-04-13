(ns clojure-course.data-and-abstraction.ex-07-nested-update-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-07-nested-update :as sut]))

(def ^:private data
  {:red  {:alice {:goals 3 :assists 5}
          :bob   {:goals 1 :assists 2}}
   :blue {:carol {:goals 7 :assists 1}}})

(deftest get-stat-test
  (testing "existing stat"
    (is (= 3 (sut/get-stat data :red :alice :goals))))
  (testing "different stat"
    (is (= 1 (sut/get-stat data :blue :carol :assists))))
  (testing "missing player returns nil"
    (is (nil? (sut/get-stat data :red :eve :goals)))))

(deftest inc-stat-test
  (testing "increment goals"
    (is (= 4 (sut/get-stat (sut/inc-stat data :red :alice :goals)
                            :red :alice :goals))))
  (testing "increment assists"
    (is (= 3 (sut/get-stat (sut/inc-stat data :red :bob :assists)
                            :red :bob :assists))))
  (testing "other stats unchanged"
    (let [updated (sut/inc-stat data :red :alice :goals)]
      (is (= 5 (sut/get-stat updated :red :alice :assists)))
      (is (= 1 (sut/get-stat updated :red :bob :goals)))))
  (testing "other teams unchanged"
    (let [updated (sut/inc-stat data :red :alice :goals)]
      (is (= 7 (sut/get-stat updated :blue :carol :goals))))))

(deftest add-player-test
  (testing "new player has zero stats"
    (let [updated (sut/add-player data :red :eve)]
      (is (= 0 (sut/get-stat updated :red :eve :goals)))
      (is (= 0 (sut/get-stat updated :red :eve :assists)))))
  (testing "existing players unchanged"
    (let [updated (sut/add-player data :blue :dave)]
      (is (= 7 (sut/get-stat updated :blue :carol :goals)))))
  (testing "add to new team"
    (let [updated (sut/add-player data :green :frank)]
      (is (= 0 (sut/get-stat updated :green :frank :goals))))))
