# Exercise 02: assoc and update

## What you're learning

`assoc` creates a new map with a key set to a value. `update` creates a new
map with a key transformed by a function. Neither mutates the original. This
is how you "change" immutable data — you don't; you make a new version.

## The problem

Given a person map `{:name "Alice" :age 30 :email "alice@example.com"}`:

1. `set-name`: return a new map with `:name` set to a given string.
2. `birthday`: return a new map with `:age` incremented by 1.
3. `update-email`: return a new map with `:email` set to a given string.

## The interface

```clojure
(defn set-name [person new-name])     ;; returns person with :name replaced
(defn birthday [person])              ;; returns person with :age incremented
(defn update-email [person new-email]) ;; returns person with :email replaced
```

## Hints

- `(assoc m :key val)` returns a new map with `:key` set to `val`.
- `(update m :key f)` returns a new map with `:key` transformed by `f`.
- `inc` increments a number by 1.

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-02-assoc-update-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_02_assoc_update.clj`.
