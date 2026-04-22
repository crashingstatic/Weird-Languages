# Exercise 01: Multimethod Shape Area

## What you're learning

A **multimethod** is Clojure's open-dispatch mechanism. `defmulti` declares the
dispatch function; `defmethod` installs an implementation for a specific dispatch
value. New shapes can be added without touching existing code.

## The problem

A multimethod `area` dispatches on the `:shape` key. Two methods are provided
(`:circle` and `:rectangle`). Fill in the blanks to add the `:triangle` method.

Triangle area = `(* 0.5 base height)`.

## The interface

```clojure
(area {:shape :triangle :base 3 :height 4})  ;=> 6.0
```

## Hints

- `defmethod area :triangle [{:keys [base height]}]` destructures the map.
- The body is a single arithmetic expression.

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-01-multimethod-shape-area-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_01_multimethod_shape_area.clj`.
