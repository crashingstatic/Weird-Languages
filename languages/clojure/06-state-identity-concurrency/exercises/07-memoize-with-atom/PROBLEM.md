# Exercise 07: Memoize with Atom

## What you're learning

An atom holding a map is a natural cache. `memoize-with-atom` wraps a function
so that repeated calls with the same arguments return a cached result instead of
recomputing.

## The problem

Implement `(memoize-with-atom f)` that returns a new function. The new function:

1. Checks the cache (an atom holding `{args result}`)
2. If found, returns the cached result
3. If not found, calls `f`, stores the result, and returns it

Must correctly cache `nil` results (not re-call `f` for nil).

## Hints

- `(find m key)` returns `[key val]` or `nil` — distinguishes "key mapped to nil" from "key absent."
- `(apply f args)` calls f with a sequence of arguments.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-07-memoize-with-atom-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/state_identity_concurrency/ex_07_memoize_with_atom.clj`.
