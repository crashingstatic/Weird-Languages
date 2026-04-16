# Exercise 10: Tiny REPL

## What you're learning

A language isn't complete until you can interact with it. The REPL
(Read-Eval-Print Loop) ties together everything you've built: parsing,
evaluation, and output.

## The problem

Implement `run-program`: given a vector of program-line strings, parse and
evaluate each one in order. All lines share the same environment, so `define`s
persist across lines. Return a vector of results.

Example:
```clojure
(run-program ["(define x 10)" "(+ x 5)"])
;=> [10 15]
```

The complete parser and evaluator are provided.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-10-tiny-repl-test
```

**Starter file:** `src/clojure_course/building_a_language/ex_10_tiny_repl.clj`
