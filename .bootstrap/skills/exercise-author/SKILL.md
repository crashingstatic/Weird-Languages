---
name: exercise-author
description: Use this skill whenever creating, editing, or reviewing a single course exercise in the Paradigms Course project. This skill encodes the mandatory write→starter→solution→test→verify loop that ensures every exercise actually works: tests must pass against the reference solution AND fail against the unmodified starter before the exercise is considered done. Use this skill any time you are about to author or modify a PROBLEM.md, starter file, test file, or solution file. Use it even when the user only says "make exercise N" or "fix the test for ex 7" — exercise authoring is exactly when this discipline matters most.
---

# Exercise Author

This skill encodes the mandatory authoring loop for course exercises. It is
deliberately strict because the most common agent failure mode on this kind
of project is producing exercises that *look* right but don't actually work:
tests that pass trivially, starters that don't compile, solutions that don't
match what the test expects.

## Prerequisite reading

Before authoring, read in this order:

1. `.bootstrap/COURSE_AUTHORING_SPEC.md` — the canonical project contract
2. `.bootstrap/clojure/ASPECTS.md` — the brief for the aspect you're working on
3. The `paradigm-course-spec` skill — directory layout, file conventions,
   T1/T2/T3 tiers
4. The `clojure-testing` skill — Clojure-specific test idioms (only when
   working on Clojure)

If any of those are missing from the repo, stop and tell the human — the
project hasn't been bootstrapped.

## The loop (mandatory, in this order)

For every single exercise, follow these steps in order. Do not skip steps.
Do not reorder steps. Do not batch multiple exercises through a single pass
of the loop — each exercise gets its own complete pass.

### Step 1: Write `PROBLEM.md`

Follow the format in section 4 of `.bootstrap/COURSE_AUTHORING_SPEC.md`.
Required sections, in order: title, "What you're learning", "The problem",
"The interface", "Constraints" (if any), "Hints" (optional), "Run the tests".

Be concrete about inputs and outputs. The student should be able to
understand what they're supposed to build without reading any other file.
Match the tone: direct, technical, no filler.

The "The interface" section names the exact functions and signatures the
test will call. This is non-optional even for T3 exercises — without it the
student doesn't know what to name their functions and the test won't find
them.

### Step 2: Write `starter.clj`

Create the starter file with the correct namespace declaration matching the
exercise's path. Apply the appropriate scaffolding tier for this exercise's
position in the course (see the spec table). T1 exercises use `___` blanks
with adjacent comments. T2 exercises have signature + docstring with a
sentinel-throwing body. T3 exercises have just the namespace and a function
listing comment.

The starter must parse cleanly even though the blanks/stubs make it
non-functional. Run a syntax check before moving on:

```bash
clojure -e "(load-file \"path/to/starter.clj\")" 2>&1 | head -20
```

If it doesn't parse, fix the syntax of your scaffolding before proceeding.

### Step 3: Write `.solutions/solution.clj`

Write a complete, idiomatic reference implementation in the same namespace
as the starter. This is the "obviously correct" implementation a working
Clojurist would write. Not transliterated Java. Not transliterated Python.

Comments in solutions explain **the paradigm point**, not the syntax. "This
is where the sequence abstraction from aspect 02 pays off" is a good
comment. "Use map to transform each element" is a bad comment.

Sanity-check the solution loads:

```bash
clojure -e "(load-file \"path/to/.solutions/solution.clj\")" 2>&1 | head -20
```

### Step 4: Write `test.clj`

Write a behavioral test that exercises the public API of the exercise. Use
`clojure.test`. Tests must:

- Import the exercise namespace under an alias (`:as sut` is convention).
- Call the public functions and assert on their return values or visible
  effects.
- NEVER parse the source file, NEVER use `slurp`/`read-string` on the
  starter, NEVER assert on the presence of specific syntax.
- Have timeouts for any test that calls a function that could plausibly
  loop forever. The `clojure-testing` skill has the idioms.
- Include enough cases to catch trivial cheats (e.g., a `square` function
  that hard-codes `4` for input `2` should fail at least one test case).

### Step 5: Run the test against the SOLUTION. Confirm PASS.

