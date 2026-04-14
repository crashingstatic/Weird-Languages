(ns clojure-course.state-identity-concurrency.ex-03-agent-logger-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-03-agent-logger :as sut]))

(deftest make-logger-test
  (testing "new logger starts empty"
    (is (= [] (sut/get-logs (sut/make-logger))))))

(deftest log-message-test
  (testing "log-message appends to the log"
    (let [logger (sut/make-logger)]
      (sut/log-message logger "hello")
      (await logger)
      (is (= ["hello"] (sut/get-logs logger)))))
  (testing "multiple messages in order"
    (let [logger (sut/make-logger)]
      (sut/log-message logger "a")
      (sut/log-message logger "b")
      (sut/log-message logger "c")
      (await logger)
      (is (= ["a" "b" "c"] (sut/get-logs logger))))))

(deftest independent-loggers-test
  (testing "loggers are independent"
    (let [l1 (sut/make-logger)
          l2 (sut/make-logger)]
      (sut/log-message l1 "x")
      (sut/log-message l2 "y")
      (await l1)
      (await l2)
      (is (= ["x"] (sut/get-logs l1)))
      (is (= ["y"] (sut/get-logs l2))))))
