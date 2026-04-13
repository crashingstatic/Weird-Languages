(ns clojure-course.recursion-and-recur.ex-08-count-occurrences-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.recursion-and-recur.ex-08-count-occurrences :as sut]))

(deftest count-occurrences-test
  (testing "empty collection"
    (is (= 0 (sut/count-occurrences :a []))))
  (testing "not found"
    (is (= 0 (sut/count-occurrences :x [:a :b :c]))))
  (testing "flat — one occurrence"
    (is (= 1 (sut/count-occurrences :b [:a :b :c]))))
  (testing "flat — multiple occurrences"
    (is (= 3 (sut/count-occurrences 1 [1 2 1 3 1]))))
  (testing "nested — several levels"
    (is (= 3 (sut/count-occurrences :a [:a :b [:a :c [:a]]]))))
  (testing "deeply nested single target"
    (is (= 1 (sut/count-occurrences 42 [[[[[42]]]]]))))
  (testing "nested with empty branches"
    (is (= 2 (sut/count-occurrences :x [:x [] [:x [] []]]))))
  (testing "numbers"
    (is (= 4 (sut/count-occurrences 0 [0 [0] [[0] 1 [0 2]]]))))
  (testing "string target"
    (is (= 2 (sut/count-occurrences "hi" ["hi" ["bye" ["hi"]]])))))
