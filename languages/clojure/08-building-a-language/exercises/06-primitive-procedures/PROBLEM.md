# Exercise 06: Primitive Procedures

## What you're learning

A language without built-in operations is useless. Primitive procedures are
the bridge between your tiny language and the host language (Clojure). Each
primitive is a map `{:type :primitive :fn <clojure-fn>}`.

## The problem

Implement two functions:

**(a)** `make-global-env` — returns an environment (a list containing one
frame) where primitive procedure names are bound to their implementations.
Bind at least: `+`, `-`, `*`, `/`, `=`, `<`, `>`, `not`.

**(b)** `apply-primitive` — given a primitive procedure map and a list of
arguments, apply the Clojure function to the arguments.

The eval dispatch is provided and already handles application by calling
`apply-primitive` when the procedure type is `:primitive`.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-06-primitive-procedures-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/building_a_language/ex_06_primitive_procedures.clj`.
