# Exercise 05: Bank Account

## What you're learning

Some atom updates need conditional logic — "only withdraw if the balance is
sufficient." You can't just `swap!` with subtraction because that might produce
a negative balance. You need a CAS loop that checks the condition atomically.

## The problem

Implement a single bank account using an atom:

- `make-account` — creates an account with initial balance
- `get-balance` — reads the balance
- `deposit` — always succeeds, adds amount
- `withdraw` — returns `true` and decrements if sufficient funds, `false` otherwise

The withdraw must be atomic — no negative balances even under contention.

## Hints

- `compare-and-set!` lets you CAS: read, check, write atomically.
- Or use `swap!` with a function that conditionally updates.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-05-bank-account-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/state_identity_concurrency/ex_05_bank_account.clj`.
