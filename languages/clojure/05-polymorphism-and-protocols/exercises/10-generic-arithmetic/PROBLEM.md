# Exercise 10: Generic Arithmetic

## What you're learning

SICP 2.5.1 builds a generic arithmetic system where operations work across
multiple number types via type coercion. Lower types promote to higher types
when mixed: integer → rational → real.

## The problem

Build a generic arithmetic package over three types: `:integer`, `:rational`,
`:real`.

Implement:

- **Constructors:** `make-integer`, `make-rational`, `make-real`
- **Accessors:** `type-of`, `value-of`
- **Operations:** `add`, `mul`, `equ?`, `zero?`
- **Coercion:** when types differ, promote the lower type before operating

Rationals should be reduced to lowest terms. Integers are stored as plain
numbers; rationals as `[numerator denominator]`; reals as doubles.

## The interface

```clojure
(defn make-integer [n])
(defn make-rational [n d])
(defn make-real [x])
(defn type-of [x])
(defn value-of [x])
(defn add [a b])
(defn mul [a b])
(defn equ? [a b])
(defn zero? [a])
```

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-10-generic-arithmetic-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_10_generic_arithmetic.clj`.
