# Exercise 05: Newton's Square Root

## What you're learning

Recursive processes: a function that calls itself to iteratively refine a
guess until it converges. This is SICP section 1.1.7 adapted to Clojure.
The key insight is that recursion isn't just for "recursive data structures" —
it's a natural way to express iterative improvement.

## The problem

Implement `sqrt` using Newton's method. The algorithm:

1. Start with an initial guess (1.0 works).
2. Check if the guess is "good enough" — `|guess^2 - n| < 0.00001`.
3. If not, improve the guess: `new-guess = (guess + n/guess) / 2`.
4. Repeat until good enough.

You'll need two helper functions:

- `good-enough?` — returns true if the guess is within tolerance.
- `improve` — returns an improved guess.

Then `sqrt` ties them together in a recursive `sqrt-iter` (either as a
separate function or as a local `letfn`).

## The interface

```clojure
(defn good-enough? [guess n] ...)  ;; true if |guess^2 - n| < 0.00001
(defn improve [guess n] ...)       ;; returns (guess + n/guess) / 2
(defn sqrt [n] ...)                ;; returns the square root of n
```

## Hints

- `Math/abs` gives you absolute value for doubles.
- Start your initial guess at `1.0` (a double, not an integer).

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-05-newton-sqrt-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_05_newton_sqrt.clj`.
