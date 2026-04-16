# Exercise 05: If and Quote

## What you're learning

**Special forms** are expressions where the evaluator does *not* evaluate all
subexpressions. `if` only evaluates one branch. `quote` evaluates nothing —
it returns its argument as data.

## The problem

Implement two functions that the eval dispatch calls:

**(a)** `eval-quote` — given `(quote datum)`, return `datum` without evaluating
it.

**(b)** `eval-if` — given `(if pred then else?)`, evaluate `pred`. If truthy,
evaluate and return `then`. Otherwise, evaluate and return `else` (if present;
`nil` if not).

The environment functions and eval dispatch are provided.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-05-if-and-quote-test
```

**Starter file:** `src/clojure_course/building_a_language/ex_05_if_and_quote.clj`
