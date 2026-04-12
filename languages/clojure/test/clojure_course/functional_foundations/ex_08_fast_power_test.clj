(ns clojure-course.functional-foundations.ex-08-fast-power-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-08-fast-power :as sut]))

(defn- within [ms f & args]
  (let [fut (future (apply f args))
        result (deref fut ms ::timeout)]
    (when (= result ::timeout)
      (future-cancel fut)
      (throw (ex-info "test timed out" {:ms ms})))
    result))

(deftest fast-power-test
  (testing "base cases"
    (is (= 1 (sut/fast-power 5 0)))
    (is (= 1 (sut/fast-power 0 0))))
  (testing "small inputs"
    (is (= 8 (sut/fast-power 2 3)))
    (is (= 27 (sut/fast-power 3 3)))
    (is (= 1024 (sut/fast-power 2 10))))
  (testing "base zero"
    (is (= 0 (sut/fast-power 0 5))))
  (testing "base one"
    (is (= 1 (sut/fast-power 1 1000000))))
  (testing "negative base"
    (is (= -8 (sut/fast-power -2 3)))
    (is (= 16 (sut/fast-power -2 4))))
  (testing "large exponent completes quickly (O(log n) required)"
    (is (some? (within 3000 sut/fast-power 2 1000000)))))
