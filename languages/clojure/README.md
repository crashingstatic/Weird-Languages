# Clojure Module

This module teaches functional programming, data-driven design, and
metaprogramming through Clojure — a modern Lisp on the JVM. The pedagogical
sequence is aligned with SICP (*Structure and Interpretation of Computer
Programs*), adapted for idiomatic Clojure rather than Scheme.

By the end of the module you will have built a working interpreter for a tiny
Lisp (aspect 08) and a mini-Datalog query engine (capstone). You will think
about programs as expressions that produce values rather than statements that
mutate state.

## Prerequisites

- An undergraduate CS degree or equivalent systems knowledge.
- ~3 years of professional experience in C-family languages (C, C++, Java,
  Python, Go, Rust, etc.).
- Comfort with closures, recursion, hash tables, and unit tests.
- A working terminal and text editor.

You do **not** need prior Lisp or Clojure experience. That's what this module
teaches.

## Getting started

### Dev environment

The student devcontainer in `.devcontainer/` has everything pre-installed:
JDK, Clojure CLI, clj-kondo (linter), and rlwrap (REPL ergonomics). Open
this directory in VS Code with the Dev Containers extension, or use GitHub
Codespaces.

> **Note:** The devcontainer is added in Phase 4 of course authoring. If it
> doesn't exist yet, install Clojure manually:
> [Clojure Getting Started](https://clojure.org/guides/getting_started).

### Running tests

From this directory (`languages/clojure/`):

```bash
# Run all tests
clojure -M:test

# Run a single exercise's test
clojure -M:test --focus 'clojure-course.functional-foundations.ex-01-hello-values-test'
```

Each exercise's `PROBLEM.md` includes the exact command to run its tests.

## Module structure

See [SYLLABUS.md](SYLLABUS.md) for the full ordered list with descriptions and
time estimates. The short version:

| # | Aspect | SICP alignment |
|---|--------|---------------|
| 01 | Functional Foundations | Ch. 1.1-1.2 |
| 02 | Higher-Order Functions and the Sequence Library | Ch. 1.3 |
| 03 | Recursion, Iteration, and `recur` | Ch. 1.2 (Clojure-specific) |
| 04 | Data and Abstraction | Ch. 2.1-2.2 |
| 05 | Polymorphism and Protocols | Ch. 2.4-2.5 |
| 06 | State, Identity, and Concurrency | Ch. 3.1-3.4 |
| 07 | Macros and Code-as-Data | Ch. 4 (framing) |
| 08 | Building a Language: A Tiny Evaluator | Ch. 4.1 |
| -- | **Capstone:** Mini-Datalog Query Engine | Original |

Each aspect has 10 exercises that progress from fill-in-the-blank (exercises
01-03) to guided implementation (04-07) to open-ended problems (08-10). The
scaffolding decreases as you go — by the end of each aspect, you're writing
Clojure from scratch.

## How exercises work

Each exercise has three parts spread across the standard Clojure layout:

```
01-functional-foundations/exercises/05-newton-sqrt/
├── PROBLEM.md                          # what to build and why
└── .solutions/
    └── solution.clj                    # reference implementation

src/clojure_course/functional_foundations/
└── ex_05_newton_sqrt.clj               # your working file (starter)

test/clojure_course/functional_foundations/
└── ex_05_newton_sqrt_test.clj          # behavioral tests (read-only)
```

1. Read `PROBLEM.md`.
2. Edit the starter file in `src/`.
3. Run the test command from `PROBLEM.md`.
4. Iterate until tests pass.

### About solutions

Reference solutions live in `.solutions/` (dot-prefixed, hidden from plain
`ls`). They are checked into git, not encrypted, not secret. Look at them
whenever you're stuck — the dot is a gentle nudge, not a lock.

## Where to go next

*After* completing the capstone (not before):

**Libraries and ecosystems:**
- **core.async** — CSP-style channels and go blocks for async programming
- **ClojureScript** — Clojure compiled to JavaScript for browser/Node work
- **Datomic** — an immutable database built on Datalog (your capstone is a toy
  version of its query engine)
- **Reagent / Re-frame** — ClojureScript React wrappers for frontend apps

**Books:**
- *Programming Clojure* (Miller, Halloway) — the standard reference
- *Clojure Applied* (Vandgrift, Miller) — practical patterns for real projects
- *The Joy of Clojure* (Fogus, Houser) — deeper into the philosophy

**Community:**
- [Clojurians Slack](https://clojurians.slack.com) — the main community hub
- [ClojureVerse](https://clojureverse.org) — forum for longer discussions
- [Clojure Style Guide](https://guide.clojure.style/) — the idiomaticity
  reference used throughout this course
