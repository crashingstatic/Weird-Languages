# Exercise 08: Let Desugaring

## What you're learning

`let` is syntactic sugar. It doesn't need to be a primitive in the evaluator —
it can be **desugared** (transformed) into a lambda application:

```
(let ((x 1) (y 2)) body)  =>  ((lambda (x y) body) 1 2)
```

`let*` allows each binding to see the earlier ones. It desugars to nested
`let`s:

```
(let* ((x 1) (y (+ x 1))) body)
  => (let ((x 1)) (let* ((y (+ x 1))) body))
```

## The problem

Implement two functions that transform expressions **without evaluating them**:

**(a)** `desugar-let` — transform a `let` expression into a lambda application.

**(b)** `desugar-let*` — transform a `let*` into nested `let`s. Handle the
base cases: zero bindings → `(begin body...)`, one binding → plain `let`.

The eval dispatch calls `(tiny-eval (desugar-let exp) env)`, so your functions
return data — they do not call eval themselves.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-08-let-desugaring-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/building_a_language/ex_08_let_desugaring.clj`.
