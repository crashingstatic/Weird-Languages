(ns clojure-course.polymorphism-and-protocols.ex-01-multimethod-shape-area-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-01-multimethod-shape-area :as sut]))

(deftest circle-area-test
  (testing "circle area uses pi*r^2"
    (is (> (sut/area {:shape :circle :radius 1}) 3.14))
    (is (< (sut/area {:shape :circle :radius 1}) 3.15))
    (is (> (sut/area {:shape :circle :radius 5}) 78.5))))

(deftest rectangle-area-test
  (testing "rectangle area uses width*height"
    (is (= 12 (sut/area {:shape :rectangle :width 3 :height 4})))
    (is (= 25 (sut/area {:shape :rectangle :width 5 :height 5})))))

(deftest triangle-area-test
  (testing "triangle area uses 0.5*base*height"
    (is (= 6.0 (sut/area {:shape :triangle :base 3 :height 4})))
    (is (= 25.0 (sut/area {:shape :triangle :base 10 :height 5})))
    (is (= 0.5 (sut/area {:shape :triangle :base 1 :height 1})))))
