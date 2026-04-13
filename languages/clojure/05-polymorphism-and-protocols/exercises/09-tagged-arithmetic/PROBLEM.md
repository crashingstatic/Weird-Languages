# Exercise 09: Tagged Arithmetic

## What you're learning

SICP 2.4.2 introduces the idea of "tagged data" — attaching a type tag to a
datum so that generic operations can dispatch on it. This is the foundation of
data-directed programming.

## The problem

Implement:

1. `attach-tag` — takes a tag keyword and a datum, returns a tagged value
2. `type-tag` — extracts the tag from a tagged value
3. `contents` — extracts the datum from a tagged value
4. `add` — a multimethod that dispatches on `[type-of-a type-of-b]` and supports:
   - `:integer + :integer` → integer
   - `:rational + :rational` → rational (rationals are `[numerator denominator]`)
   - `:integer + :rational` → rational
   - `:rational + :integer` → rational

## The interface

```clojure
(defn attach-tag [tag datum])
(defn type-tag [tagged])
(defn contents [tagged])
(defmulti add (fn [a b] [(type-tag a) (type-tag b)]))
```

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-09-tagged-arithmetic-test
```

**Starter file:** `src/clojure_course/polymorphism_and_protocols/ex_09_tagged_arithmetic.clj`
