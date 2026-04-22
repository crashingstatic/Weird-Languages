# Exercise 08: Log Context

## What you're learning

Dynamic vars (`^:dynamic`) provide thread-local state that can be rebound with
`binding`. A macro can wrap `binding` + `try`/`finally` to ensure cleanup.

## The problem

Implement:

1. A dynamic var `*log-context*` (initially `{}`)
2. `get-context` — returns the current context map
3. `with-log-context` macro — merges bindings into the context, evaluates body,
   restores context on exit (even on exception)

```clojure
(with-log-context {:request-id "abc"}
  (with-log-context {:user "alice"}
    (get-context)))
;=> {:request-id "abc" :user "alice"}
```

## Run the tests

```bash
clojure -M:test -n clojure-course.macros-and-code-as-data.ex-08-log-context-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/macros_and_code_as_data/ex_08_log_context.clj`.
