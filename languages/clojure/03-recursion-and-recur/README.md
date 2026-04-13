# Aspect 03: Recursion, Iteration, and `recur`

## What this teaches

Clojure runs on the JVM, which does not perform general tail-call optimization.
`recur` is the language-level mechanism that gives you constant-stack iteration
by jumping back to the top of a `loop` or function with new arguments. This
aspect teaches you when you need `recur`, when plain recursion is fine, and how
to recognize the difference.

## Why it matters

In Scheme, you write naive recursion and the runtime handles the rest. In
Clojure, naive recursion on a large input blows the stack — the JVM allocates a
new frame for every call and never reclaims them. `recur` is the fix, and the
accumulator pattern is how you restructure a computation so `recur` can express
it. Not every recursion can be flattened this way — tree recursion is the
counter-example — and knowing the boundary is essential.

## SICP mapping

Chapter 1.2 — Procedures and the Processes They Generate. The distinction
between linear-recursive and linear-iterative processes maps directly to the
distinction between plain recursion and `loop`/`recur` in Clojure.

## Prerequisites

Aspects 01 (Functional Foundations) and 02 (Higher-Order Functions and the
Sequence Library).

## Vocabulary introduced

`loop`, `recur`, accumulator pattern, linear-recursive process,
linear-iterative process, tree recursion, tail position.

## Exercises

1. **convert-to-recur** — Rewrite a naive recursive factorial to use `loop`/`recur`.
2. **tail-recur-sum** — Fill in a `recur` call in the tail position of a running sum.
3. **accumulator-pattern** — Supply the accumulator initial value and `recur` step for sum-to-n.
4. **my-reverse** — Reverse a sequence using `loop`/`recur`; must handle 100k elements.
5. **my-length** — Count elements using `loop`/`recur`; must handle 1M elements.
6. **my-nth** — Index into a sequence with bounds checking; throw on out-of-bounds.
7. **tree-sum** — Sum all numbers in a nested vector structure using tree recursion.
8. **count-occurrences** — Count a target value in an arbitrarily nested structure.
9. **my-flatten** — Flatten an arbitrarily nested structure into a flat sequence.
10. **vending-machine** — Drive a state machine via `loop`/`recur` over a sequence of inputs.

## When you're done

You can convert any linear recursion to a constant-stack `loop`/`recur` form
using the accumulator pattern. You can recognize tree recursion as the case
where `recur` alone isn't enough. You understand why the JVM constraint exists
and how Clojure's `recur` is a principled workaround rather than a limitation.

## Where to go deeper

- *Structure and Interpretation of Computer Programs*, Section 1.2
- [Clojure Reference — Special Forms: `recur`](https://clojure.org/reference/special_forms#recur)
- Joy of Clojure, Chapter 7.2 — "On Recursion"
