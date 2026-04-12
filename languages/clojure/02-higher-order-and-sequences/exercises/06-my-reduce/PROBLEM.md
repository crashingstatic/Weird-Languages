# Exercise 06: My Reduce

## What you're learning

`reduce` is the most general of the three core sequence operations. Unlike
`map` and `filter`, which always produce a collection, `reduce` can produce
any value — a number, a string, a map, another collection. Building it
yourself shows you why: the accumulator can be anything.

## The problem

Implement `(my-reduce f init coll)` and `(my-reduce f coll)`.

The two-arity version (no `init`) uses the first element of the collection
as the initial accumulator and reduces over the rest. If the collection is
empty in the two-arity case, call `(f)` with no arguments (this is what
Clojure's `reduce` does — `(+ )` returns 0, `(* )` returns 1).

- `(my-reduce + 0 [1 2 3])` => `6`
- `(my-reduce + [1 2 3])` => `6`
- `(my-reduce + [])` => `0`  (because `(+)` is `0`)
- `(my-reduce str "" ["a" "b" "c"])` => `"abc"`

Do not use the built-in `reduce`, `reductions`, or `transduce`.

## The interface

```clojure
(defn my-reduce
  ([f init coll] ...)    ;; three-arity: explicit initial value
  ([f coll] ...))        ;; two-arity: first element is initial value
```

## Hints

- The three-arity version recurs: apply `f` to the accumulator and the first
  element, then recur with the result as the new accumulator and the rest of
  the collection.
- The two-arity version can delegate to the three-arity version once you
  figure out the init and the remaining collection.

## Run the tests

From `languages/clojure/`:

```bash
clojure -M:test -n clojure-course.higher-order-seqs.ex-06-my-reduce-test
```

Your starter file: `src/clojure_course/higher_order_seqs/ex_06_my_reduce.clj`
