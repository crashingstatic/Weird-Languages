# Exercise 10: Vending Machine

## What you're learning

Using `loop`/`recur` to drive a state machine. The state is an immutable map
that gets transformed by each input event. This pattern — "fold a sequence of
events into a final state" — appears everywhere in real Clojure code: parsers,
game loops, protocol handlers, UI reducers.

## The problem

Implement `run-machine`: given an initial machine state and a sequence of
command vectors, process each command in order and return the final state.

The machine state is a map:
```clojure
{:balance 0      ;; cents inserted by the customer
 :inventory {...} ;; map from item keyword to {:price p :qty q}
 :dispensed []}   ;; items dispensed this session
```

Commands are two-element vectors `[action arg]`:

- `[:insert amount]` — add `amount` cents to `:balance`.
- `[:select item]` — if `item` exists in inventory, has quantity > 0, and the
  balance covers the price: decrement quantity, subtract price from balance,
  and conj `item` onto `:dispensed`. Otherwise, do nothing (no error).
- `[:refund]` — set `:balance` to 0 (the customer gets their money back; in
  this model we don't track the physical coins, just reset balance).

## The interface

```clojure
(defn run-machine [state commands])  ;; returns the final state map
```

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-10-vending-machine-test
```

**Starter file:** `src/clojure_course/recursion_and_recur/ex_10_vending_machine.clj`
