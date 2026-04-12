# Exercise 08: Frequencies from Scratch

## What you're learning

Building a real utility function from the tools you now have. Clojure's
built-in `frequencies` returns a map from each distinct element to the number
of times it appears. Implementing it yourself is a natural exercise in
`reduce` — the accumulator is a map, and each step updates a count.

## The problem

Implement `(my-frequencies coll)` that returns a map from each distinct
element in `coll` to the number of times it appears.

- `(my-frequencies [:a :b :a :c :b :a])` => `{:a 3, :b 2, :c 1}`
- `(my-frequencies [])` => `{}`
- `(my-frequencies [42])` => `{42 1}`

Do not use the built-in `frequencies` or `group-by`.

## The interface

```clojure
(defn my-frequencies [coll] ...)  ;; returns a map of element → count
```

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-08-frequencies-from-scratch-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_08_frequencies_from_scratch.clj`
