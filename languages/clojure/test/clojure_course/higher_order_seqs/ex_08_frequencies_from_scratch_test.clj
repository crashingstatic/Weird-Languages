(ns clojure-course.higher-order-seqs.ex-08-frequencies-from-scratch-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.higher-order-seqs.ex-08-frequencies-from-scratch :as sut]))

(deftest my-frequencies-test
  (testing "empty collection"
    (is (= {} (sut/my-frequencies []))))
  (testing "single element"
    (is (= {42 1} (sut/my-frequencies [42]))))
  (testing "all same"
    (is (= {:a 4} (sut/my-frequencies [:a :a :a :a]))))
  (testing "all distinct"
    (is (= {1 1 2 1 3 1} (sut/my-frequencies [1 2 3]))))
  (testing "mixed keywords"
    (is (= {:a 3 :b 2 :c 1} (sut/my-frequencies [:a :b :a :c :b :a]))))
  (testing "strings"
    (is (= {"hi" 2 "bye" 1} (sut/my-frequencies ["hi" "bye" "hi"]))))
  (testing "longer input with ties"
    (let [input (concat (repeat 50 :x) (repeat 50 :y) (repeat 25 :z))
          result (sut/my-frequencies input)]
      (is (= 50 (:x result)))
      (is (= 50 (:y result)))
      (is (= 25 (:z result))))))
