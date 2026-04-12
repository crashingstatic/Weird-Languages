# Course Authoring Spec

This is the contract for all agent work on the Paradigms Course. Every agent
prompt for this project tells the agent to read this file first. If anything
in this file is ambiguous, the agent must stop and ask the human rather than
guess.

The canonical location of this file is `.bootstrap/COURSE_AUTHORING_SPEC.md`
in the project repo. The bootstrap directory `.bootstrap/` is the source of
truth for project conventions, aspect briefs, capstone spec, custom skills,
and agent prompts.

## 1. Audience and tone

The student is a working software engineer with an undergraduate CS degree
and roughly three years of professional experience writing C-family languages
(C, C++, Python, Java, Rust, Go, etc.). They know what a variable, a
function, a closure, recursion, a stack, a heap, a hash table, and a unit
test are. They have used a debugger. They are not a beginner. **Do not**
explain what a function is, what immutability is in the abstract, or why
testing is good. **Do** explain what the *Clojure* take on these things is
and how it differs from what the student already knows.

Tone in PROBLEM.md and aspect READMEs: direct, technical, slightly opinionated.
Short sentences. Concrete examples over hand-waving. Assume the reader will
be annoyed by filler.

## 2. Top-level repo layout

```
paradigms-course/
├── README.md                       # course-level orientation
├── .bootstrap/                     # spec, briefs, skills, prompts (source of truth)
│   ├── COURSE_AUTHORING_SPEC.md
│   ├── VERIFICATION_CHECKLIST.md
│   ├── skills/{paradigm-course-spec,exercise-author,clojure-testing}/
│   ├── clojure/{ASPECTS.md,CAPSTONE.md}
│   └── agent-prompts/PHASE-{1,2,3,4}-*.md
└── languages/
    └── clojure/
        ├── README.md               # student-facing module orientation
        ├── SYLLABUS.md             # ordered aspects, time estimates, dependency graph
        ├── deps.edn                # module-shared deps with :test alias
        ├── .devcontainer/          # built in Phase 4 only
        │   ├── Dockerfile
        │   ├── devcontainer.json
        │   └── smoke-test.sh
        ├── src/                        # ** Clojure-specific — see note below **
        │   └── clojure_course/
        │       └── <aspect_slug>/
        │           ├── ex_01_<slug>.clj    # starter files
        │           └── ...
        ├── test/
        │   └── clojure_course/
        │       └── <aspect_slug>/
        │           ├── ex_01_<slug>_test.clj
        │           └── ...
        ├── 01-functional-foundations/
        │   ├── README.md
        │   └── exercises/
        │       ├── 01-<slug>/
        │       │   ├── PROBLEM.md
        │       │   └── .solutions/
        │       │       └── solution.clj
        │       ├── 02-<slug>/
        │       └── ... (10 exercises)
        ├── 02-higher-order-and-sequences/
        ├── 03-recursion-and-recur/
        ├── 04-data-and-abstraction/
        ├── 05-polymorphism-and-protocols/
        ├── 06-state-identity-concurrency/
        ├── 07-macros-and-code-as-data/
        ├── 08-building-a-language/
        └── capstone/
            ├── README.md
            ├── starter/
            ├── test/
            └── .solutions/
                └── reference/
```

Aspect directories use `NN-kebab-slug`. Exercise directories use
`NN-kebab-slug` inside `exercises/`. Two-digit zero-pad matters for sort order.
The capstone is `capstone/` (no slug suffix) and is structurally different
from aspects — it is one project, not 10 exercises.

### Language-specific file layout: Clojure

Clojure requires source filenames to match the last segment of the namespace
they declare, and namespace segments map to directory paths (with hyphens
replaced by underscores). This means generic names like `starter.clj` and
`test.clj` cannot be used — the file must be named after the exercise
namespace (e.g., `ex_01_hello_values.clj`).

Because of this constraint, Clojure starter and test files live in centralized
`src/` and `test/` trees under the language root, following standard Clojure
project conventions:

```
languages/clojure/
├── src/clojure_course/<aspect_slug>/ex_NN_<slug>.clj       # starters
├── test/clojure_course/<aspect_slug>/ex_NN_<slug>_test.clj # tests
└── NN-<aspect>/exercises/NN-<slug>/
    ├── PROBLEM.md
    └── .solutions/solution.clj
```

