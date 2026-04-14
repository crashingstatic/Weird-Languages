(ns clojure-course.state-identity-concurrency.ex-07-memoize-with-atom-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-07-memoize-with-atom :as sut]))

(deftest basic-memoize-test
  (testing "returns correct results"
    (let [f (sut/memoize-with-atom +)]
      (is (= 3 (f 1 2)))
      (is (= 7 (f 3 4)))))
  (testing "caches results — function called only once per unique args"
    (let [call-count (atom 0)
          f (sut/memoize-with-atom
              (fn [x]
                (swap! call-count inc)
                (* x x)))]
      (is (= 4 (f 2)))
      (is (= 4 (f 2)))
      (is (= 4 (f 2)))
      (is (= 1 @call-count))
      (is (= 9 (f 3)))
      (is (= 2 @call-count)))))

(deftest nil-result-test
  (testing "caches nil results correctly"
    (let [call-count (atom 0)
          f (sut/memoize-with-atom
              (fn [_]
                (swap! call-count inc)
                nil))]
      (is (nil? (f :x)))
      (is (nil? (f :x)))
      (is (= 1 @call-count)))))

(deftest multi-arity-test
  (testing "different arities are cached separately"
    (let [f (sut/memoize-with-atom (fn [& args] (apply + args)))]
      (is (= 1 (f 1)))
      (is (= 3 (f 1 2)))
      (is (= 6 (f 1 2 3))))))
