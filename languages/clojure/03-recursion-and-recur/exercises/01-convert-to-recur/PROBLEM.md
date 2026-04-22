# Exercise 01: Convert to `recur`

## What you're learning

How to rewrite a naive recursive function as a constant-stack `loop`/`recur`
form. The naive version creates a new stack frame per call; the `recur` version
reuses a single frame. This is the fundamental move you'll make every time a
recursive function needs to handle large input without blowing the stack.

## The problem

A naive recursive `factorial` is provided in the starter. It works correctly
for small inputs but will overflow the stack for large ones. Rewrite it using
`loop`/`recur` and an accumulator. Most of the `loop`/`recur` structure is
already in place — you need to fill in the blanks.

## The interface

```clojure
(defn factorial [n])  ;; returns n! (n >= 0)
```

## Hints

- The accumulator starts at 1 (the identity for multiplication).
- Each `recur` step should multiply the accumulator by the current counter and
  decrement the counter.
- `recur` must be in tail position — here that means it's the only expression
  in its branch of the `if`.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-01-convert-to-recur-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/recursion_and_recur/ex_01_convert_to_recur.clj`.
