# Exercise 01: Hello Values

## What you're learning

In Clojure, `def` binds a name to a value in the current namespace. There are
no variable declarations, no type annotations, no assignment operators — just
a name and a value. This exercise gets you typing your first parentheses and
seeing them evaluate.

## The problem

Define several named values using `def`:

- `pi` — the double `3.14159`
- `e` — the double `2.71828`
- `greeting` — the string `"Hello, Clojure!"`
- `answer` — the integer `42`
- `golden-ratio` — the double `1.6180339887`

Then define a function `circle-area` that takes a radius `r` and returns
`pi * r * r`.

## The interface

```clojure
(def pi ___)
(def e ___)
(def greeting ___)
(def answer ___)
(def golden-ratio ___)

(defn circle-area [r] ...)
```

The test will check that each name is bound to the correct value and that
`circle-area` returns the right result for several inputs.

## Hints

- `def` creates a global binding: `(def name value)`.
- `defn` is shorthand for `def` + `fn`: `(defn name [args] body)`.
- Arithmetic in Clojure is prefix: `(* 3 4)` returns `12`.
- You can use `pi` inside `circle-area` — it's already defined in the same namespace.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.functional-foundations.ex-01-hello-values-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/functional_foundations/ex_01_hello_values.clj`.
