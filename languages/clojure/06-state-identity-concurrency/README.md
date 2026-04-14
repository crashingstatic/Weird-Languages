# Aspect 06: State, Identity, and Concurrency

## What this teaches

Clojure's model separates three ideas that most languages conflate:

- A **value** is immutable — the number 5, the map `{:a 1}`.
- An **identity** is a logical entity that takes on different values over time.
- **State** is the value an identity has at a given moment.

Reference types manage identity. Each is suited to a different concurrency story:

| Reference | Coordination | Concurrency model |
|-----------|-------------|-------------------|
| `atom` | Uncoordinated, synchronous | Single identity, CAS retry |
| `ref` | Coordinated, synchronous | Multiple identities, STM transactions |
| `agent` | Uncoordinated, asynchronous | Single identity, message queue |

## Why it matters

A Java programmer says "this counter has the value 5." A Clojure programmer says
"the counter identity currently refers to the immutable value 5; in a moment it
may refer to the immutable value 6." This sounds like word games until you've
debugged a race condition that the Clojure formulation makes literally impossible
to write.

## SICP mapping

Chapter 3.1–3.4: assignment, local state, the environment model, concurrency,
streams as an alternative to assignment.

## Prerequisites

Aspects 01–04 (Functional Foundations, Higher-Order Functions, Recursion, Data
and Abstraction). Aspect 05 helpful but not required.

## Vocabulary

| Term | What it is |
|------|-----------|
| `atom` | Uncoordinated reference; updated with `swap!` or `reset!` |
| `swap!` | Atomically applies a function to an atom's value (CAS loop) |
| `reset!` | Sets an atom's value directly |
| `ref` | Coordinated reference; updated inside `dosync` transactions |
| `dosync` | Starts an STM transaction |
| `alter` | Updates a ref inside a transaction |
| `commute` | Like `alter` but allows reordering (for commutative ops) |
| `agent` | Asynchronous reference; updated with `send` / `send-off` |
| `send` | Dispatches a function to an agent (CPU-bound pool) |
| `send-off` | Dispatches to an agent (I/O-bound pool) |
| `await` | Blocks until all dispatched actions on agents complete |
| `deref` / `@` | Reads the current value of any reference type |

## Exercises

| # | Slug | Tier | Key concept |
|---|------|------|-------------|
| 01 | atom-counter | T1 | `swap!` to increment an atom |
| 02 | ref-balance | T1 | `dosync` + `alter` to modify a ref |
| 03 | agent-logger | T1 | `send` to append to an agent's state |
| 04 | thread-safe-counter | T2 | Atom under contention (100 futures x 1000 increments) |
| 05 | bank-account | T2 | Atomic withdraw that rejects negative balance |
| 06 | bank-transfer | T2 | Refs + STM for multi-account transfers |
| 07 | memoize-with-atom | T2 | Atom-backed memoization cache |
| 08 | thread-safe-queue | T3 | FIFO queue with concurrent producers/consumers |
| 09 | agent-counter-ring | T3 | Ring of agents passing a token |
| 10 | deadlock-and-fix | T3 | Diagnose and fix a deadlock using STM |

## When you're done

You should be able to:

- Choose between atoms, refs, and agents for a given problem.
- Write correct concurrent code without explicit locks.
- Explain why Clojure's STM eliminates entire classes of concurrency bugs.
- Build thread-safe data structures using reference types.

## Where to go deeper

- SICP 3.1–3.4 (assignment, environment model, concurrency)
- Clojure Reference: [Atoms](https://clojure.org/reference/atoms)
- Clojure Reference: [Refs and Transactions](https://clojure.org/reference/refs)
- Clojure Reference: [Agents](https://clojure.org/reference/agents)

## Run all tests for this aspect

```bash
clojure -M:test -d clojure-course.state-identity-concurrency
```

Or run a single exercise:

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-01-atom-counter-test
```
