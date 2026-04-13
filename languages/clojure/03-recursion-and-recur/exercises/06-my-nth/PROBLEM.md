# Exercise 06: my-nth

## What you're learning

Using `loop`/`recur` to walk a sequence to a specific index, with proper
bounds checking. Unlike the previous exercises where you processed every
element, here you stop early — the loop variable tracks how many steps remain.

## The problem

Implement `my-nth`: given a sequence `xs` and a zero-based index `n`, return
the element at that position. If `n` is negative or beyond the end of the
sequence, throw an `IndexOutOfBoundsException`.

Do not use `clojure.core/nth`.

## The interface

```clojure
(defn my-nth [xs n])  ;; returns the element at index n, or throws
```

## Constraints

- Must use `loop`/`recur`.
- Must not use `nth`.
- Must throw `IndexOutOfBoundsException` on out-of-bounds access.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-06-my-nth-test
```

**Starter file:** `src/clojure_course/recursion_and_recur/ex_06_my_nth.clj`
