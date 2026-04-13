# Aspect 04: Data and Abstraction

## What this teaches

Clojure's approach to data: model the world as immutable maps, vectors, and
sets first; write small functions that transform them; reach for protocols and
records only when plain data isn't enough. The persistent collection literals
are not afterthoughts — they're the substrate everything else builds on.

## Why it matters

A Java programmer reaches for a class. A C programmer reaches for a struct. A
Clojure programmer reaches for a map. This shift — from "what is the noun?" to
"what is the shape of the data?" — is the single biggest day-to-day difference
in how Clojure code is written. Data abstraction barriers (constructors and
selectors that hide representation choices) give you the benefits of
encapsulation without the ceremony of class hierarchies.

## SICP mapping

Chapter 2.1–2.2 — Building Abstractions with Data. The rational number
package, data abstraction barriers, and the idea that representation choices
should be hidden behind a small interface.

## Prerequisites

Aspects 01 (Functional Foundations), 02 (Higher-Order Functions), and 03
(Recursion and `recur`).

## Vocabulary introduced

Vectors, maps, sets, keywords as functions, `assoc`, `dissoc`, `update`,
`get`, `get-in`, `assoc-in`, `update-in`, destructuring (positional and
associative), `select-keys`, `merge`, `conj` on different collection types.

## Exercises

1. **person-map** — Define a map with `:name`, `:age`, and `:email` keys.
2. **assoc-update** — Use `assoc` and `update` to transform a person map.
3. **destructure-args** — Fill in destructured argument lists in function definitions.
4. **point-2d-abstract** — Build a 2D point abstraction with constructor, selectors, and distance.
5. **rational-arithmetic** — SICP rational number package: `make-rat`, `add-rat`, `mul-rat`.
6. **deck-of-cards** — Model a 52-card deck as data with deal and filter operations.
7. **nested-update** — Use `update-in` to modify deeply nested maps.
8. **binary-tree-of-data** — Immutable BST as nested maps with insert, contains, and traversal.
9. **interval-arithmetic** — SICP interval arithmetic with add, multiply, and width.
10. **inventory-system** — Design a data shape and implement store inventory operations.

## When you're done

You can model any domain as plain Clojure data — maps, vectors, sets — and
build abstraction barriers that hide representation choices behind constructors
and selectors. You understand that "data first" is not a slogan; it's a
concrete design technique that eliminates most of the boilerplate you'd write
in a class-based language.

## Where to go deeper

- *Structure and Interpretation of Computer Programs*, Sections 2.1–2.2
- [Clojure Reference — Data Structures](https://clojure.org/reference/data_structures)
- Rich Hickey, "The Value of Values" (talk)
