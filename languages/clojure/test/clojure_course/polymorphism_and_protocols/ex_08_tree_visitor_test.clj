(ns clojure-course.polymorphism-and-protocols.ex-08-tree-visitor-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-08-tree-visitor :as sut]))

(deftest evaluate-num-test
  (testing "number evaluates to its value"
    (is (= 5 (sut/evaluate (sut/->Num 5))))
    (is (= 0 (sut/evaluate (sut/->Num 0))))))

(deftest evaluate-add-test
  (testing "add evaluates to sum"
    (is (= 8 (sut/evaluate (sut/->Add (sut/->Num 5) (sut/->Num 3)))))))

(deftest evaluate-mul-test
  (testing "mul evaluates to product"
    (is (= 15 (sut/evaluate (sut/->Mul (sut/->Num 5) (sut/->Num 3)))))))

(deftest evaluate-nested-test
  (testing "(2 + 3) * 4 = 20"
    (is (= 20 (sut/evaluate (sut/->Mul (sut/->Add (sut/->Num 2) (sut/->Num 3))
                                        (sut/->Num 4))))))
  (testing "2 * (3 + 4) = 14"
    (is (= 14 (sut/evaluate (sut/->Mul (sut/->Num 2)
                                        (sut/->Add (sut/->Num 3) (sut/->Num 4))))))))

(deftest visit-num-test
  (testing "visiting a number produces no ops"
    (is (= {:result 5 :ops []} (sut/visit (sut/->Num 5))))))

(deftest visit-add-test
  (testing "visiting an add logs one add op"
    (is (= {:result 8 :ops ["add"]}
           (sut/visit (sut/->Add (sut/->Num 5) (sut/->Num 3)))))))

(deftest visit-mul-test
  (testing "visiting a mul logs one mul op"
    (is (= {:result 15 :ops ["mul"]}
           (sut/visit (sut/->Mul (sut/->Num 5) (sut/->Num 3)))))))

(deftest visit-nested-test
  (testing "(2 + 3) * 4 logs add then mul"
    (let [result (sut/visit (sut/->Mul (sut/->Add (sut/->Num 2) (sut/->Num 3))
                                        (sut/->Num 4)))]
      (is (= 20 (:result result)))
      (is (= ["add" "mul"] (:ops result)))))
  (testing "(1 + 2) * (3 + 4) logs add, add, mul"
    (let [result (sut/visit (sut/->Mul (sut/->Add (sut/->Num 1) (sut/->Num 2))
                                        (sut/->Add (sut/->Num 3) (sut/->Num 4))))]
      (is (= 21 (:result result)))
      (is (= ["add" "add" "mul"] (:ops result))))))
