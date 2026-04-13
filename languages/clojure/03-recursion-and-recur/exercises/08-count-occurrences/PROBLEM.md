# Exercise 08: count-occurrences

## What you're learning

Combining tree recursion (from exercise 07) with a counting accumulator to
search an arbitrarily nested structure. This reinforces the idea that tree
shapes require tree-recursive processes.

## The problem

Implement `count-occurrences`: given a target value and an arbitrarily nested
structure (vectors within vectors), return the number of times the target
appears as a leaf.

For example:
- `(count-occurrences :a [:a :b [:a :c [:a]]])` → `3`
- `(count-occurrences 1 [])` → `0`
- `(count-occurrences :x [:a :b :c])` → `0`

A "leaf" is anything that is not sequential.

## The interface

```clojure
(defn count-occurrences [target coll])  ;; returns the count of target in coll
```

## Run the tests

```bash
clojure -M:test -n clojure-course.recursion-and-recur.ex-08-count-occurrences-test
```

**Starter file:** `src/clojure_course/recursion_and_recur/ex_08_count_occurrences.clj`
