# Exercise 08: Binary Tree of Data

## What you're learning

Representing a binary search tree as nested maps — no classes, no records, just
data. This demonstrates that even non-trivial data structures are
straightforward to build from Clojure's persistent maps.

## The problem

Implement an immutable binary search tree where each node is a map
`{:value v :left l :right r}` and `nil` represents an empty tree.

- `bst-insert`: insert a value into the BST, returning a new tree.
- `bst-contains?`: return true if the value is in the tree.
- `bst-in-order`: return a sequence of all values in sorted order
  (in-order traversal).

Duplicate values should be ignored (inserting an existing value returns the
tree unchanged).

## The interface

```clojure
(defn bst-insert [tree val])
(defn bst-contains? [tree val])
(defn bst-in-order [tree])
```

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-08-binary-tree-of-data-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_08_binary_tree_of_data.clj`.
