(ns clojure-course.polymorphism-and-protocols.ex-06-when-to-use-which-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-06-when-to-use-which :as sut]))

(deftest text-render-test
  (testing "text renders as paragraph"
    (is (= "<p>Hello world</p>" (sut/render (sut/->Text "Hello world"))))
    (is (= "<p></p>" (sut/render (sut/->Text ""))))))

(deftest image-render-test
  (testing "image renders as img tag"
    (is (= "<img src=\"pic.jpg\" alt=\"A picture\">"
           (sut/render (sut/->Image "pic.jpg" "A picture"))))))

(deftest link-render-test
  (testing "link renders as anchor tag"
    (is (= "<a href=\"http://example.com\">Click here</a>"
           (sut/render (sut/->Link "http://example.com" "Click here"))))))

(deftest protocol-test
  (testing "all records satisfy the protocol"
    (is (satisfies? sut/Renderable (sut/->Text "x")))
    (is (satisfies? sut/Renderable (sut/->Image "x" "y")))
    (is (satisfies? sut/Renderable (sut/->Link "x" "y")))))
