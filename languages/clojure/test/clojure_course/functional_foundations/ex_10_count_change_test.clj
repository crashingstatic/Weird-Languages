(ns clojure-course.functional-foundations.ex-10-count-change-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-10-count-change :as sut]))

(defn- within [ms f & args]
  (let [fut (future (apply f args))
        result (deref fut ms ::timeout)]
    (when (= result ::timeout)
      (future-cancel fut)
      (throw (ex-info "test timed out" {:ms ms})))
    result))

(deftest count-change-test
  (testing "zero cents"
    (is (= 1 (sut/count-change 0))))
  (testing "one cent — only one way"
    (is (= 1 (sut/count-change 1))))
  (testing "five cents — two ways (5 or 1+1+1+1+1)"
    (is (= 2 (sut/count-change 5))))
  (testing "ten cents — four ways"
    (is (= 4 (sut/count-change 10))))
  (testing "twenty-five cents"
    (is (= 13 (sut/count-change 25))))
  (testing "SICP classic: one dollar = 292 ways"
    (is (= 292 (within 5000 sut/count-change 100))))
  (testing "negative amount"
    (is (= 0 (sut/count-change -1)))))
