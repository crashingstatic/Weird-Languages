(ns clojure-course.functional-foundations.ex-07-gcd-euclid)

;; Euclid's algorithm: the GCD doesn't change when you replace
;; (a, b) with (b, a mod b). The process terminates because the
;; second argument strictly decreases toward zero.
(defn gcd [a b]
  (let [a (Math/abs a)
        b (Math/abs b)]
    (if (zero? b)
      a
      (recur b (mod a b)))))
