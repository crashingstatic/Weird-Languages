# Exercise 03: Agent Logger

## What you're learning

An **agent** is an asynchronous reference type. `send` dispatches a function
that will be applied to the agent's state on a separate thread. Actions on a
single agent are processed in order.

## The problem

Fill in the blanks to implement:

1. `make-logger` — creates an agent holding an empty vector
2. `log-message` — uses `send` to `conj` a message onto the vector
3. `get-logs` — dereferences the agent

## Hints

- `(agent [])` creates an agent holding an empty vector.
- `(send a conj msg)` dispatches `conj` with the message.
- `@a` reads the agent's current value.
- Tests use `(await agent)` to wait for pending actions.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-03-agent-logger-test
```

**Starter file:** `src/clojure_course/state_identity_concurrency/ex_03_agent_logger.clj`
