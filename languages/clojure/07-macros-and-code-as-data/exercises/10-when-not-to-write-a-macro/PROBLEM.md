# Exercise 10: When Not to Write a Macro

## What you're learning

The most important macro lesson: **when not to write one.** If a function can do
the job, a function is always better — it composes, it can be passed as a value,
and it doesn't have compile-time evaluation surprises.

## The problem

The starter file provides an over-engineered `apply-transforms-macro`. Your tasks:

**(a)** Write `apply-transforms` — the simplest function that applies a sequence
of functions to a value. (Hint: `reduce`.)

**(b)** The tests verify your function works with runtime-computed transform
sequences — something the macro cannot do.

**(c)** In a comment in your solution, explain in 2 sentences why the macro
version is wrong.

**NOTE:** Part (c) is human-reviewed. There is no automated test for it.

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-10-when-not-to-write-a-macro-test
```

**Starter file:** `src/clojure_course/macros_and_code_as_data/ex_10_when_not_to_write_a_macro.clj`
