# Exercise 06: Power (Recursive)

## What you're learning

Simple recursion: a function that calls itself with a smaller problem until
it hits a base case. This is the most direct way to express "do something
n times" in a functional language — no loop variable, no mutation, just a
function and a shrinking argument.

## The problem

Implement `(power b n)` that computes `b` raised to the non-negative integer
power `n`, using recursion. The definition:

- `b^0 = 1` (base case)
- `b^n = b * b^(n-1)` (recursive case)

Do not use `Math/pow`. Use multiplication and recursion.

## The interface

```clojure
(defn power [b n] ...)  ;; returns b^n for non-negative integer n
```

## Hints

- Think about what value to return when `n` is zero.
- The recursive case multiplies `b` by the result of a smaller problem.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-06-power-recursive-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_06_power_recursive.clj`.
