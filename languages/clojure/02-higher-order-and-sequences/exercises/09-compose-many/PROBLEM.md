# Exercise 09: Compose Many

## What you're learning

Clojure's `comp` composes a fixed number of functions. What if you have a
*sequence* of functions and you want to compose them all? You need a function
that takes a collection of one-argument functions and returns their
right-to-left composition. This is `reduce` applied to function composition
itself — functions as data.

## The problem

Implement `(compose-all fs)` that takes a sequence of single-argument
functions and returns a new function that is their right-to-left composition.

- `((compose-all [inc inc inc]) 0)` => `3`
- `((compose-all [str inc]) 1)` => `"2"` (inc first, then str)
- `((compose-all []) 42)` => `42` (identity — empty composition)

Do not use the built-in `comp`.

## The interface

```clojure
(defn compose-all [fs] ...)  ;; returns a single-argument function
```

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-09-compose-many-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_09_compose_many.clj`
