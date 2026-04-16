(ns clojure-course.building-a-language.ex-03-env-lookup-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.building-a-language.ex-03-env-lookup :as sut]))

(deftest lookup-single-frame-test
  (testing "finds variable in a single frame"
    (let [env (list (atom {"x" 42}))]
      (is (= 42 (sut/lookup-var env "x"))))))

(deftest lookup-inner-frame-test
  (testing "finds variable in the innermost frame first"
    (let [env (list (atom {"x" 10})
                    (atom {"x" 99}))]
      (is (= 10 (sut/lookup-var env "x"))))))

(deftest lookup-outer-frame-test
  (testing "walks to outer frame when not in inner"
    (let [env (list (atom {"x" 1})
                    (atom {"y" 2}))]
      (is (= 2 (sut/lookup-var env "y"))))))

(deftest lookup-multiple-frames-test
  (testing "walks through multiple frames"
    (let [env (list (atom {"a" 1})
                    (atom {"b" 2})
                    (atom {"c" 3}))]
      (is (= 3 (sut/lookup-var env "c"))))))

(deftest lookup-unbound-throws-test
  (testing "throws for unbound variable"
    (let [env (list (atom {"x" 1}))]
      (is (thrown-with-msg? clojure.lang.ExceptionInfo
                            #"Unbound variable"
                            (sut/lookup-var env "z"))))))

(deftest lookup-empty-env-throws-test
  (testing "throws for empty environment"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo
                          #"Unbound variable"
                          (sut/lookup-var () "x")))))
