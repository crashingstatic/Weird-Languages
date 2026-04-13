(ns clojure-course.data-and-abstraction.ex-08-binary-tree-of-data-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.data-and-abstraction.ex-08-binary-tree-of-data :as sut]))

(deftest bst-insert-test
  (testing "insert into empty tree"
    (let [t (sut/bst-insert nil 5)]
      (is (= 5 (:value t)))
      (is (nil? (:left t)))
      (is (nil? (:right t)))))
  (testing "insert smaller goes left"
    (let [t (-> nil (sut/bst-insert 5) (sut/bst-insert 3))]
      (is (= 3 (get-in t [:left :value])))))
  (testing "insert larger goes right"
    (let [t (-> nil (sut/bst-insert 5) (sut/bst-insert 7))]
      (is (= 7 (get-in t [:right :value])))))
  (testing "duplicate ignored"
    (let [t (-> nil (sut/bst-insert 5) (sut/bst-insert 5))]
      (is (nil? (:left t)))
      (is (nil? (:right t))))))

(deftest bst-contains-test
  (testing "empty tree"
    (is (false? (sut/bst-contains? nil 5))))
  (testing "root match"
    (is (true? (sut/bst-contains? (sut/bst-insert nil 5) 5))))
  (testing "deep match"
    (let [t (reduce sut/bst-insert nil [5 3 7 1 4])]
      (is (true? (sut/bst-contains? t 1)))
      (is (true? (sut/bst-contains? t 4)))
      (is (true? (sut/bst-contains? t 7)))))
  (testing "not found"
    (let [t (reduce sut/bst-insert nil [5 3 7])]
      (is (false? (sut/bst-contains? t 2)))
      (is (false? (sut/bst-contains? t 10))))))

(deftest bst-in-order-test
  (testing "empty tree"
    (is (= () (sut/bst-in-order nil))))
  (testing "single element"
    (is (= '(5) (seq (sut/bst-in-order (sut/bst-insert nil 5))))))
  (testing "sorted output"
    (let [t (reduce sut/bst-insert nil [5 3 7 1 4 6 8])]
      (is (= '(1 3 4 5 6 7 8) (seq (sut/bst-in-order t))))))
  (testing "reverse insertion still sorted"
    (let [t (reduce sut/bst-insert nil [7 6 5 4 3 2 1])]
      (is (= '(1 2 3 4 5 6 7) (seq (sut/bst-in-order t))))))
  (testing "duplicates don't appear twice"
    (let [t (reduce sut/bst-insert nil [3 1 3 2 1])]
      (is (= '(1 2 3) (seq (sut/bst-in-order t)))))))
