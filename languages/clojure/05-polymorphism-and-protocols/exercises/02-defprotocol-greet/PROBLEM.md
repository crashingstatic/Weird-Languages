# Exercise 02: Defining a Protocol

## What you're learning

A **protocol** declares a named set of polymorphic functions. Each function
dispatches on the type of its first argument. Records can implement protocols
inline at definition time.

## The problem

Fill in the blanks to:

1. Complete the `Greetable` protocol with a `greet` method.
2. Implement `greet` for `Person` — return `"Hello, <name>!"`.
3. Implement `greet` for `Robot` — return `"BEEP BOOP, I am <id>"`.

## The interface

```clojure
(greet (->Person "Alice"))   ;=> "Hello, Alice!"
(greet (->Robot "R2D2"))     ;=> "BEEP BOOP, I am R2D2"
```

## Hints

- Protocol methods look like: `(method-name [this])`.
- Inside a `defrecord`, fields are in scope as locals.

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-02-defprotocol-greet-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_02_defprotocol_greet.clj`.
