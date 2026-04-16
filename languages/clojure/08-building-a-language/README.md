# Aspect 08: Building a Language — A Tiny Evaluator

## What this teaches

You are going to implement a small Scheme-like language in Clojure. By the end
of this aspect you will have built `eval` and `apply`, a working environment
model with proper lexical scoping, and a REPL. You will understand what was
happening under the hood the entire time you were writing Clojure.

The language you build — call it **Tiny** — supports:

- Numbers, strings, booleans
- Variables and `define`
- `if`, `quote`, `begin`, `cond`
- `lambda` with lexical closure
- Primitive procedures (`+`, `-`, `*`, `/`, `=`, `<`, `>`, `not`, ...)
- `let` and `let*` (desugared to lambda application)

That is enough to write recursive functions, higher-order functions, and
closures — in a language you wrote yourself.

## Why it matters

Most programmers go their entire careers without writing an interpreter for the
language they write in. SICP's central pedagogical move is that once you have
done this, you understand evaluation and scoping at a level that nothing else
can teach you. Clojure is a good substrate because its data representations are
so close to s-expressions that you can read and write the AST as native Clojure
data.

## SICP mapping

Chapter 4.1: the metacircular evaluator. Structure and Interpretation of
Computer Programs builds the evaluator in stages — self-evaluating expressions,
variables, special forms, procedures, environments. This aspect follows the
same progression.

## Prerequisites

Aspects 01–07. This is the final aspect for a reason — you need comfort with
recursion, higher-order functions, data abstraction, and macros before you can
build a language.

## The exercises

| # | Name | Tier | What you build |
|---|------|------|----------------|
| 01 | parse-atom | T1 | Convert string tokens to Clojure values |
| 02 | eval-self-evaluating | T1 | Numbers, strings, booleans evaluate to themselves |
| 03 | env-lookup | T1 | Walk a chain of environment frames |
| 04 | extend-env-and-define | T2 | Create frames, define variables |
| 05 | if-and-quote | T2 | Conditional evaluation and quoting |
| 06 | primitive-procedures | T2 | Built-in operations (+, -, *, ...) |
| 07 | lambda-and-application | T2 | Closures and procedure application |
| 08 | let-desugaring | T3 | `let` and `let*` as syntactic sugar |
| 09 | closures-and-lexical-scope | T3 | Fix a scoping bug, pass closure tests |
| 10 | tiny-repl | T3 | Read-eval-print loop for your language |

## Key concepts

- **Self-evaluating expressions:** numbers, strings, and booleans are their own
  values.
- **Environment model:** a chain of frames (each frame is a map of names to
  values). Variable lookup walks the chain from innermost to outermost.
- **Special forms:** expressions like `if`, `define`, `lambda`, `quote` that
  the evaluator handles specially — they do not evaluate all their subexpressions.
- **Closure:** a `lambda` captures the environment where it was *defined*, not
  where it is *called*. This is lexical scoping.
- **Desugaring:** `let` is syntactic sugar for lambda application. The evaluator
  transforms `(let ((x 1)) body)` into `((lambda (x) body) 1)` before
  evaluating it.

## Running the tests

```bash
# Single exercise
clojure -M:test -n clojure-course.building-a-language.ex-01-parse-atom-test

# All exercises
clojure -M:test -n clojure-course.building-a-language.ex-01-parse-atom-test \
  -n clojure-course.building-a-language.ex-02-eval-self-evaluating-test \
  -n clojure-course.building-a-language.ex-03-env-lookup-test \
  -n clojure-course.building-a-language.ex-04-extend-env-and-define-test \
  -n clojure-course.building-a-language.ex-05-if-and-quote-test \
  -n clojure-course.building-a-language.ex-06-primitive-procedures-test \
  -n clojure-course.building-a-language.ex-07-lambda-and-application-test \
  -n clojure-course.building-a-language.ex-08-let-desugaring-test \
  -n clojure-course.building-a-language.ex-09-closures-and-lexical-scope-test \
  -n clojure-course.building-a-language.ex-10-tiny-repl-test
```
