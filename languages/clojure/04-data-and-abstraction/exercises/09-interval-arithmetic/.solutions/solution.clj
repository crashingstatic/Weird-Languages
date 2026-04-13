(ns clojure-course.data-and-abstraction.ex-09-interval-arithmetic)

;; Representation: a two-element vector [lo hi].
(defn make-interval [lo hi] [lo hi])
(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

;; [a,b] + [c,d] = [a+c, b+d]
(defn add-interval [a b]
  (make-interval (+ (lower-bound a) (lower-bound b))
                 (+ (upper-bound a) (upper-bound b))))

;; [a,b] * [c,d]: the result spans the min to max of all four products.
;; This handles negative intervals correctly without case analysis.
(defn mul-interval [a b]
  (let [p1 (* (lower-bound a) (lower-bound b))
        p2 (* (lower-bound a) (upper-bound b))
        p3 (* (upper-bound a) (lower-bound b))
        p4 (* (upper-bound a) (upper-bound b))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2.0))

(defn contains-zero? [i]
  (and (<= (lower-bound i) 0)
       (>= (upper-bound i) 0)))
