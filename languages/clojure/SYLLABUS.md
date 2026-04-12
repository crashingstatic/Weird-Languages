# Clojure Syllabus

8 aspects, 80 exercises, one capstone. Work through them in order — later
aspects build on earlier ones.

**Time estimate:** ~4-8 hours per aspect for an experienced engineer. The
capstone is roughly equivalent to two aspects. Budget 50-80 hours total.

## Dependency graph

```
01 Functional Foundations
 └─► 02 Higher-Order Functions and the Sequence Library
      └─► 03 Recursion, Iteration, and recur
           └─► 04 Data and Abstraction
                ├─► 05 Polymorphism and Protocols
                ├─► 06 State, Identity, and Concurrency
                │    (05 helpful but not required)
                └─► 07 Macros and Code-as-Data
                     (06 helpful for with-resource examples)
                     └─► 08 Building a Language
                          └─► Capstone: Mini-Datalog
```

Aspects 05 and 06 can be done in either order after 04. Aspect 07 benefits
from exposure to 06 but does not require it. Aspect 08 requires all prior
aspects. The capstone requires everything.

---

## Aspect 01 — Functional Foundations

**SICP:** Chapters 1.1-1.2

Programs as expressions that produce values, not statements that mutate state.
Functions are the primary unit of abstraction. This aspect exists to flip the
mental switch from "what does this *do*?" to "what does this *evaluate to*?"
You'll work with `def`, `let`, `defn`, `if`, `cond`, `fn`, and the numeric
tower, building up from simple value bindings to recursive algorithms like
Newton's method and the SICP coin-change problem.

**Prerequisites:** None.

---

## Aspect 02 — Higher-Order Functions and the Sequence Library

**SICP:** Chapter 1.3

Functions are values. They take functions, return functions, and compose. The
sequence library (`map`, `filter`, `reduce`, `comp`, `partial`) is how Clojure
programmers express computation that a C programmer would write as a `for`
loop. You'll use the built-in sequence operations, then build them from scratch
so you understand they're not magic, then combine them into data-processing
pipelines.

**Prerequisites:** Aspect 01.

---

## Aspect 03 — Recursion, Iteration, and `recur`

**SICP:** Chapter 1.2 (Clojure-specific framing)

Clojure runs on the JVM, which doesn't do general tail-call optimization.
`recur` is the workaround — a language-level "jump to the top with new
arguments" that gives you constant-stack iteration. You'll learn when you need
`recur`, when you don't (tree recursion), and how the accumulator pattern
turns a stack-blowing recursion into a tight loop.

**Prerequisites:** Aspects 01, 02.

---

## Aspect 04 — Data and Abstraction

**SICP:** Chapters 2.1-2.2

"Data > Functions > Macros." The Clojure community models the world as
immutable data first and writes small functions to transform it. Persistent
collection literals (vectors, maps, sets) aren't afterthoughts — they're the
substrate. You'll shift from "what is the noun?" (classes, structs) to "what
is the shape of the data?" using destructuring, nested updates, and data
abstraction barriers.

**Prerequisites:** Aspects 01, 02, 03.

---

## Aspect 05 — Polymorphism and Protocols

**SICP:** Chapters 2.4-2.5

Polymorphism in Clojure comes in two flavors: multimethods (open dispatch on
any function of the arguments) and protocols (closed dispatch on the type of
the first argument). They solve different problems. You'll learn when a class
hierarchy is the wrong reflex and how Clojure separates the choice of dispatch
axis from the implementation.

**Prerequisites:** Aspects 01-04.

---

## Aspect 06 — State, Identity, and Concurrency

**SICP:** Chapters 3.1-3.4

Clojure's "value/identity/state" model: a value is immutable, an identity is a
logical entity that takes on different values over time, and state is the value
an identity has at a given moment. Atoms, refs, and agents are different
reference types for managing identity, each suited to a different concurrency
story. You'll build thread-safe counters, STM-backed bank transfers, and
understand why Clojure makes entire classes of race conditions impossible to
write.

**Prerequisites:** Aspects 01-04. Aspect 05 is helpful but not required.

---

## Aspect 07 — Macros and Code-as-Data

**SICP:** Chapter 4 (metalinguistic abstraction, framing)

Code is data. A Clojure program is a tree of lists, vectors, maps, symbols,
and literals. Macros are functions that take that tree and return a new tree,
which then gets evaluated. The macro system is the Lisp superpower — and the
most important lesson is when *not* to write a macro. You'll build macros from
`unless` to a state-machine DSL, then learn to recognize when a plain function
would have been better.

**Prerequisites:** Aspects 01-04. Aspect 06 is helpful for `with-resource`
style examples.

---

## Aspect 08 — Building a Language: A Tiny Evaluator

**SICP:** Chapter 4.1

Implement a small Scheme-like language in Clojure. By the end you'll have
built `eval` and `apply`, a working environment model with proper lexical
scoping, and a REPL. You'll understand what was happening under the hood the
entire time you were writing Clojure. This is where the course earns its
"rewires your brain" claim.

**Prerequisites:** Aspects 01-07. This is the final aspect for a reason.

---

## Capstone — Mini-Datalog Query Engine

After all 8 aspects, you build a small Datalog query engine: EAV fact storage,
pattern matching with logic variables, multi-clause joins, recursive rules, and
a thin macro layer for surface syntax. It ties together data modeling (aspect
04), sequence pipelines (02), recursion (03), polymorphic dispatch (05), and
the "macros should be thin" lesson (07). Eight milestones, ~300-500 lines of
Clojure.

See `capstone/README.md` for the full breakdown.

**Prerequisites:** All 8 aspects.
