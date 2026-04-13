(ns clojure-course.data-and-abstraction.ex-05-rational-arithmetic)

;; Representation: a two-element vector [numerator denominator].
;; make-rat normalizes sign and reduces to lowest terms.
(defn- gcd [a b]
  (if (zero? b) a (recur b (mod a b))))

(defn make-rat [n d]
  (let [sign  (if (neg? d) -1 1)
        n     (* sign n)
        d     (* sign d)
        g     (gcd (Math/abs n) d)]
    [(/ n g) (/ d g)]))

(defn numer [r] (first r))
(defn denom [r] (second r))

;; a/b + c/d = (a*d + c*b) / (b*d)
(defn add-rat [r1 r2]
  (make-rat (+ (* (numer r1) (denom r2))
               (* (numer r2) (denom r1)))
            (* (denom r1) (denom r2))))

;; a/b * c/d = (a*c) / (b*d)
(defn mul-rat [r1 r2]
  (make-rat (* (numer r1) (numer r2))
            (* (denom r1) (denom r2))))

;; a/b = c/d iff a*d = c*b (both already in lowest terms,
;; but cross-multiply is safer)
(defn equal-rat? [r1 r2]
  (= (* (numer r1) (denom r2))
     (* (numer r2) (denom r1))))
