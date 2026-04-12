# Exercise 04: My Map

## What you're learning

`map` is not magic — it's a recursive function that applies a function to the
first element of a collection, then recurs on the rest. Building it yourself
makes the abstraction concrete: a higher-order function is just a function
that takes a function.

## The problem

Implement `(my-map f coll)` that behaves like Clojure's built-in `map` for
a single collection: it applies `f` to each element and returns a sequence
of the results.

- `(my-map inc [1 2 3])` => `(2 3 4)`
- `(my-map str [1 2 3])` => `("1" "2" "3")`
- `(my-map inc [])` => `()`

Laziness is **not** required. An eager, fully-realized sequence is fine.
Do not use the built-in `map`, `mapv`, or `for`.

## The interface

```clojure
(defn my-map [f coll] ...)  ;; returns a seq of (f elem) for each elem
```

## Hints

- Think recursively: what's the result for the empty case? For a non-empty
  collection, apply `f` to the first element and `cons` it onto the result
  of mapping the rest.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-04-my-map-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_04_my_map.clj`