Use the file-swap mechanic from the spec:

```bash
cd <exercise-dir>
cp starter.clj /tmp/starter.bak.clj
cp .solutions/solution.clj starter.clj
clojure -M:test --focus '<test-namespace>'
```

The test must pass cleanly. Capture the actual output:

```
Ran 1 test containing 4 assertions.
0 failures, 0 errors.
```

Save this output to your working notes for this exercise.

### Step 6: Run the test against the unmodified STARTER. Confirm FAIL.

```bash
cp /tmp/starter.bak.clj starter.clj
clojure -M:test --focus '<test-namespace>'
```

The test MUST fail. If the test passes against the starter, the test is
trivially satisfiable and is broken. Capture the failure output:

```
ERROR in (sqrt-test) (...)
clojure.lang.ExceptionInfo: not implemented
Ran 1 test containing 4 assertions.
0 failures, 1 errors.
```

Save this output too.

### Step 7: If either gate fails, fix and repeat from step 5

Common fixes:
- **Test passes against starter:** test is too weak. Add more cases or
  tighter assertions. Check that the test is actually loading the starter
  namespace (not a stale solution).
- **Test fails against solution:** either the test is wrong or the solution
  is wrong. Read PROBLEM.md again. Whichever one disagrees with PROBLEM.md
  is the one to fix.
- **Both fail:** the solution and the test were written from different
  mental models. Re-read PROBLEM.md and align them.

If you fix and re-run twice and still can't get both gates green, STOP and
ask the human. Don't keep iterating blindly.

### Step 8: Mark the exercise done

Only after both gates pass, the exercise is done. Note in your working log:

```
EX NN-slug: DONE
  Solution gate: PASS (0 failures, 0 errors)
  Starter gate:  FAIL (1 failure, 1 error - "not implemented")
```

Move to the next exercise.

## Anti-patterns

These are the failure modes this skill exists to prevent. If you catch
yourself doing any of these, stop.

- **Writing all 10 starters before any tests.** You will produce starters
  that can't be tested. Author one exercise end-to-end at a time.
- **Writing the test before the solution.** Tempting but wrong here — you
  need a known-correct solution to validate the test. Spec says solution
  before test for a reason.
- **Skipping the starter-fail gate** because "obviously the stub will
  fail." Stubs sometimes accidentally satisfy weak tests. Run it.
- **Asserting on source structure.** If your test has `slurp` or
  `read-string` on a `.clj` file, you have written a structural test. Delete
  it and write a behavioral test.
- **Catching exceptions in the test to make it pass.** If the starter
  throws and your test wraps it in `try`/`catch` to swallow the error, the
  test is no longer a test.
- **Making the solution overcomplicated to match a complex test.** If the
  test is hard to satisfy with idiomatic code, the test is wrong, not the
  language.
- **Cleverness in the solution.** Solutions are teaching material. Boring
  is correct. If a Clojure programmer would be embarrassed to read the
  solution, rewrite it.

## Edge cases

- **Exercises that test exception behavior.** Use `is (thrown? ExceptionType ...)`.
  The starter stub `(throw (ex-info "not implemented" {}))` will throw the
  wrong exception type, so this still satisfies the starter-fail gate.
- **Exercises that test side effects (println, atom mutation).** Use
  `with-out-str` for output, deref atoms for state. The `clojure-testing`
  skill covers patterns.
- **Macro exercises (aspect 7).** Test the *expansion* with `macroexpand-1`,
  AND test that the expansion *runs* correctly. Both matter.
- **Interpreter exercises (aspect 8).** Each exercise extends the previous
  one's code. The starter for exercise N is the solution to exercise N-1
  plus stubs for the new feature. Tests can assume earlier features work.

## Reporting back to the human

When you finish an aspect (10 exercises), summarize:

```
Aspect NN: <title> — DONE
- 10/10 exercises authored
- 10/10 solution gates: PASS
- 10/10 starter gates:  FAIL (as expected)

Test command for the human to re-verify:
  clj -M:test --focus 'clojure-course.<aspect>.*'
```

The human will run the verification checklist independently to confirm.
