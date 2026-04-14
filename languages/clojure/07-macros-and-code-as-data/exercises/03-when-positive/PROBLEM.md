# Exercise 03: When Positive

## What you're learning

Macros with `& body` accept multiple body forms. `~@body` splices them into
the expansion.

## The problem

Fill in the body of `when-positive`:

```clojure
(when-positive n body1 body2 ...)
```

Evaluates the body forms only if `n` is positive. Returns nil otherwise.

## Hints

- `pos?` tests if a number is positive.
- `` `(when (pos? ~n) ~@body) ``

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-03-when-positive-test
```

**Starter file:** `src/clojure_course/macros_and_code_as_data/ex_03_when_positive.clj`
