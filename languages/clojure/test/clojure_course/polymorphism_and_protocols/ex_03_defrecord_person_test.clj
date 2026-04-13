(ns clojure-course.polymorphism-and-protocols.ex-03-defrecord-person-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-03-defrecord-person :as sut]))

(deftest employee-describe-test
  (testing "Employee describe format"
    (is (= "Alice is a Engineer earning $120000"
           (sut/describe (sut/->Employee "Alice" "Engineer" 120000))))
    (is (= "Bob is a Manager earning $95000"
           (sut/describe (sut/->Employee "Bob" "Manager" 95000))))))

(deftest product-describe-test
  (testing "Product describe format"
    (is (= "Widget costs $9.99"
           (sut/describe (sut/->Product "Widget" 9.99))))
    (is (= "Gizmo costs $42"
           (sut/describe (sut/->Product "Gizmo" 42))))))

(deftest records-satisfy-protocol-test
  (testing "both records satisfy Describable"
    (is (satisfies? sut/Describable (sut/->Employee "x" "y" 0)))
    (is (satisfies? sut/Describable (sut/->Product "x" 0)))))

(deftest record-fields-accessible-test
  (testing "Employee fields accessible as keywords"
    (let [e (sut/->Employee "Alice" "Engineer" 120000)]
      (is (= "Alice" (:name e)))
      (is (= "Engineer" (:role e)))
      (is (= 120000 (:salary e))))))
