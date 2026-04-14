# Exercise 02: Unless Macro

## What you're learning

`defmacro` defines a compile-time function that transforms code. The simplest
macros rearrange their arguments into existing forms.

## The problem

Fill in the body of `unless`. It should expand to `if` with branches swapped:

```clojure
(unless condition then-expr else-expr)
;; expands to: (if condition else-expr then-expr)
```

## Hints

- `` `(if ~condition ~else-expr ~then-expr) ``

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-02-unless-macro-test
```

**Starter file:** `src/clojure_course/macros_and_code_as_data/ex_02_unless_macro.clj`
