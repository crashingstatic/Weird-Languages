# Exercise 09: Closures and Lexical Scope

## What you're learning

The most important property of `lambda` is **lexical scoping**: a closure sees
the environment where it was *defined*, not where it is *called*. Get this
wrong and closures break in subtle ways.

## The problem

The starter file provides a **complete evaluator** — but it has a scoping bug.
Lambda does not capture the defining environment, so closures cannot see
variables from their enclosing scope.

Your task: **find and fix the bug.** The tests exercise closures, nested
lambdas, higher-order functions, and shadowing. They will tell you exactly
what is broken.

**Hint:** Look at what `:env` is set to in the `lambda` and `define` cases.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-09-closures-and-lexical-scope-test
```

**Starter file:** `src/clojure_course/building_a_language/ex_09_closures_and_lexical_scope.clj`
