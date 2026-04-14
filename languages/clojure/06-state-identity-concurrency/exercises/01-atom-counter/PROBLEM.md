# Exercise 01: Atom Counter

## What you're learning

An **atom** is Clojure's simplest reference type. It holds a single value that
can be updated atomically using `swap!` (apply a function) or `reset!` (set
directly). `@` (or `deref`) reads the current value.

## The problem

Fill in the blanks to implement:

1. `make-counter` — creates an atom initialized to 0
2. `increment` — uses `swap!` to add 1
3. `get-count` — dereferences the atom

## Hints

- `(atom 0)` creates an atom holding 0.
- `(swap! a inc)` applies `inc` to the atom's value.
- `@a` reads the atom.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-01-atom-counter-test
```

**Starter file:** `src/clojure_course/state_identity_concurrency/ex_01_atom_counter.clj`
