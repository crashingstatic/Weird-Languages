# Exercise 08: Tree Visitor

## What you're learning

Protocols let you define operations that traverse recursive data structures.
A visitor pattern collects information as it walks a tree.

## The problem

Implement an `Expr` protocol with:

- `evaluate` — returns the numeric result
- `visit` — returns `{:result n :ops [list-of-strings]}` where `:ops` logs each
  operation (`"add"` or `"mul"`) in post-order (left subtree, right subtree,
  then the current node)

Create three record types:

- `Num` (field: `value`) — a leaf
- `Add` (fields: `left`, `right`) — addition
- `Mul` (fields: `left`, `right`) — multiplication

## The interface

```clojure
(defprotocol Expr
  (evaluate [this])
  (visit [this]))
```

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-08-tree-visitor-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_08_tree_visitor.clj`.
