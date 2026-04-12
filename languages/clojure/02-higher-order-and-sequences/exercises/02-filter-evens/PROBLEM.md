# Exercise 02: Filter Evens

## What you're learning

`filter` takes a predicate and a collection and returns a sequence of elements
for which the predicate returns true. It's how you express "keep only the
elements that satisfy this condition" — the functional replacement for an
`if` inside a `for` loop.

## The problem

Implement `(evens-only xs)` that returns a sequence containing only the even
numbers from `xs`.

Use the built-in `filter` with an appropriate predicate.

## The interface

```clojure
(defn evens-only [xs] ...)  ;; returns a seq of even numbers
```

## Hints

- `filter` takes a predicate and a collection: `(filter pred coll)`.
- Clojure has a built-in `even?` predicate.
- Like `map`, `filter` returns a lazy sequence.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-02-filter-evens-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_02_filter_evens.clj`
