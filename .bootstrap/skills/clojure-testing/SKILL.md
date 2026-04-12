---
name: clojure-testing
description: Use this skill whenever writing or modifying Clojure test files (clojure.test) for the Paradigms Course, especially exercise tests under languages/clojure/. Covers clojure.test idioms, test namespace conventions, timeout patterns for tests that might infinite-loop, testing pure functions vs. stateful code (atoms/refs), testing macros (both expansion and runtime behavior), testing exceptions, testing output side-effects, and how to run a single test file from the command line. Use this skill any time you are about to write a deftest, an is, or invoke clojure -M:test.
---

# Clojure Testing

This skill covers Clojure-specific testing patterns for the Paradigms Course.
Use it together with the `exercise-author` skill, which encodes the
write→test→verify workflow this skill's idioms slot into.

## Project setup assumptions

The Clojure module uses `deps.edn` (Clojure CLI) rather than Leiningen for
the student container. The `:test` alias is configured to run
`clojure.test` via `cognitect-labs/test-runner`. A typical invocation from
the module root:

```bash
cd languages/clojure
clojure -M:test
```

To run a single test namespace:

```bash
clojure -M:test --focus 'clojure-course.functional-foundations.ex-05-newton-sqrt-test'
```

## Test namespace conventions

For exercise file:
```
01-functional-foundations/exercises/05-newton-sqrt/starter.clj
```
Namespace:
```clojure
(ns clojure-course.functional-foundations.ex-05-newton-sqrt)
```

Test file:
```
01-functional-foundations/exercises/05-newton-sqrt/test.clj
```
Namespace (note `-test` suffix):
```clojure
(ns clojure-course.functional-foundations.ex-05-newton-sqrt-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-05-newton-sqrt :as sut]))
```

`sut` = "system under test", a Clojure community convention. Use it.

## Basic deftest structure

```clojure
(deftest sqrt-test
  (testing "exact squares"
    (is (close? 2.0 (sut/sqrt 4)))
    (is (close? 4.0 (sut/sqrt 16))))
  (testing "non-square positives"
    (is (close? 1.41421356 (sut/sqrt 2))))
  (testing "edge cases"
    (is (close? 0.0 (sut/sqrt 0)))))
```

## Floating-point comparisons

Never assert exact float equality on computed results. Define a helper or
use a tolerance check:

```clojure
(defn close? [a b]
  (< (Math/abs (- a b)) 1e-6))
```

For the SICP Newton's method exercise, `1e-6` tolerance is fine. Tighten or
loosen depending on the algorithm.

## Testing exceptions

```clojure
(is (thrown? ArithmeticException (sut/divide 1 0)))
(is (thrown-with-msg? clojure.lang.ExceptionInfo #"negative" (sut/sqrt -1)))
```

The starter stub `(throw (ex-info "not implemented" {}))` throws
`clojure.lang.ExceptionInfo`. So `(is (close? 4.0 (sut/sqrt 16)))` against
the starter will produce an error (not a failure), satisfying the
starter-fail gate.

## Testing output side-effects

```clojure
(deftest greet-test
  (is (= "Hello, world!\n"
         (with-out-str (sut/greet "world")))))
```

`with-out-str` captures everything written to `*out*`. Use it for any
exercise where the function's purpose is to print.

## Testing functions that mutate atoms/refs

Construct a fresh state object inside the test, don't share state across
tests:

```clojure
(deftest counter-test
  (let [c (sut/make-counter)]
    (is (= 0 @c))
    (sut/inc-counter! c)
    (sut/inc-counter! c)
    (is (= 2 @c))))
```

If the exercise mandates a global atom (rare, but happens in early state
exercises), reset it explicitly:

```clojure
(use-fixtures :each
  (fn [t]
    (reset! sut/the-atom 0)
    (t)))
```

## Testing concurrency exercises (aspect 06)

For exercises that test thread-safety, spawn N futures and assert the final
state:

```clojure
(deftest thread-safe-counter-test
  (let [c (sut/make-counter)
        n 1000]
    (->> (repeatedly n #(future (sut/inc-counter! c)))
         doall
         (run! deref))
    (is (= n @c))))
```

`doall` materializes the lazy seq before deref, so all futures launch
before any blocking begins.

## Testing macros (aspect 07)

A macro test must verify TWO things:

1. The macro expands to the right shape.
2. The expanded code runs and produces the right result.

```clojure
(deftest unless-test
  (testing "expansion shape"
    (is (= '(if (clojure.core/not true) :a :b)
           (macroexpand-1 '(sut/unless true :a :b)))))
  (testing "runtime behavior"
    (is (= :a (sut/unless false :a :b)))
    (is (= :b (sut/unless true  :a :b)))))
```

For hygiene tests, write a deliberately tricky case where the caller has a
local with the same name as a symbol the macro internally uses. A
non-hygienic macro will break. Test that case explicitly — that's the lesson.

## Timeouts (CRITICAL)

A student's incorrect implementation might infinite-loop. Tests that don't
guard against this will hang the entire test suite. Use one of these
patterns:

### Per-assertion timeout

```clojure
(defn within [ms f & args]
  (let [fut (future (apply f args))
        result (deref fut ms ::timeout)]
    (when (= result ::timeout)
      (future-cancel fut)
      (throw (ex-info "test timed out" {:ms ms})))
    result))

(deftest fib-test
  (is (= 55 (within 1000 sut/fib 10))))
```

### Per-test timeout via fixture

```clojure
(use-fixtures :each
  (fn [t]
    (let [fut (future (t))]
      (when (= ::timeout (deref fut 5000 ::timeout))
        (future-cancel fut)
        (throw (ex-info "test timed out after 5s" {}))))))
```

The first pattern is more surgical (only the suspect call is timed); the
second is simpler. Pick based on the exercise.

For aspect 08 interpreter tests and the capstone milestone-7 recursive
rules tests, timeouts are mandatory — student infinite loops are common
enough that the test suite must catch them rather than hang.

## Running tests programmatically (for the verify gate)

The `exercise-author` skill requires running tests against both the
solution and the starter. The standard mechanic:

```bash
cd <exercise-dir>
cp starter.clj /tmp/starter.bak.clj
cp .solutions/solution.clj starter.clj
clojure -M:test --focus 'clojure-course.<aspect>.ex-NN-slug-test'   # → must PASS
cp /tmp/starter.bak.clj starter.clj
clojure -M:test --focus 'clojure-course.<aspect>.ex-NN-slug-test'   # → must FAIL
```

This is closer to what the student will actually do (their `starter.clj` is
the file the test loads) than playing tricks with `load-file`.

## Test output you should capture

For the `exercise-author` write-up, capture exactly this from each run:

Solution gate (PASS):
```
Testing clojure-course.functional-foundations.ex-05-newton-sqrt-test
Ran 1 test containing 4 assertions.
0 failures, 0 errors.
```

Starter gate (FAIL, expected):
```
Testing clojure-course.functional-foundations.ex-05-newton-sqrt-test
ERROR in (sqrt-test) (ex_05_newton_sqrt.clj:6)
clojure.lang.ExceptionInfo: not implemented
Ran 1 test containing 4 assertions.
0 failures, 1 errors.
```

## Where to read more

- `clojure.test` API: https://clojuredocs.org/clojure.test
- `cognitect-labs/test-runner`: https://github.com/cognitect-labs/test-runner
- Stuart Sierra on testing: https://stuartsierra.com/2016/05/19/clojure-test-runners
