# Exercise 09: Interval Arithmetic

## What you're learning

SICP's interval arithmetic system (Section 2.1.4): representing uncertain
quantities as intervals `[low, high]` and defining arithmetic operations over
them. The gotchas SICP highlights — especially multiplication and
division by intervals containing zero — are the real lesson.

## The problem

Implement an interval arithmetic package:

- `make-interval`: constructs an interval from a lower and upper bound.
- `lower-bound`: returns the lower bound.
- `upper-bound`: returns the upper bound.
- `add-interval`: adds two intervals.
- `mul-interval`: multiplies two intervals (consider all four products of
  endpoints and take the min/max).
- `width`: returns `(upper - lower) / 2`.
- `contains-zero?`: returns true if the interval spans zero.

## The interface

```clojure
(defn make-interval [lo hi])
(defn lower-bound [i])
(defn upper-bound [i])
(defn add-interval [a b])
(defn mul-interval [a b])
(defn width [i])
(defn contains-zero? [i])
```

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-09-interval-arithmetic-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_09_interval_arithmetic.clj`.
