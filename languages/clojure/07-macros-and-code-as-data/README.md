# Aspect 07: Macros and Code-as-Data

## What this teaches

Code is data. A Clojure program is a tree of lists, vectors, maps, symbols, and
literals. **Macros** are functions that take that tree and return a new tree,
which then gets evaluated. The macro system lets you extend the language itself.

It is also dangerous and overused. The most important lesson is **when not to
write a macro.**

## Why it matters

Every other language has a fixed grammar. In Clojure, you can add new syntactic
forms. This is genuinely something you can't do in any C-family language.
It's also the hardest aspect to teach because the temptation to use macros for
things functions can do is overwhelming, and resisting it is the actual lesson.

## SICP mapping

Chapter 4 (metalinguistic abstraction, framing). The evaluator aspect (08)
completes the SICP Chapter 4 arc.

## Prerequisites

Aspects 01-04. Aspect 06 helps for the `with-resource`-style examples.

## Vocabulary

| Term | What it is |
|------|-----------|
| `quote` / `'` | Prevents evaluation — returns the form as data |
| syntax-quote / `` ` `` | Like quote but namespace-qualifies symbols |
| `unquote` / `~` | Inside syntax-quote, evaluates the form |
| `unquote-splicing` / `~@` | Evaluates and splices a sequence into the parent |
| `defmacro` | Defines a macro — a compile-time function on code |
| `gensym` | Generates a unique symbol to avoid variable capture |
| `macroexpand` / `macroexpand-1` | Shows what a macro expands to |

## Exercises

| # | Slug | Tier | Key concept |
|---|------|------|-------------|
| 01 | quote-and-unquote | T1 | Syntax-quote, unquote, splicing |
| 02 | unless-macro | T1 | `defmacro` with `if` branch swap |
| 03 | when-positive | T1 | `defmacro` with conditional body |
| 04 | when-not-from-scratch | T2 | Macro that doesn't evaluate body when truthy |
| 05 | infix-macro | T2 | Operator precedence in macro expansion |
| 06 | with-timing | T2 | `gensym` to avoid variable capture |
| 07 | cond-thread | T2 | Simplified `cond->` macro |
| 08 | log-context | T3 | Dynamic var + `try`/`finally` in a macro |
| 09 | state-machine-dsl | T3 | Declarative DSL compiled by a macro |
| 10 | when-not-to-write-a-macro | T3 | Anti-pattern identification |

## When you're done

You should be able to:

- Read and write syntax-quoted templates with `~` and `~@`.
- Implement simple control-flow macros.
- Use `gensym` to write hygienic macros.
- Articulate when a function is better than a macro.

## Where to go deeper

- SICP Chapter 4 (metalinguistic abstraction)
- Clojure Reference: [Macros](https://clojure.org/reference/macros)
- Clojure Reference: [Reader](https://clojure.org/reference/reader)

## Run all tests for this aspect

```bash
clojure -M:test -d clojure-course.macros-and-code-as-data
```

Or run a single exercise:

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-01-quote-and-unquote-test
```
