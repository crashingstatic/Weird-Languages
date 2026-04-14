(ns clojure-course.macros-and-code-as-data.ex-06-with-timing-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-06-with-timing :as sut]))

(deftest returns-result-test
  (testing "returns the body's result"
    (is (= 42 (:result (sut/with-timing 42))))
    (is (= 6 (:result (sut/with-timing (+ 1 2 3)))))))

(deftest has-elapsed-ms-test
  (testing "result contains :elapsed-ms"
    (let [r (sut/with-timing (+ 1 1))]
      (is (contains? r :elapsed-ms))
      (is (number? (:elapsed-ms r)))
      (is (>= (:elapsed-ms r) 0)))))

(deftest measures-time-test
  (testing "measures actual elapsed time"
    (let [r (sut/with-timing (Thread/sleep 50))]
      (is (nil? (:result r)))
      (is (>= (:elapsed-ms r) 40)))))

(deftest multiple-body-forms-test
  (testing "evaluates multiple body forms"
    (let [a (atom 0)
          r (sut/with-timing
              (swap! a inc)
              (swap! a inc)
              @a)]
      (is (= 2 (:result r))))))

(deftest no-variable-capture-test
  (testing "does not capture user variables named start or result"
    (let [start 999
          result 888
          r (sut/with-timing (+ start result))]
      (is (= 1887 (:result r))))))
