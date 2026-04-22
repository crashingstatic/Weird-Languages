# Exercise 04: my-reverse

## What you're learning

Using `loop`/`recur` with an accumulator to reverse a sequence. The accumulator
naturally builds the result in reverse order — this is one of the cases where
the accumulator pattern gives you the answer directly instead of requiring a
final reversal.

## The problem

Implement `my-reverse`: given a sequence, return a new sequence with the
elements in reverse order. You must use `loop`/`recur` — the test includes a
100,000-element input that would overflow the stack with naive recursion.

Do not use `clojure.core/reverse` or `clojure.core/rseq`.

## The interface

```clojure
(defn my-reverse [xs])  ;; returns a sequence with elements of xs in reverse order
```

## Constraints

- Must use `loop`/`recur`.
- Must not use `reverse` or `rseq`.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-04-my-reverse-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/recursion_and_recur/ex_04_my_reverse.clj`.