The exercise directory retains `PROBLEM.md` and `.solutions/` but does **not**
contain `starter.clj` or `test.clj`. Each `PROBLEM.md` includes the path to
the actual starter file so the student knows where to edit.

**Other languages should follow the original layout** with `starter.<ext>` and
`test.<ext>` inside the exercise directory, unless they have similar
namespace-to-filesystem constraints that make this impossible.

## 3. Scaffolding tiers

Every aspect's 10 exercises are split into three tiers. Help decreases
monotonically across tiers and across aspects.

| Tier | Exercises | Help level |
|---|---|---|
| **T1** | 01–03 | Fill-in-the-blank with explanatory comments next to each blank |
| **T2** | 04–07 | Function signature + docstring provided, body empty (or stub) |
| **T3** | 08–10 | PROBLEM.md describes behavior; student picks signatures and decomposition |

Special cases:

- **Aspect 1 ramps gentler than the others.** Exercise 01 of aspect 01 is
  the most heavily scaffolded thing in the entire course.
- **A later aspect that introduces new syntax** (`loop`/`recur`,
  `defmacro`, `defprotocol`, etc.) MAY use a T1-style fill-in-the-blank for
  exercise 01 to introduce that syntax — even if the rest of the aspect
  starts at T2.
- **Aspect 8 (interpreter) is cumulative.** Each exercise extends the
  previous one's code. The starter for exercise N is the solution to
  exercise N-1 plus stubs for the new feature.

If you ever feel the need to add MORE help to a later exercise than an
earlier one, the exercises are in the wrong order. Renumber them.

## 4. Exercise file conventions

### `PROBLEM.md`

Required sections in this order:

```markdown
# Exercise NN: <Title>

## What you're learning
<one paragraph: the concept this exercise teaches and how it builds on prior exercises>

## The problem
<concrete description of what the function(s) should do — inputs, outputs, edge cases>

## The interface
<exact function names and signatures the test will call. The student needs this even
in T3 exercises — without it the test won't find their code. Example:

    (defn balance [account]) ;; returns the account's current balance, in cents
    (defn deposit [account amount]) ;; returns a new account with `amount` added
>

## Constraints
<things the student MUST or MUST NOT do — e.g., "must use loop/recur, no global state">
<omit this section if there are no constraints>

## Hints
<2-4 bullets, optional, decreasing in number as exercises progress>
<T1 exercises usually have hints; T3 exercises usually have none>

## Run the tests
The exact shell command to run this exercise's test from the language root.
```

### Starter file (`starter.clj` or language-specific equivalent)

A runnable file with a namespace declaration matching the exercise path.
Contains the function skeletons. In Clojure, this file lives at
`src/clojure_course/<aspect>/<ex_NN_slug>.clj` instead of in the exercise
directory (see "Language-specific file layout" above). For T1
fill-in-the-blank exercises, blanks are marked `___` (three underscores) with
adjacent comments. Example:

```clojure
(ns clojure-course.functional-foundations.ex-01-hello-values)

;; TASK: Define `pi` as the value 3.14159.
(def pi ___)

;; TASK: Define `circle-area` as a function of one argument `r`
;; that returns pi times r squared.
(defn circle-area [r]
  ___)
```

For T2 exercises, the signature and docstring are present and the body is
empty or a sentinel:

```clojure
(ns clojure-course.functional-foundations.ex-05-newton-sqrt)

(defn sqrt
  "Compute the square root of n using Newton's method."
  [n]
  (throw (ex-info "not implemented" {})))
```

For T3 exercises, the namespace is present and a comment lists the
public functions to implement, but the student writes the signatures.

### Test file (`test.clj` or language-specific equivalent)

