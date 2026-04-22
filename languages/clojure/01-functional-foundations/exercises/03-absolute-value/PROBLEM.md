# Exercise 03: Absolute Value

## What you're learning

In Clojure, `if` is an expression, not a statement. It evaluates to a value —
the "then" branch if the condition is truthy, the "else" branch otherwise.
There is no ternary operator because `if` already is one. This exercise makes
that concrete.

## The problem

Implement three functions:

- `my-abs` — returns the absolute value of a number `n`. Use `if` to check
  whether `n` is negative.
- `sign` — returns `-1`, `0`, or `1` depending on whether `n` is negative,
  zero, or positive. Use `cond` (Clojure's multi-branch conditional).
- `clamp` — given a value `n`, a `lo`, and a `hi`, returns `n` clamped to
  the range `[lo, hi]`.

## The interface

```clojure
(defn my-abs [n] ...)     ;; returns |n|
(defn sign [n] ...)       ;; returns -1, 0, or 1
(defn clamp [n lo hi] ...) ;; returns n bounded to [lo, hi]
```

## Hints

- `if` syntax: `(if condition then-expr else-expr)`.
- `cond` syntax: `(cond test1 expr1, test2 expr2, :else default)`.
- `neg?`, `pos?`, and `zero?` are built-in predicates.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-03-absolute-value-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_03_absolute_value.clj`.
