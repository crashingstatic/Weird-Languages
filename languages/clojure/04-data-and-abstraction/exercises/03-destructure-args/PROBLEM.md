# Exercise 03: Destructuring Arguments

## What you're learning

Destructuring lets you bind names to parts of a data structure directly in a
function's argument list. Associative destructuring pulls values out of maps;
sequential destructuring pulls values out of vectors. This eliminates the
repetitive `(:key m)` calls you'd otherwise write.

## The problem

Rewrite two functions to use destructuring in their argument lists:

1. `full-name`: takes a person map and returns `"<first> <last>"`. Use
   associative destructuring `{:keys [first last]}` to bind `:first` and
   `:last` directly in the argument list.
2. `midpoint`: takes two points (each a vector `[x y]`) and returns their
   midpoint as a vector. Use sequential destructuring `[x1 y1]` and `[x2 y2]`
   to bind the coordinates in the argument list.

## The interface

```clojure
(defn full-name [person])       ;; person has :first and :last keys
(defn midpoint [p1 p2])         ;; p1, p2 are [x y] vectors; returns [mx my]
```

## Hints

- `{:keys [a b]}` destructures a map.
- `[x y]` destructures a vector.
- The midpoint of `[x1 y1]` and `[x2 y2]` is `[(/ (+ x1 x2) 2.0) (/ (+ y1 y2) 2.0)]`.

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-03-destructure-args-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_03_destructure_args.clj`.
