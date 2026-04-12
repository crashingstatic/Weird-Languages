# Exercise 07: GCD (Euclid)

## What you're learning

Euclid's algorithm is one of the oldest known algorithms. It computes the
greatest common divisor of two integers using repeated division. In Clojure,
it's a clean example of recursion with a shrinking problem: each step replaces
the larger number with the remainder, until the remainder is zero.

## The problem

Implement `(gcd a b)` using Euclid's algorithm:

- If `b` is zero, the GCD is `a`.
- Otherwise, the GCD of `a` and `b` is the GCD of `b` and `(mod a b)`.

Handle the following edge cases:
- Either argument may be zero (the GCD of 0 and n is n).
- Arguments may arrive in any order.
- Arguments may be negative (GCD is always non-negative).

## The interface

```clojure
(defn gcd [a b] ...)  ;; returns the greatest common divisor of a and b
```

## Hints

- `mod` gives the remainder: `(mod 10 3)` is `1`.
- Use `Math/abs` to handle negative inputs (or handle them at the start).

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-07-gcd-euclid-test
```

Your starter file: `src/clojure_course/functional_foundations/ex_07_gcd_euclid.clj`
