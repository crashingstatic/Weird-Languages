(ns clojure-course.state-identity-concurrency.ex-09-agent-counter-ring-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-09-agent-counter-ring :as sut]))

(deftest basic-ring-test
  (testing "token visits each agent and increments counter"
    (let [counter (atom 0)
          ring (sut/make-ring 3 counter)]
      (sut/start-token ring 3)
      (is (= 3 @counter))))
  (testing "token goes around multiple times"
    (let [counter (atom 0)
          ring (sut/make-ring 3 counter)]
      (sut/start-token ring 9)
      (is (= 9 @counter)))))

(deftest single-agent-ring-test
  (testing "single agent ring"
    (let [counter (atom 0)
          ring (sut/make-ring 1 counter)]
      (sut/start-token ring 5)
      (is (= 5 @counter)))))

(deftest zero-hops-test
  (testing "zero hops — counter unchanged"
    (let [counter (atom 0)
          ring (sut/make-ring 3 counter)]
      (sut/start-token ring 0)
      (is (= 0 @counter)))))

(deftest larger-ring-test
  (testing "10-agent ring with 100 hops"
    (let [counter (atom 0)
          ring (sut/make-ring 10 counter)]
      (sut/start-token ring 100)
      (is (= 100 @counter)))))
