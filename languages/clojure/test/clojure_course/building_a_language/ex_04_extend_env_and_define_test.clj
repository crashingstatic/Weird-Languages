(ns clojure-course.building-a-language.ex-04-extend-env-and-define-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-04-extend-env-and-define :as sut]))

(deftest extend-env-test
  (testing "creates a new frame with bindings"
    (let [env (sut/extend-env () ["x" "y"] [1 2])]
      (is (= 1 (sut/lookup-var env "x")))
      (is (= 2 (sut/lookup-var env "y"))))))

(deftest extend-env-shadows-test
  (testing "new frame shadows outer bindings"
    (let [outer (sut/extend-env () ["x"] [10])
          inner (sut/extend-env outer ["x"] [99])]
      (is (= 99 (sut/lookup-var inner "x"))))))

(deftest extend-env-preserves-outer-test
  (testing "outer bindings remain accessible"
    (let [outer (sut/extend-env () ["x"] [10])
          inner (sut/extend-env outer ["y"] [20])]
      (is (= 10 (sut/lookup-var inner "x")))
      (is (= 20 (sut/lookup-var inner "y"))))))

(deftest define-var-test
  (testing "adds a new binding to the current frame"
    (let [env (sut/extend-env () [] [])]
      (sut/define-var! env "x" 42)
      (is (= 42 (sut/lookup-var env "x"))))))

(deftest define-var-overwrites-test
  (testing "overwrites an existing binding in the current frame"
    (let [env (sut/extend-env () ["x"] [1])]
      (sut/define-var! env "x" 99)
      (is (= 99 (sut/lookup-var env "x"))))))

(deftest define-var-returns-value-test
  (testing "returns the defined value"
    (let [env (sut/extend-env () [] [])]
      (is (= 42 (sut/define-var! env "x" 42))))))
