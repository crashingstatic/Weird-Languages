# Exercise 04: Basic Arithmetic Functions

## What you're learning

`defn` is sugar for `def` + `fn`. A function in Clojure is a value — it can
be bound to a name, passed as an argument, returned from another function.
This exercise has you write small functions from scratch so the mechanics of
`defn` become automatic.

## The problem

Implement four functions:

- `square` — returns `n * n`.
- `cube` — returns `n * n * n`.
- `average` — returns the arithmetic mean of `a` and `b`.
- `distance-2d` — returns the Euclidean distance between points
  `(x1, y1)` and `(x2, y2)`: `sqrt((x2-x1)^2 + (y2-y1)^2)`.

Use `Math/sqrt` for the square root. You may use your own `square` function
inside `distance-2d`.

## The interface

```clojure
(defn square [n] ...)
(defn cube [n] ...)
(defn average [a b] ...)
(defn distance-2d [x1 y1 x2 y2] ...)
```

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-04-basic-arithmetic-fns-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_04_basic_arithmetic_fns.clj`.
