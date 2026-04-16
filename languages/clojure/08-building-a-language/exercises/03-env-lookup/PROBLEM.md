# Exercise 03: Environment Lookup

## What you're learning

Variables need an environment to live in. In our evaluator, an environment is a
list of **frames**. Each frame is an atom containing a map of
`{name-string -> value}`. The first frame is the innermost scope; the last is
the outermost.

Looking up a variable means walking the chain from innermost to outermost until
you find it (or throw).

## The problem

Fill in `lookup-var`. It receives an environment (list of frames) and a
variable name (string). Walk the frames from first to last:

- If the frame contains the name, return the value.
- If not, recur on the rest of the frames.
- If you run out of frames, throw with `"Unbound variable"`.

**Hint:** `@(first env)` dereferences the atom in the first frame.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-03-env-lookup-test
```

**Starter file:** `src/clojure_course/building_a_language/ex_03_env_lookup.clj`