Uses `clojure.test`. Loads the starter namespace and exercises its public
API. In Clojure, this file lives at
`test/clojure_course/<aspect>/<ex_NN_slug>_test.clj` (see "Language-specific
file layout" above). **Tests verify behavior, not source.** Do not parse the student's
source file, do not check for the presence of specific function calls, do
not lint, do not `slurp`/`read-string` the starter. Just call the functions
and assert on results.

```clojure
(ns clojure-course.functional-foundations.ex-05-newton-sqrt-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.functional-foundations.ex-05-newton-sqrt :as sut]))

(deftest sqrt-test
  (testing "exact squares"
    (is (< (Math/abs (- (sut/sqrt 4)  2.0)) 1e-6))
    (is (< (Math/abs (- (sut/sqrt 16) 4.0)) 1e-6)))
  (testing "non-square positives"
    (is (< (Math/abs (- (sut/sqrt 2) 1.41421356)) 1e-6))))
```

Tests must time out cleanly on infinite loops. The `clojure-testing` skill
covers idioms.

### `.solutions/solution.clj`

A complete, idiomatic Clojure implementation. Same namespace as the starter.
**The test file MUST pass against this solution AND fail against the
unmodified starter.** This is the verification gate. See section 6.

## 5. Aspect directory README

Each `NN-aspect/README.md` is the student-facing front door to the aspect.
Required sections in this order:

```markdown
# Aspect NN: <Title>

## What this teaches
<2-3 sentences>

## Why it matters
<2-4 sentences contrasting with the C-family toolkit the student arrives with>

## SICP mapping
<which SICP chapters this aspect tracks, or "Clojure-specific" if none>

## Prerequisites
<which earlier aspects must be completed first>

## Vocabulary introduced
<the Clojure-specific symbols, special forms, and concepts this aspect adds>

## Exercises
1. **<title>** — <one-line summary>
...
10. **<title>** — <one-line summary>

## When you're done
<a 2-3 sentence statement of what the student can now do that they couldn't before>

## Where to go deeper
<2-3 links or book references for students who want more on this aspect>
```

The `clojure/ASPECTS.md` brief is the agent's input. The aspect README is
the student's output. They are not the same document — the agent rewrites
the brief for the student, dropping the agent-only context (e.g., the
explicit tier markers in exercise titles).

## 6. The write→test→verify loop (NON-NEGOTIABLE)

For every exercise, the agent MUST follow this loop in this order:

1. Write `PROBLEM.md`.
2. Write the starter file (`starter.clj` or language-specific equivalent).
3. Write `.solutions/solution.clj`.
4. Write the test file (`test.clj` or language-specific equivalent).
5. **Run** the test against the solution. Confirm it passes.
6. **Run** the test against the unmodified starter. Confirm it fails.
7. If either gate fails, fix and re-run from step 5.
8. Only then mark the exercise done.

This catches the most common failure mode: tests that pass trivially (test
imports the wrong namespace, asserts something always true) and tests that
fail for the wrong reason (starter has a syntax error so the test "fails"
before even running). The `exercise-author` skill bakes this loop in and
the `VERIFICATION_CHECKLIST.md` re-checks it.

The agent must capture the actual test output (pass on solution, fail on
starter) in their working notes so the human can spot-check.

A typical mechanic for swapping starter and solution during the gate.
In Clojure, the starter file is in `src/` (not the exercise directory), so
adjust the paths accordingly. The test runner flag is `-n` (not `--focus`).

```bash
# Generic (languages where starter.clj lives in the exercise directory):
cp .solutions/solution.clj /tmp/sol.clj
cp starter.clj /tmp/starter.bak.clj
cp /tmp/sol.clj starter.clj
clojure -M:test --focus '<test-namespace>'   # → must PASS
cp /tmp/starter.bak.clj starter.clj
clojure -M:test --focus '<test-namespace>'   # → must FAIL

# Clojure (starter lives in src/ tree):
STARTER=src/clojure_course/<aspect>/ex_NN_<slug>.clj
SOLUTION=NN-<aspect>/exercises/NN-<slug>/.solutions/solution.clj
cp "$STARTER" /tmp/starter.bak.clj
cp "$SOLUTION" "$STARTER"
clojure -M:test -n '<test-namespace>'        # → must PASS
cp /tmp/starter.bak.clj "$STARTER"
clojure -M:test -n '<test-namespace>'        # → must FAIL
```

## 7. Idiomaticity rule

Solutions must be idiomatic Clojure as a working Clojurist would write them.
Not transliterated Java. Not transliterated Python. If an exercise's "right"
solution would use `loop`/`recur` in idiomatic Clojure, the solution uses
that — even if a Java-style imperative loop would also pass the tests.

The agent should reference the *Clojure Style Guide*
(https://guide.clojure.style/) when in doubt. Boring is correct. Solutions
are teaching material.

Comments in solutions explain **the paradigm point**, not the syntax.
"This is where the sequence abstraction from aspect 02 pays off" is a good
comment. "Use map to transform each element" is a bad comment.

## 8. SICP alignment, where applicable

The Clojure aspects map to SICP chapters where the mapping is natural. The
mapping is documented in `clojure/ASPECTS.md`. Where SICP code is shown in
Scheme, the agent adapts it to idiomatic Clojure rather than transliterates.
The point is to preserve the *pedagogical sequence* and the *conceptual
content*, not the syntax.

Aspects with no clean SICP mapping (e.g., aspect 6 on
state/identity/concurrency, which is much more developed in Clojure than in
SICP) are explicitly marked Clojure-specific in the briefs.

## 9. Solution visibility

Solutions live in `.solutions/` (with the dot — hidden from `ls`, visible to
`ls -a`). They are **not** gitignored. They are **not** encrypted. The student
can read them whenever they want. The dot directory is a soft signal, not a
security boundary. The language-level README explains this so students aren't
surprised.

`ls` doesn't show `.solutions/`. `ls -a`, `git status`, `git ls-files`,
`find .`, and language build tools all see them normally.

For the capstone, the reference implementation lives at
`capstone/.solutions/reference/` and is a full mini-project (multiple files,
its own `deps.edn`, etc.) rather than a single solution file.

## 10. Student devcontainer vs. agent environment

The student devcontainer is a deliverable artifact built in Phase 4:
`languages/clojure/.devcontainer/Dockerfile` +
`languages/clojure/.devcontainer/devcontainer.json` +
`languages/clojure/.devcontainer/smoke-test.sh`.

During Phases 1–3, agents install Clojure tooling **directly into their
current environment** (the host devcontainer they're running in). They do
not run `docker build` during exercise authoring. They do not nest
containers. They just install `clojure`, `clj-kondo`, `rlwrap`, and any
other tools they need to run tests.

In Phase 4, the agent builds the student devcontainer as a separate artifact
and smoke-tests it. The agent's authoring environment and the student
devcontainer never need to be the same.

## 11. When the agent must stop and ask

- The agent encounters a SICP exercise that doesn't translate cleanly to
  Clojure and can't decide whether to keep, modify, or replace it.
- An exercise's natural solution would require a concept the student hasn't
  been introduced to yet (forward reference / scaffolding violation).
- A test the agent writes passes against the starter, meaning the test is
  trivially satisfiable. After one fix attempt fails, stop and ask.
- The agent's solution and the spec disagree about what an exercise should
  teach.
- An exercise as planned in `clojure/ASPECTS.md` turns out to be a bad fit
  (too thin, too overlapping, too forward-referencing). Do not ship a weak
  version. Stop and propose a replacement.
- Anything in this spec is ambiguous as applied to the current task.

Stopping and asking is preferred to silently making a judgment call.

## 12. What "done" means for an aspect

An aspect is done when:

- All 10 `PROBLEM.md` files exist and follow the format in section 4
  (including the "The interface" and "Run the tests" sections).
- All 10 `starter.clj` files exist and parse cleanly.
- All 10 `.solutions/solution.clj` files exist, are idiomatic, and pass
  their respective tests.
- All 10 `test.clj` files exist and (a) pass against the solution, (b) fail
  against the unmodified starter.
- The aspect `README.md` exists and follows the format in section 5
  (including the "When you're done" section).
- The aspect's exercises form a monotonically-decreasing scaffolding ladder
  per section 3 (T1 → T2 → T3, and no inversions).
- The agent has captured the test output (pass on solution, fail on starter)
  in their working notes for the human's spot check.
- The agent has run the self-review steps from PHASE-2 task 3.

The human then runs the **Per-aspect checklist** in `VERIFICATION_CHECKLIST.md`
before the aspect ships and the next one starts.
