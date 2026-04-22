# Exercise 09: Agent Counter Ring

## What you're learning

Agents process messages in order on separate threads. A ring of agents passing
a token demonstrates message ordering and coordination via `send`.

## The problem

Build a ring of N agents:

- `make-ring` takes `n` (number of agents) and `counter` (an atom)
- `start-token` takes the ring and a number of `hops`
- Each hop: the current agent increments the counter atom, then sends the token
  to the next agent in the ring
- After all hops, `start-token` returns

## Hints

- `send` dispatches a function to an agent asynchronously.
- `await` blocks until all queued actions on the given agents complete.
- The token carries the remaining hop count.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-09-agent-counter-ring-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/state_identity_concurrency/ex_09_agent_counter_ring.clj`.
