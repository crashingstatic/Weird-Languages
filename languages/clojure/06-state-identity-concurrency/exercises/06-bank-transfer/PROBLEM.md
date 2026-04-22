# Exercise 06: Bank Transfer

## What you're learning

When you need to update **multiple** identities atomically, atoms aren't enough.
Refs and `dosync` give you STM (Software Transactional Memory) — a transaction
that either commits all changes or retries from scratch.

## The problem

Implement:

- `make-account` — creates a ref-backed account
- `get-balance` — reads the balance
- `transfer` — atomically moves money from one account to another; returns
  `true` on success, `false` if insufficient funds
- `total-balance` — sums all account balances (also in a transaction)

The test spawns concurrent opposing transfers and asserts that total money is
invariant.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-06-bank-transfer-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/state_identity_concurrency/ex_06_bank_transfer.clj`.
