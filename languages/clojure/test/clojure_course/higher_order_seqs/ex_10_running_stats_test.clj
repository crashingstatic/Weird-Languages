(ns clojure-course.higher-order-seqs.ex-10-running-stats-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-10-running-stats :as sut]))

(defn- close? [a b]
  (< (Math/abs (- (double a) (double b))) 1e-6))

(deftest running-stats-test
  (testing "empty input"
    (is (= () (sut/running-stats []))))
  (testing "single element"
    (let [result (first (sut/running-stats [5]))]
      (is (= 1 (:count result)))
      (is (= 5 (:sum result)))
      (is (close? 5.0 (:mean result)))))
  (testing "three elements"
    (let [[r1 r2 r3] (sut/running-stats [10 20 30])]
      (is (= 1 (:count r1)))
      (is (= 10 (:sum r1)))
      (is (close? 10.0 (:mean r1)))
      (is (= 2 (:count r2)))
      (is (= 30 (:sum r2)))
      (is (close? 15.0 (:mean r2)))
      (is (= 3 (:count r3)))
      (is (= 60 (:sum r3)))
      (is (close? 20.0 (:mean r3)))))
  (testing "length matches input"
    (is (= 5 (count (sut/running-stats [1 2 3 4 5])))))
  (testing "negative numbers"
    (let [[r1 r2] (sut/running-stats [-10 10])]
      (is (= -10 (:sum r1)))
      (is (= 0 (:sum r2)))
      (is (close? 0.0 (:mean r2)))))
  (testing "fractional mean"
    (let [[_ r2] (sut/running-stats [1 2])]
      (is (close? 1.5 (:mean r2))))))
