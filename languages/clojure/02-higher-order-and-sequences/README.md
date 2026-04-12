# Aspect 02: Higher-Order Functions and the Sequence Library

## What this teaches

Functions are values in Clojure — they can be passed as arguments, returned
from other functions, and stored in data structures. The sequence library
(`map`, `filter`, `reduce`, `comp`, `partial`) is built on this idea and
replaces the `for` loop you'd reach for in a C-family language.

## Why it matters

The for-loop reflex is the single biggest tell of a non-functional programmer
working in a functional language. Every `for` loop is secretly a `map`, a
`filter`, a `reduce`, or a combination of them. This aspect breaks the
for-loop reflex by showing how common iteration patterns decompose into
sequence operations — then has you build those operations from scratch so you
understand they're not magic.

## SICP mapping

Chapter 1.3 — Formulating Abstractions with Higher-Order Procedures.

## Prerequisites

Aspect 01: Functional Foundations.

## Vocabulary introduced

`map`, `filter`, `reduce`, `comp`, `partial`, anonymous function shorthand
`#()`, varargs (`& args`), `apply`, `reductions`.

## Exercises

1. **map-double** — Use `map` to double every element in a collection.
2. **filter-evens** — Use `filter` with a predicate to keep even numbers.
3. **reduce-sum** — Use `reduce` with `+` to sum a collection.
4. **my-map** — Implement `map` from scratch using recursion.
5. **my-filter** — Implement `filter` from scratch using recursion.
6. **my-reduce** — Implement `reduce` from scratch, supporting both arities.
7. **pipeline-with-comp** — Compose sequence operations to transform data.
8. **frequencies-from-scratch** — Implement Clojure's `frequencies` without using the built-in.
9. **compose-many** — Implement right-to-left function composition over a sequence of functions.
10. **running-stats** — Compute running count, sum, and mean over a sequence of numbers.

## When you're done

You can replace any `for` loop with a composition of `map`, `filter`, and
`reduce`. You can build these operations yourself from first principles. You
understand that higher-order functions are not syntax sugar — they're the
primary abstraction mechanism for computation over collections.

## Where to go deeper

- *Structure and Interpretation of Computer Programs*, Section 1.3
- [Clojure — Sequences](https://clojure.org/reference/sequences)
- Rich Hickey, "Clojure for Java Programmers" (talk) — the sequence library section
