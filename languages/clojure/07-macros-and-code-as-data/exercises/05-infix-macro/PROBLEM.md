# Exercise 05: Infix Macro

## What you're learning

Macros rearrange code. The simplest rewrite: take `(infix a op b)` and produce
`(op a b)`.

## The problem

Implement an `infix` macro:

```clojure
(infix 1 + 2)   ;=> 3
(infix 3 * 4)   ;=> 12
(infix 5 = 5)   ;=> true
```

Only handles simple three-element expressions (no precedence needed).

## Hints

- `(list op left right)` builds a prefix form from the three arguments.

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-05-infix-macro-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/macros_and_code_as_data/ex_05_infix_macro.clj`.
