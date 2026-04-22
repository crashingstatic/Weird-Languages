# Exercise 03: The Accumulator Pattern

## What you're learning

The accumulator pattern: converting a computation that builds its result on the
way *back up* the call stack (linear-recursive) into one that carries the
partial result *forward* through an accumulator (linear-iterative). This is
the prerequisite for using `recur`.

## The problem

Implement `sum-to-n`: given a non-negative integer `n`, return the sum
`0 + 1 + 2 + ... + n`. The `loop`/`recur` skeleton is provided. Fill in
the accumulator's initial value and the `recur` step.

## The interface

```clojure
(defn sum-to-n [n])  ;; returns 0 + 1 + 2 + ... + n
```

## Hints

- The accumulator starts at 0 (the identity for addition).
- Each step adds `i` to the accumulator and decrements `i`.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-03-accumulator-pattern-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/recursion_and_recur/ex_03_accumulator_pattern.clj`.
