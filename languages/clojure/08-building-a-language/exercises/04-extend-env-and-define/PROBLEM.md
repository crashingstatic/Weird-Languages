# Exercise 04: Extend Env and Define

## What you're learning

The environment grows in two ways: **extending** (adding a new frame when
entering a new scope) and **defining** (adding a binding to the current frame).

## The problem

Implement two functions:

**(a)** `extend-env` — takes an existing env, a vector of names, and a vector
of values. Creates a new frame (an atom containing a zipmap of names to values)
and conses it onto the front of the env.

**(b)** `define-var!` — takes an env, a name, and a value. Adds (or updates)
the binding in the **first** frame. Returns the value.

`lookup-var` is provided for testing.

## Run the tests

```bash
clojure -M:test -n clojure-course.building-a-language.ex-04-extend-env-and-define-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/building_a_language/ex_04_extend_env_and_define.clj`.
