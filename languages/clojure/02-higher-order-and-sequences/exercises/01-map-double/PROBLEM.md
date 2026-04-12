# Exercise 01: Map Double

## What you're learning

`map` applies a function to every element of a collection and returns a new
sequence of the results. It's the most common replacement for a `for` loop
that transforms each element. Here you use it for the first time.

## The problem

Implement `(double-all xs)` that returns a sequence where every element of
`xs` has been multiplied by 2.

Use the built-in `map` with an appropriate function. Do not use a loop or
explicit recursion.

## The interface

```clojure
(defn double-all [xs] ...)  ;; returns a seq of each element doubled
```

## Hints

- `map` takes a function and a collection: `(map f coll)`.
- You can pass a named function like `inc`, or an anonymous function like
  `(fn [x] (* x 2))`, or the shorthand `#(* % 2)`.
- `map` returns a lazy sequence, but the tests compare with `=` which
  realizes it — you don't need to worry about laziness here.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-01-map-double-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_01_map_double.clj`
