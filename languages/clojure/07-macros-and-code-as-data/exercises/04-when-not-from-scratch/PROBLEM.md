# Exercise 04: When-Not from Scratch

## What you're learning

The "killer test" for a conditional macro is proving it does NOT evaluate its
body when it shouldn't. If you accidentally implement `when-not` as a function,
both branches get evaluated before the function even runs.

## The problem

Implement `my-when-not` as a macro:

```clojure
(my-when-not condition body1 body2 ...)
```

Evaluates body forms only when condition is falsy. The test uses a side-effecting
body to prove the body is not evaluated when the condition is truthy.

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-04-when-not-from-scratch-test
```

**Starter file:** `src/clojure_course/macros_and_code_as_data/ex_04_when_not_from_scratch.clj`
