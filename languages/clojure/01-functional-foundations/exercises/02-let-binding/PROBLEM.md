# Exercise 02: Let Binding

## What you're learning

`let` creates local bindings — names that exist only inside the `let` form.
Unlike `def`, which creates a namespace-level global, `let` bindings are
lexically scoped: they're visible only within the body of the `let`, and
they disappear when the `let` form is done evaluating.

## The problem

Write a function `circle-area` that takes a radius `r` and returns the area
of a circle. Inside the function, use a `let` form to bind `pi` to `3.14159`
before computing the area.

Write a second function `cylinder-volume` that takes a radius `r` and a height
`h`. Use `let` to bind both `pi` and `base-area` (the area of the circular
base), then return `base-area * h`.

## The interface

```clojure
(defn circle-area [r] ...)    ;; returns pi * r * r
(defn cylinder-volume [r h] ...) ;; returns pi * r * r * h
```

## Hints

- `let` syntax: `(let [name1 value1, name2 value2] body)`.
- Bindings in a `let` are evaluated top-to-bottom. A later binding can
  refer to an earlier one in the same `let`.
- `let` is an expression — it returns the value of its body.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-02-let-binding-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_02_let_binding.clj`.
