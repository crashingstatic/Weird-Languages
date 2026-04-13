# Exercise 09: my-flatten

## What you're learning

Building a recursive function that transforms a tree structure into a flat
sequence. This combines tree recursion with sequence construction and is a
practical function you'll use frequently.

## The problem

Implement `my-flatten`: given an arbitrarily nested structure, return a flat
sequence of all non-sequential leaves, preserving left-to-right order.

For example:
- `(my-flatten [1 [2 [3]] 4])` → `(1 2 3 4)`
- `(my-flatten [])` → `()`
- `(my-flatten [[[]]])` → `()`

Do not use `clojure.core/flatten`.

## The interface

```clojure
(defn my-flatten [coll])  ;; returns a flat sequence of all leaves
```

## Constraints

- Must not use `clojure.core/flatten`.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-09-my-flatten-test
```

**Starter file:** `src/clojure_course/recursion_and_recur/ex_09_my_flatten.clj`
