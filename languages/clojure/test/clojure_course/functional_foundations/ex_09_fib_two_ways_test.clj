(ns clojure-course.functional-foundations.ex-09-fib-two-ways-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-09-fib-two-ways :as sut]))

(defn- within [ms f & args]
  (let [fut (future (apply f args))
        result (deref fut ms ::timeout)]
    (when (= result ::timeout)
      (future-cancel fut)
      (throw (ex-info "test timed out" {:ms ms})))
    result))

(deftest fib-tree-test
  (testing "base cases"
    (is (= 0 (sut/fib-tree 0)))
    (is (= 1 (sut/fib-tree 1))))
  (testing "small values"
    (is (= 1 (sut/fib-tree 2)))
    (is (= 2 (sut/fib-tree 3)))
    (is (= 5 (sut/fib-tree 5)))
    (is (= 8 (sut/fib-tree 6)))
    (is (= 55 (sut/fib-tree 10)))))

(deftest fib-iter-test
  (testing "base cases"
    (is (= 0 (sut/fib-iter 0)))
    (is (= 1 (sut/fib-iter 1))))
  (testing "small values match fib-tree"
    (is (= 1 (sut/fib-iter 2)))
    (is (= 2 (sut/fib-iter 3)))
    (is (= 5 (sut/fib-iter 5)))
    (is (= 55 (sut/fib-iter 10))))
  (testing "medium values"
    (is (= 6765 (sut/fib-iter 20)))
    (is (= 832040 (sut/fib-iter 30))))
  (testing "large n completes quickly (linear process required)"
    (is (= 23416728348467685N
           (within 3000 sut/fib-iter 80)))))
