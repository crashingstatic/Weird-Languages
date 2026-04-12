# Aspect 01: Functional Foundations

## What this teaches

Programs as expressions that produce values, not statements that mutate state.
You'll define bindings, write pure functions, use conditionals as expressions,
and build recursive algorithms — all in your first hours with Clojure.

## Why it matters

Your C-family reflex is "what does this *do*?" — a function runs, mutates
something, maybe returns a value as a side effect. A Lisp programmer's reflex
is "what does this *evaluate to*?" Until that switch flips, nothing else in
this course will land. Aspect 01 exists to flip it.

## SICP mapping

Chapters 1.1–1.2: elements of programming, procedures and the processes they
generate. The exercises track SICP's progression from simple expressions through
recursive and iterative processes.

## Prerequisites

None. This is the starting point.

## Vocabulary introduced

- `def` — bind a name to a value
- `let` — lexical bindings (local scope)
- `defn` / `fn` — define and create functions
- `if`, `cond` — conditionals as expressions
- Basic arithmetic: `+`, `-`, `*`, `/`, `mod`, `quot`, `rem`
- Numeric tower basics: integers, longs, ratios, doubles

## Exercises

1. **Hello Values** — bind names to numbers and strings with `def`
2. **Let Binding** — compute circle area using `let` for local bindings
3. **Absolute Value** — write a conditional expression with `if`
4. **Basic Arithmetic Functions** — implement `square`, `cube`, `average`, `distance-2d`
5. **Newton's Square Root** — recursive square root via Newton's method (SICP 1.1.7)
6. **Power (Recursive)** — compute `b^n` with simple recursion
7. **GCD (Euclid)** — implement Euclid's algorithm
8. **Fast Power** — compute `b^n` in O(log n) using square-and-halve
9. **Fibonacci Two Ways** — tree-recursive vs. linear-iterative Fibonacci
10. **Count Change** — SICP's coin-change counting problem

## When you're done

You can write pure functions in Clojure, use `let` for local bindings, express
conditionals as values, and implement both recursive and iterative algorithms.
You think in expressions, not statements — and you're ready to discover that
functions themselves are values.

## Where to go deeper

- SICP chapters 1.1–1.2: [full text online](https://mitp-content-server.mit.edu/books/content/sectbyfn/books_pres_0/6515/sicp.zip/index.html)
- *Programming Clojure* (Miller, Halloway) — chapters 1–3 cover the basics with more depth
- [Clojure Style Guide](https://guide.clojure.style/) — the idiomaticity reference
