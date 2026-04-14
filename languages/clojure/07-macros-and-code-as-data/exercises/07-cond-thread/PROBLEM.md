# Exercise 07: Cond Thread

## What you're learning

`cond->` threads a value through conditional steps. Implementing it requires
generating a chain of `let` bindings at macro-expansion time.

## The problem

Implement `my-cond->`:

```clojure
(my-cond-> 1
  true  (+ 1)    ;; applied: 2
  false (* 100)  ;; skipped: still 2
  true  (+ 1))   ;; applied: 3
;=> 3
```

Clauses come in pairs: `test form`. When test is truthy, thread the value
through form (like `->`, inserting as second element).

## Hints

- `(partition 2 clauses)` groups pairs.
- Generate a `let` chain where each step conditionally applies the form.

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-07-cond-thread-test
```

**Starter file:** `src/clojure_course/macros_and_code_as_data/ex_07_cond_thread.clj`
