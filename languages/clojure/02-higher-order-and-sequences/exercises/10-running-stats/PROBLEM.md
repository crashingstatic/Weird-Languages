# Exercise 10: Running Stats

## What you're learning

`reductions` is the "scan" operation — like `reduce`, but it returns
the sequence of all intermediate accumulator values, not just the final one.
This exercise combines everything from the aspect: higher-order functions,
sequence operations, and building derived data from a stream of inputs.

## The problem

Implement `(running-stats numbers)` that takes a sequence of numbers and
returns a sequence of maps, one per input element, showing the running
count, sum, and mean at each step.

- `(running-stats [10 20 30])` =>
  ```
  ({:count 1 :sum 10 :mean 10.0}
   {:count 2 :sum 30 :mean 15.0}
   {:count 3 :sum 60 :mean 20.0})
  ```
- `(running-stats [])` => `()`
- `(running-stats [5])` => `({:count 1 :sum 5 :mean 5.0})`

The `:mean` value should be a double (use `double` or `/` with floating-point
arithmetic).

## The interface

```clojure
(defn running-stats [numbers] ...)
;; returns a seq of {:count n :sum s :mean m} maps
```

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-10-running-stats-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_10_running_stats.clj`
