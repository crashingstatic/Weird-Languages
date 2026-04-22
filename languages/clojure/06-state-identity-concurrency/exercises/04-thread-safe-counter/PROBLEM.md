# Exercise 04: Thread-Safe Counter

## What you're learning

Atoms use compare-and-swap (CAS) internally. When multiple threads call `swap!`
simultaneously, each thread's function may be retried, but the final value is
always correct. This exercise proves it under real contention.

## The problem

Implement a counter with:

- `make-counter` — starts at 0
- `increment` — adds 1
- `increment-by` — adds n
- `get-count` — reads the value
- `reset-counter` — resets to 0

The test spawns 100 futures each incrementing 1000 times and asserts the final
value is exactly 100,000.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-04-thread-safe-counter-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/state_identity_concurrency/ex_04_thread_safe_counter.clj`.
