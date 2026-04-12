# Exercise 03: Reduce Sum

## What you're learning

`reduce` takes a function, an optional initial value, and a collection.
It combines the elements one at a time by applying the function to the
accumulated result and the next element. It's the most general sequence
operation — both `map` and `filter` can be expressed in terms of `reduce`.

## The problem

Implement `(sum-all xs)` that returns the sum of all numbers in `xs`.

Use `reduce` with `+` and an appropriate initial value. Do not use a loop or
explicit recursion.

## The interface

```clojure
(defn sum-all [xs] ...)  ;; returns the sum of all elements
```

## Hints

- `reduce` with an initial value: `(reduce f init coll)`.
- The initial value for addition is `0`.
- What does `(reduce + 0 [])` return?

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-03-reduce-sum-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_03_reduce_sum.clj`
