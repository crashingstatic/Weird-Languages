# Exercise 02: Tail-Recursive Sum

## What you're learning

How `recur` works in the tail position of a `loop`. The structure is provided —
you supply the `recur` expression that advances the computation by one step.

## The problem

Implement `running-sum`: given a sequence of numbers, return their sum. A
`loop` with two bindings (`remaining` and `acc`) is provided. Fill in the
`recur` call that processes the first element and continues with the rest.

## The interface

```clojure
(defn running-sum [xs])  ;; returns the sum of all elements in xs
```

## Hints

- `first` gives you the head of the sequence; `rest` gives you the tail.
- The accumulator should grow by the current element each step.
- The `recur` call replaces both `remaining` and `acc` simultaneously.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-02-tail-recur-sum-test
```

**Starter file:** `src/clojure_course/recursion_and_recur/ex_02_tail_recur_sum.clj`
