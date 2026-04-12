(ns clojure-course.functional-foundations.ex-05-newton-sqrt-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-05-newton-sqrt :as sut]))

(defn- close? [a b]
  (< (Math/abs (- (double a) (double b))) 1e-4))

(defn- within [ms f & args]
  (let [fut (future (apply f args))
        result (deref fut ms ::timeout)]
    (when (= result ::timeout)
      (future-cancel fut)
      (throw (ex-info "test timed out" {:ms ms})))
    result))

(deftest good-enough?-test
  (testing "exact match"
    (is (true? (sut/good-enough? 3.0 9.0))))
  (testing "close enough"
    (is (true? (sut/good-enough? 1.4142136 2.0))))
  (testing "not close"
    (is (false? (sut/good-enough? 2.0 9.0)))))

(deftest improve-test
  (testing "improves toward sqrt of 4"
    (let [improved (sut/improve 1.0 4.0)]
      (is (close? 2.5 improved))))
  (testing "already good guess barely changes"
    (let [improved (sut/improve 2.0 4.0)]
      (is (close? 2.0 improved)))))

(deftest sqrt-test
  (testing "exact squares"
    (is (close? 2.0 (within 2000 sut/sqrt 4)))
    (is (close? 3.0 (within 2000 sut/sqrt 9)))
    (is (close? 4.0 (within 2000 sut/sqrt 16))))
  (testing "non-square positives"
    (is (close? 1.41421356 (within 2000 sut/sqrt 2)))
    (is (close? 1.73205080 (within 2000 sut/sqrt 3))))
  (testing "large input"
    (is (close? 100.0 (within 2000 sut/sqrt 10000)))))
