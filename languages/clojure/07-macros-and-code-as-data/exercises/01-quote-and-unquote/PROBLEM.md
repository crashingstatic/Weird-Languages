# Exercise 01: Quote and Unquote

## What you're learning

`quote` prevents evaluation. Syntax-quote (`` ` ``) namespace-qualifies symbols.
`~` (unquote) evaluates inside a syntax-quote. `~@` (unquote-splicing) evaluates
and splices a sequence into the parent form.

## The problem

Fill in the blanks:

1. `quoted-symbol` — return the symbol `hello`
2. `make-inc-form` — given `x`, return `(inc x)` as data (not evaluated)
3. `make-sum-form` — given a vector of args, return `(+ arg1 arg2 ...)` as data

## Hints

- `'hello` returns the symbol hello.
- `` `(inc ~x) `` returns a list with inc and the value of x.
- `` `(+ ~@args) `` splices args into the + form.

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-01-quote-and-unquote-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/macros_and_code_as_data/ex_01_quote_and_unquote.clj`.
