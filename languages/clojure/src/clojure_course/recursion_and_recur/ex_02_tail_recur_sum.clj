(ns clojure-course.recursion-and-recur.ex-02-tail-recur-sum)

;; TASK: Fill in the recur call to sum all elements of xs.

(def ^:private ___ nil)

(defn running-sum [xs]
  (loop [remaining xs
         acc       0]
    (if (empty? remaining)
      acc
      (recur ___ ___))))   ;; <-- new remaining, new acc
