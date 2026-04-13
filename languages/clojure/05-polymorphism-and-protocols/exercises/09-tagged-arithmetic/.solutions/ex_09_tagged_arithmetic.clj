(ns clojure-course.polymorphism-and-protocols.ex-09-tagged-arithmetic)

(defn attach-tag [tag datum]
  {:tag tag :datum datum})

(defn type-tag [tagged]
  (:tag tagged))

(defn contents [tagged]
  (:datum tagged))

(defmulti add (fn [a b] [(type-tag a) (type-tag b)]))

(defmethod add [:integer :integer] [a b]
  (attach-tag :integer (+ (contents a) (contents b))))

(defmethod add [:rational :rational] [a b]
  (let [[an ad] (contents a)
        [bn bd] (contents b)]
    (attach-tag :rational [(+ (* an bd) (* bn ad)) (* ad bd)])))

(defmethod add [:integer :rational] [a b]
  (let [ai (contents a)
        [bn bd] (contents b)]
    (attach-tag :rational [(+ (* ai bd) bn) bd])))

(defmethod add [:rational :integer] [a b]
  (add b a))
