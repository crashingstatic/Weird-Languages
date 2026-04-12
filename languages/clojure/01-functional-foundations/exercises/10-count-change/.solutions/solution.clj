(ns clojure-course.functional-foundations.ex-10-count-change)

(def ^:private us-coins [50 25 10 5 1])

;; The two branches of the recursion mirror the two choices:
;; use the largest remaining coin at least once, or skip it entirely.
;; This produces a tree whose leaves are the base cases.
(defn- cc [amount coins]
  (cond
    (zero? amount)  1
    (neg? amount)   0
    (empty? coins)  0
    :else (+ (cc (- amount (first coins)) coins)
             (cc amount (rest coins)))))

(defn count-change [amount]
  (cc amount us-coins))
