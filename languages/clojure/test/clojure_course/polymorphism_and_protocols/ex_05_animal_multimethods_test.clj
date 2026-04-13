(ns clojure-course.polymorphism-and-protocols.ex-05-animal-multimethods-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.polymorphism-and-protocols.ex-05-animal-multimethods :as sut]))

(deftest sound-test
  (testing "each species has a specific sound"
    (is (= "meow" (sut/sound {:species ::sut/cat :name "Whiskers"})))
    (is (= "woof" (sut/sound {:species ::sut/dog :name "Rex"})))
    (is (= "chirp" (sut/sound {:species ::sut/sparrow :name "Tweety"})))
    (is (= "squawk" (sut/sound {:species ::sut/parrot :name "Polly"})))))

(deftest locomotion-test
  (testing "mammals walk, birds fly — dispatched via hierarchy"
    (is (= "walk" (sut/locomotion {:species ::sut/cat :name "Whiskers"})))
    (is (= "walk" (sut/locomotion {:species ::sut/dog :name "Rex"})))
    (is (= "fly" (sut/locomotion {:species ::sut/sparrow :name "Tweety"})))
    (is (= "fly" (sut/locomotion {:species ::sut/parrot :name "Polly"})))))

(deftest describe-test
  (testing "describe combines name, species, and sound"
    (is (= "Whiskers the cat says meow"
           (sut/describe {:species ::sut/cat :name "Whiskers"})))
    (is (= "Rex the dog says woof"
           (sut/describe {:species ::sut/dog :name "Rex"})))))

(deftest hierarchy-test
  (testing "isa? relationships hold"
    (is (isa? ::sut/cat ::sut/mammal))
    (is (isa? ::sut/dog ::sut/mammal))
    (is (isa? ::sut/sparrow ::sut/bird))
    (is (isa? ::sut/parrot ::sut/bird))
    (is (isa? ::sut/mammal ::sut/animal))
    (is (isa? ::sut/bird ::sut/animal))
    (is (isa? ::sut/cat ::sut/animal))))
