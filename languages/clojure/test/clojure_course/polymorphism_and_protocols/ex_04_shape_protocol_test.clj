(ns clojure-course.polymorphism-and-protocols.ex-04-shape-protocol-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-04-shape-protocol :as sut]))

(defn- approx= [expected actual]
  (< (Math/abs (- expected actual)) 0.001))

(deftest circle-test
  (testing "circle area and perimeter"
    (let [c (sut/->Circle 5)]
      (is (approx= 78.5398 (sut/area c)))
      (is (approx= 31.4159 (sut/perimeter c)))))
  (testing "unit circle"
    (let [c (sut/->Circle 1)]
      (is (approx= Math/PI (sut/area c)))
      (is (approx= (* 2 Math/PI) (sut/perimeter c))))))

(deftest rectangle-test
  (testing "rectangle area and perimeter"
    (let [r (sut/->Rectangle 3 4)]
      (is (= 12 (sut/area r)))
      (is (= 14 (sut/perimeter r)))))
  (testing "square"
    (let [r (sut/->Rectangle 5 5)]
      (is (= 25 (sut/area r)))
      (is (= 20 (sut/perimeter r))))))

(deftest triangle-test
  (testing "3-4-5 right triangle"
    (let [t (sut/->Triangle 3 4 5)]
      (is (approx= 6.0 (sut/area t)))
      (is (= 12 (sut/perimeter t)))))
  (testing "equilateral triangle"
    (let [t (sut/->Triangle 6 6 6)]
      (is (approx= 15.5885 (sut/area t)))
      (is (= 18 (sut/perimeter t))))))

(deftest composite-test
  (testing "composite shape sums areas and perimeters"
    (let [c (sut/->Circle 1)
          r (sut/->Rectangle 3 4)
          comp (sut/make-composite [c r])]
      (is (approx= (+ Math/PI 12) (sut/area comp)))
      (is (approx= (+ (* 2 Math/PI) 14) (sut/perimeter comp)))))
  (testing "empty composite"
    (let [comp (sut/make-composite [])]
      (is (= 0 (sut/area comp)))
      (is (= 0 (sut/perimeter comp))))))

(deftest satisfies-protocol-test
  (testing "all types satisfy Shape"
    (is (satisfies? sut/Shape (sut/->Circle 1)))
    (is (satisfies? sut/Shape (sut/->Rectangle 1 1)))
    (is (satisfies? sut/Shape (sut/->Triangle 3 4 5)))
    (is (satisfies? sut/Shape (sut/make-composite [])))))
