# Exercise 06: With Timing

## What you're learning

`gensym` (or auto-gensym with `#` suffix) generates unique symbols to avoid
variable capture — the macro equivalent of "don't shadow the user's variables."

## The problem

Implement `with-timing`:

```clojure
(with-timing (+ 1 2))
;=> {:result 3 :elapsed-ms 0.042}
```

Returns a map with `:result` (the body's value) and `:elapsed-ms`.

Use auto-gensym (`start#`, `result#`) to avoid capturing user variables named
`start` or `result`.

## Hints

- `(System/nanoTime)` returns nanoseconds.
- Divide by `1e6` to get milliseconds.

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-06-with-timing-test
```

**Starter file:** `src/clojure_course/macros_and_code_as_data/ex_06_with_timing.clj`
