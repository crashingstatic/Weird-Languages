# Aspect 05: Polymorphism and Protocols

## What this teaches

Polymorphism in Clojure comes in two flavors:

- **Multimethods** — open dispatch on any function of the arguments. Totally
  flexible, slightly slower. You choose the dispatch function; methods are
  installed for specific dispatch values.
- **Protocols** — closed dispatch on the type of the first argument. Fast,
  JVM-friendly, the workhorse for day-to-day polymorphism.

They solve different problems. Sometimes you need to dispatch on a keyword tag
in your data. Sometimes you need to dispatch on the Java type. Sometimes you
need to dispatch on *two* argument types at once. Clojure separates the
*choice of dispatch axis* from the *implementation* — most OOP languages can't.

## Why it matters

A Java programmer's reflex is "make a class hierarchy." A Clojure programmer's
reflex is "what's the actual dispatch axis?" This aspect trains that reflex.

## SICP mapping

Chapters 2.4–2.5: generic operations, tagged data, dispatch on type,
data-directed programming, coercion.

## Prerequisites

Aspects 01–04 (Functional Foundations, Higher-Order Functions, Recursion,
Data and Abstraction).

## Vocabulary

| Term | What it is |
|------|-----------|
| `defmulti` | Declares a multimethod with a dispatch function |
| `defmethod` | Installs an implementation for a specific dispatch value |
| `derive` / `isa?` | Ad-hoc hierarchy — lets dispatch values inherit from parents |
| `defprotocol` | Declares a named set of polymorphic functions |
| `defrecord` | Defines a named type (Java class) with protocol implementations |
| `extend-protocol` | Extends a protocol to types defined elsewhere |
| `extend-type` | Extends one type to implement multiple protocols |
| `reify` | Creates a one-off anonymous object implementing protocols |

## Exercises

| # | Slug | Tier | Key concept |
|---|------|------|-------------|
| 01 | multimethod-shape-area | T1 | Fill in a `defmethod` for triangle |
| 02 | defprotocol-greet | T1 | Fill in a protocol declaration |
| 03 | defrecord-person | T1 | Fill in a `defrecord` implementing a protocol |
| 04 | shape-protocol | T2 | Protocol with `area`/`perimeter`, three records + `reify` |
| 05 | animal-multimethods | T2 | Multimethod hierarchy with `derive` |
| 06 | when-to-use-which | T2 | Convert multimethod to protocol — same tests |
| 07 | expr-eval-multi | T2 | Expression evaluator via multimethod dispatch |
| 08 | tree-visitor | T3 | Polymorphic tree visitor with protocols |
| 09 | tagged-arithmetic | T3 | SICP 2.4 tagged-data dispatch |
| 10 | generic-arithmetic | T3 | SICP 2.5 generic package with coercion |

## When you're done

You should be able to:

- Choose between multimethods and protocols for a given problem.
- Define hierarchies with `derive` and dispatch on them.
- Implement protocols with `defrecord`, `extend-type`, and `reify`.
- Build data-directed systems where new types don't require editing
  existing code.

## Where to go deeper

- SICP 2.4–2.5 (tagged data, data-directed programming, coercion)
- Clojure Reference: [Multimethods and Hierarchies](https://clojure.org/reference/multimethods)
- Clojure Reference: [Protocols](https://clojure.org/reference/protocols)
- Clojure Reference: [Datatypes (defrecord, deftype)](https://clojure.org/reference/datatypes)

## Run all tests for this aspect

```bash
clojure -M:test -d clojure-course.polymorphism-and-protocols
```

Or run a single exercise:

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-01-multimethod-shape-area-test
```
