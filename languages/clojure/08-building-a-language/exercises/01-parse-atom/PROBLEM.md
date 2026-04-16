# Exercise 01: Parse Atom

## What you're learning

Every language needs a reader — something that turns a string of characters
into a data structure the evaluator can work with. For a Lisp, the reader
converts tokens like `"42"`, `"true"`, and `"+"` into numbers, booleans, and
symbols.

## The problem

The tokenizer and parser are provided. Your task is to fill in `parse-atom`,
which converts a single string token into the correct Clojure value:

- `"42"` or `"-7"` → a Long (integer)
- `"true"` → `true`
- `"false"` → `false`
- `"\"hello\""` → `"hello"` (strip the surrounding quotes)
- Anything else → a symbol (e.g. `"+"` → `'+`)

**Hint:** `Long/parseLong`, `re-matches`, `.startsWith`, `subs`, `symbol`.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-01-parse-atom-test
```

**Starter file:** `src/clojure_course/building_a_language/ex_01_parse_atom.clj`
