(ns clojure-course.macros-and-code-as-data.ex-08-log-context-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.macros-and-code-as-data.ex-08-log-context :as sut]))

(deftest empty-context-test
  (testing "default context is empty"
    (is (= {} (sut/get-context)))))

(deftest basic-context-test
  (testing "with-log-context adds to the context"
    (sut/with-log-context {:request-id "abc"}
      (is (= {:request-id "abc"} (sut/get-context))))))

(deftest nested-context-test
  (testing "nested contexts merge"
    (sut/with-log-context {:request-id "abc"}
      (sut/with-log-context {:user "alice"}
        (is (= {:request-id "abc" :user "alice"} (sut/get-context))))
      (is (= {:request-id "abc"} (sut/get-context))))))

(deftest context-restored-after-exception-test
  (testing "context is restored even after exception"
    (try
      (sut/with-log-context {:temp "value"}
        (throw (Exception. "boom")))
      (catch Exception _))
    (is (= {} (sut/get-context)))))

(deftest returns-body-value-test
  (testing "returns the body's value"
    (is (= 42 (sut/with-log-context {:k "v"} 42)))))
