# Exercise 10: Deadlock and Fix

## What you're learning

Explicit locking (`locking`) can deadlock when two threads acquire locks in
opposite order. Clojure's STM (`dosync`/`alter`) eliminates this class of bug
entirely — transactions retry instead of blocking on locks.

## The problem

The starter file shows a buggy `transfer` that uses nested `locking`. Your task:
implement a correct version using refs and `dosync`.

- `make-account` — creates a ref-backed account
- `get-balance` — reads the balance
- `transfer` — atomically moves money; returns `true` on success, `nil` if
  insufficient funds

The test spawns concurrent opposing transfers that would deadlock the buggy
version. Your implementation must complete without deadlock and preserve the
total balance invariant.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-10-deadlock-and-fix-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/state_identity_concurrency/ex_10_deadlock_and_fix.clj`.
