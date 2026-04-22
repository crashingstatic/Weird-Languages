# Exercise 08: Fast Power

## What you're learning

The square-and-halve trick (SICP 1.2.4): instead of computing `b^n` in O(n)
multiplications, observe that `b^n = (b^(n/2))^2` when n is even. This halves
the problem each step, giving O(log n) time. The naive O(n) solution from
exercise 06 won't pass the performance test here.

## The problem

Implement `(fast-power b n)` that computes `b^n` for non-negative integer `n`
in O(log n) multiplications. The algorithm:

- If `n` is 0, return 1.
- If `n` is even, compute `fast-power(b, n/2)` and square the result.
- If `n` is odd, return `b * fast-power(b, n-1)`.

The test includes a case with `n = 1000000` that will time out if you use
the naive O(n) approach.

## The interface

```clojure
(defn fast-power [b n] ...)  ;; returns b^n in O(log n) time
```

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-08-fast-power-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_08_fast_power.clj`.
