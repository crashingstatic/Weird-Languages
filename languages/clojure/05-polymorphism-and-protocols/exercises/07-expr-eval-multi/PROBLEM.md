# Exercise 07: Expression Evaluator via Multimethods

## What you're learning

Multimethods excel when you need to dispatch on a value stored in data (like an
`:op` keyword), not on the Java type. Expression trees are a natural fit.

## The problem

Expressions are maps with an `:op` key:

```clojure
{:op :number :value 5}
{:op :sum :left expr :right expr}
{:op :product :left expr :right expr}
```

Implement two multimethods:

1. `eval-expr` — evaluates the expression tree to a number
2. `stringify` — returns a parenthesized string like `"((5 + 3) * 2)"`

## The interface

```clojure
(defmulti eval-expr :op)
(defmulti stringify :op)
```

## Hints

- `:number` is the base case for both.
- `stringify` for `:sum` returns `"(<left> + <right>)"`.

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-07-expr-eval-multi-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_07_expr_eval_multi.clj`.
