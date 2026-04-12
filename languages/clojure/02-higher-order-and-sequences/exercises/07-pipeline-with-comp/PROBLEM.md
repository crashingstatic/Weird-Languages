# Exercise 07: Pipeline with Comp

## What you're learning

`comp` composes functions: `(comp f g)` returns a new function that applies
`g` first, then `f` to the result. `partial` fixes some arguments of a
function. Together they let you build data-processing pipelines without
naming intermediate results — a common Clojure idiom.

## The problem

You're given a sequence of customer records (maps). Each customer has:

```clojure
{:name    "Alice"
 :region  "west"
 :active  true
 :orders  [{:total 120} {:total 80}]}
```

Implement `(active-order-total region customers)` that returns the sum of
all order totals for active customers in the given region.

You **must** use `comp` at least once in your solution. You may also use
`filter`, `map`, `reduce`, `partial`, `mapcat`, or `apply` as needed.

## The interface

```clojure
(defn active-order-total [region customers] ...)
;; returns a number: the sum of :total across all orders
;; for active customers in the specified region
```

## Hints

- Break the problem into stages: filter by region, filter by active, extract
  orders, flatten, extract totals, sum.
- `mapcat` is `(comp cat map)` — it maps a function that returns collections,
  then concatenates the results.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-07-pipeline-with-comp-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_07_pipeline_with_comp.clj`
