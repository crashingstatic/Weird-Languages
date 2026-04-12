# Exercise 05: My Filter

## What you're learning

Like `map`, `filter` is a straightforward recursive function. The only
difference: at each step you decide whether to include the current element
based on the predicate's return value.

## The problem

Implement `(my-filter pred coll)` that behaves like Clojure's built-in
`filter` for a single collection: it returns a sequence of elements for which
`(pred elem)` is truthy.

- `(my-filter even? [1 2 3 4 5])` => `(2 4)`
- `(my-filter pos? [-1 0 1 2])` => `(1 2)`
- `(my-filter even? [])` => `()`

Laziness is **not** required. Do not use the built-in `filter`, `filterv`,
`remove`, or `for`.

## The interface

```clojure
(defn my-filter [pred coll] ...)  ;; returns a seq of elements satisfying pred
```

## Hints

- Same recursive skeleton as `my-map`, but with a conditional: if `(pred elem)`
  is truthy, include it; otherwise skip it and recur on the rest.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-05-my-filter-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_05_my_filter.clj`
