(ns clojure-course.macros-and-code-as-data.ex-09-state-machine-dsl-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-09-state-machine-dsl :as sut]))

(sut/defstate-machine turnstile
  {:locked   {:coin :unlocked}
   :unlocked {:push :locked}})

(sut/defstate-machine traffic-light
  {:red    {:next :green}
   :green  {:next :yellow}
   :yellow {:next :red}})

(deftest turnstile-test
  (testing "basic transitions"
    (is (= :unlocked (turnstile :locked [:coin])))
    (is (= :locked (turnstile :unlocked [:push]))))
  (testing "sequence of events"
    (is (= :locked (turnstile :locked [:coin :push])))
    (is (= :unlocked (turnstile :locked [:coin :push :coin]))))
  (testing "unknown event stays in same state"
    (is (= :locked (turnstile :locked [:push])))))

(deftest traffic-light-test
  (testing "full cycle"
    (is (= :green (traffic-light :red [:next])))
    (is (= :yellow (traffic-light :red [:next :next])))
    (is (= :red (traffic-light :red [:next :next :next]))))
  (testing "no events"
    (is (= :red (traffic-light :red [])))))

(deftest empty-events-test
  (testing "empty events returns initial state"
    (is (= :locked (turnstile :locked [])))))
