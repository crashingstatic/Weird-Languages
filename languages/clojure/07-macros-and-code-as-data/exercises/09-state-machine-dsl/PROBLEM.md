# Exercise 09: State Machine DSL

## What you're learning

Macros can define domain-specific languages. `defstate-machine` takes a
declarative definition and emits a function that runs the state machine.

## The problem

Implement `defstate-machine`:

```clojure
(defstate-machine turnstile
  {:locked   {:coin :unlocked}
   :unlocked {:push :locked}})

(turnstile :locked [:coin :push :coin])
;=> :unlocked
```

The generated function takes an initial state and a sequence of events. For each
event, it looks up the transition; if none exists, the state is unchanged.

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-09-state-machine-dsl-test
```

**Starter file:** `src/clojure_course/macros_and_code_as_data/ex_09_state_machine_dsl.clj`
