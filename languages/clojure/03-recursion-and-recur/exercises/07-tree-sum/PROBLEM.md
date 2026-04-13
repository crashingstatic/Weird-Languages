# Exercise 07: tree-sum

## What you're learning

Not every recursion can be converted to `loop`/`recur`. Tree recursion — where
a function makes multiple recursive calls per step — is the canonical
counter-example. This exercise makes the boundary visible: `recur` handles
linear processes; tree processes need actual stack frames (or an explicit stack
you manage yourself).

## The problem

Implement `tree-sum`: given a nested vector structure where every leaf is a
number, return the sum of all leaves. The nesting depth is arbitrary.

For example:
- `(tree-sum [1 2 3])` → `6`
- `(tree-sum [1 [2 [3]] 4])` → `10`
- `(tree-sum [])` → `0`

A "leaf" is anything that is a number (satisfies `number?`). A "branch" is
anything that is sequential (satisfies `sequential?`).

## The interface

```clojure
(defn tree-sum [tree])  ;; returns the sum of all numeric leaves
```

## Constraints

- Must use plain recursion (not `loop`/`recur`) — this is tree-recursive by
  nature. Attempting to use `recur` here would require an explicit stack, which
  defeats the pedagogical point.

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-07-tree-sum-test
```

**Starter file:** `src/clojure_course/recursion_and_recur/ex_07_tree_sum.clj`
