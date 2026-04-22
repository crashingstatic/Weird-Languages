# Exercise 05: Rational Arithmetic

## What you're learning

The SICP rational number package (Section 2.1.1): a complete data abstraction
with constructor, selectors, and operations. The key insight is that reducing
to lowest terms belongs in the constructor — callers never think about it.

## The problem

Implement a rational number package:

- `make-rat`: constructs a rational from numerator and denominator, reduced to
  lowest terms. Denominator must be positive (if the rational is negative, the
  sign goes on the numerator).
- `numer`: returns the numerator.
- `denom`: returns the denominator.
- `add-rat`: adds two rationals.
- `mul-rat`: multiplies two rationals.
- `equal-rat?`: returns true if two rationals represent the same value.

## The interface

```clojure
(defn make-rat [n d])
(defn numer [r])
(defn denom [r])
(defn add-rat [r1 r2])
(defn mul-rat [r1 r2])
(defn equal-rat? [r1 r2])
```

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-05-rational-arithmetic-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_05_rational_arithmetic.clj`.
