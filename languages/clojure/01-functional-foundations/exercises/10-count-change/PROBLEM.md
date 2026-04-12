# Exercise 10: Count Change

## What you're learning

Tree recursion applied to a combinatorial problem: counting the number of
ways to make change for a given amount using a set of coin denominations.
This is SICP exercise 1.2.2. The tree structure emerges naturally from the
two choices at each step: use the current coin denomination or skip it.

## The problem

Implement `(count-change amount)` that returns the number of ways to make
change for `amount` cents using US coin denominations: 1, 5, 10, 25, 50.

The algorithm (recursive, no memoization needed):

- 0 cents can be changed in exactly 1 way (use no coins).
- A negative amount can be changed in 0 ways.
- If there are no coin denominations left, there are 0 ways.
- Otherwise, the count is: ways using the first denomination at least once
  (subtract it from amount, keep all denominations) PLUS ways not using the
  first denomination at all (keep the amount, drop the first denomination).

You may structure helper functions however you like. A common approach is a
helper that takes both the amount and the list of denominations.

## The interface

```clojure
(defn count-change [amount] ...)  ;; returns the number of ways to make change
```

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-10-count-change-test
```

Your starter file: `src/clojure_course/functional_foundations/ex_10_count_change.clj`
