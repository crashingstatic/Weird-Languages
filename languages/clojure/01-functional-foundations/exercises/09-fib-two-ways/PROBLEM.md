# Exercise 09: Fibonacci Two Ways

## What you're learning

The difference between a tree-recursive process and a linear iterative process.
Both compute the same result, but the tree-recursive version has exponential
time complexity while the iterative version is linear. This is SICP 1.2.2's
central illustration: the shape of a process is not determined by the syntax
of the function.

## The problem

Implement two functions:

- `fib-tree` — computes the nth Fibonacci number using tree recursion.
  `fib(0) = 0`, `fib(1) = 1`, `fib(n) = fib(n-1) + fib(n-2)`.
- `fib-iter` — computes the same result using a linear iterative process
  (constant stack). Must handle `n = 80` without blowing up.

Both must return the same values for all inputs. The test for `fib-iter`
includes `n = 80` — the tree-recursive version would take longer than the
age of the universe on that input.

## The interface

```clojure
(defn fib-tree [n] ...)  ;; tree-recursive Fibonacci
(defn fib-iter [n] ...)  ;; linear iterative Fibonacci
```

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-09-fib-two-ways-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_09_fib_two_ways.clj`.
