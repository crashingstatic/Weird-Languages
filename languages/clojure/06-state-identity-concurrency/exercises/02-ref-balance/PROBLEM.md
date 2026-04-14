# Exercise 02: Ref Balance

## What you're learning

A **ref** is a coordinated reference type. Updates must happen inside a `dosync`
transaction. Multiple refs can be updated atomically within the same transaction.

## The problem

Fill in the blanks to implement:

1. `make-account` — creates a ref holding the initial balance
2. `deposit` — uses `dosync` and `alter` to add the amount
3. `get-balance` — dereferences the ref

## Hints

- `(ref 100)` creates a ref holding 100.
- `(dosync (alter r + amount))` updates a ref inside a transaction.
- `@r` reads the ref.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-02-ref-balance-test
```

**Starter file:** `src/clojure_course/state_identity_concurrency/ex_02_ref_balance.clj`
