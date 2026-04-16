# Exercise 07: Lambda and Application

## What you're learning

This is the heart of the evaluator. `lambda` creates a **closure** — a
procedure value that captures the environment where it was defined. Application
evaluates the operator and operands, then calls the procedure.

After this exercise, you have a working language.

## The problem

Implement two functions:

**(a)** `make-procedure` — takes a list of parameter names, a body (vector of
expressions), and the current environment. Returns a map:
`{:type :compound :params params :body body :env env}`.

**(b)** `tiny-apply` — applies a procedure to arguments:
- For `:primitive` procedures: use Clojure's `apply` with `(:fn proc)`.
- For `:compound` procedures: evaluate the body in an environment extended
  with the parameter bindings. Use `eval-sequence` (provided) for multi-form
  bodies.

The eval dispatch is provided and handles `lambda`, `define` (including the
`(define (f x) body)` sugar), and application by calling your functions.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-07-lambda-and-application-test
```

**Starter file:** `src/clojure_course/building_a_language/ex_07_lambda_and_application.clj`
