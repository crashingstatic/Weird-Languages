# Exercise 04: 2D Point Abstraction

## What you're learning

Data abstraction barriers: a constructor creates the representation, selectors
access it, and all other code uses only the constructor and selectors. If you
later change the representation (e.g., from a vector to a map), only the
barrier functions need to change — everything else stays the same.

## The problem

Implement a 2D point abstraction using vectors as the underlying
representation. Provide:

- `make-point`: constructs a point from x and y coordinates.
- `point-x`: returns the x coordinate.
- `point-y`: returns the y coordinate.
- `distance`: returns the Euclidean distance between two points.

The tests treat points as opaque — they only use your constructor and
selectors, never peek at the representation directly.

## The interface

```clojure
(defn make-point [x y])          ;; returns a point
(defn point-x [p])               ;; returns the x coordinate
(defn point-y [p])               ;; returns the y coordinate
(defn distance [p1 p2])          ;; returns Euclidean distance between p1 and p2
```

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-04-point-2d-abstract-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_04_point_2d_abstract.clj`.
