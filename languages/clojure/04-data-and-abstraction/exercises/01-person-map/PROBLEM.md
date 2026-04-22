# Exercise 01: Person Map

## What you're learning

How to create and access Clojure maps — the workhorse data structure you'll
use for almost everything. Keywords double as accessor functions, and maps are
the default way to represent entities.

## The problem

Define a map called `person` with three keys: `:name` (a string), `:age` (an
integer), and `:email` (a string). Then define a function `greet` that takes a
person map and returns the string `"Hello, <name>!"`.

## The interface

```clojure
person  ;; a map with :name, :age, :email
(defn greet [p])  ;; returns "Hello, <name>!"
```

## Hints

- `{:key value}` is a map literal.
- `(:name m)` or `(get m :name)` retrieves a value.
- `str` concatenates strings.

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-01-person-map-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_01_person_map.clj`.
