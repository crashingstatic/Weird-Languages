# Exercise 03: Records Implementing Protocols

## What you're learning

`defrecord` creates a named type with fields. Records can implement protocols
inline, and their fields are accessible as keywords (`:name`, `:role`, etc.).

## The problem

Fill in the blanks to:

1. Give the `Employee` record three fields: `name`, `role`, `salary`.
2. Implement `describe` for `Employee`: `"<name> is a <role> earning $<salary>"`.
3. Implement `describe` for `Product`: `"<name> costs $<price>"`.

## The interface

```clojure
(describe (->Employee "Alice" "Engineer" 120000))
;=> "Alice is a Engineer earning $120000"

(describe (->Product "Widget" 9.99))
;=> "Widget costs $9.99"
```

## Hints

- `defrecord Employee [name role salary]` defines the fields.
- Use `str` to concatenate the parts.

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-03-defrecord-person-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_03_defrecord_person.clj`.
