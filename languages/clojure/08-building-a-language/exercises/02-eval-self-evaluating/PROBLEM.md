# Exercise 02: Eval Self-Evaluating

## What you're learning

The simplest case in any evaluator: some expressions are their own value.
Numbers evaluate to themselves. Strings evaluate to themselves. Booleans
evaluate to themselves. Everything else requires more work.

## The problem

Fill in two blanks:

**(a)** `self-evaluating?` — a predicate that returns `true` for numbers,
strings, and booleans.

**(b)** The return value in `tiny-eval` — when an expression is
self-evaluating, return it unchanged.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-02-eval-self-evaluating-test
```

**Starter file:** `src/clojure_course/building_a_language/ex_02_eval_self_evaluating.clj`
