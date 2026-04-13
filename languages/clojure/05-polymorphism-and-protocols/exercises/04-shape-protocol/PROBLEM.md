# Exercise 04: Shape Protocol

## What you're learning

Protocols can have multiple methods. Records implementing a protocol must
provide all of them. `reify` creates anonymous, one-off protocol implementations.

## The problem

Implement a `Shape` protocol with `area` and `perimeter`. Create three records:

- **Circle** (field: `radius`) — area = pi*r^2, perimeter = 2*pi*r
- **Rectangle** (fields: `width`, `height`) — standard formulas
- **Triangle** (fields: `a`, `b`, `c` — side lengths) — Heron's formula for area

Also implement `make-composite` that takes a collection of shapes and returns
a new Shape (via `reify`) whose area and perimeter are the sums of its parts.

## The interface

```clojure
(defprotocol Shape
  (area [this])
  (perimeter [this]))

(defn make-composite [shapes])  ;; returns a Shape
```

## Hints

- Heron's formula: `s = (a+b+c)/2`, area = `sqrt(s*(s-a)*(s-b)*(s-c))`.
- `Math/PI`, `Math/sqrt` are available.
- `reify Shape (area [this] ...) (perimeter [this] ...)` creates an anonymous impl.

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-04-shape-protocol-test
```

**Starter file:** `src/clojure_course/polymorphism_and_protocols/ex_04_shape_protocol.clj`
