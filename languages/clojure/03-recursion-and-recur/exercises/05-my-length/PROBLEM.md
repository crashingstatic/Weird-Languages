# Exercise 05: my-length

## What you're learning

Another application of the accumulator pattern: counting elements by walking a
sequence with `loop`/`recur` and incrementing a counter.

## The problem

Implement `my-length`: given a sequence, return the number of elements. You
must use `loop`/`recur`. The test includes a 1,000,000-element input.

Do not use `clojure.core/count`.

## The interface

```clojure
(defn my-length [xs])  ;; returns the number of elements in xs
```

## Constraints

- Must use `loop`/`recur`.
- Must not use `count`.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-05-my-length-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/recursion_and_recur/ex_05_my_length.clj`.
