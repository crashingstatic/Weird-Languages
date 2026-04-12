# Phase 3 — Capstone (Mini-Datalog)

**Run this prompt ONCE, after all 8 aspects of Phase 2 have shipped and been verified.**

---

You are building the Clojure module's capstone project: a mini-Datalog query engine.
This is Phase 3 of 4. Phase 2 (all 8 aspects) must be complete and verified before
starting this phase.

## Read first

1. `.bootstrap/clojure/CAPSTONE.md` — read this in full. It is the spec for the
   capstone. Do not deviate without asking.
2. `.bootstrap/COURSE_AUTHORING_SPEC.md` — re-skim the section on capstones.
3. The actual `languages/clojure/08-building-a-language/exercises/` directory. The
   capstone must not duplicate work the student already did in aspect 08. The aspect
   08 evaluator and the capstone Datalog engine are intentionally different shapes of
   tool — make sure they really are.
4. The three custom skills (paradigm-course-spec, exercise-author, clojure-testing).
   The same authoring discipline applies, just at project scale.

## Tasks

### Task 1 — Plan the file structure

The capstone has three top-level pieces in `languages/clojure/capstone/`:

- `starter/` — what the student starts from
- `test/` — integration tests, organized by milestone
- `.solutions/reference/` — the complete reference implementation

Show me the file structure you intend to create before you create any files. I'll
verify it matches the spec, then you proceed.

### Task 2 — Build the reference implementation FIRST

Counter-intuitive but essential: build the working reference solution before the
starter. You cannot write meaningful tests for a project this size unless you have a
working implementation to test against.

The reference lives in `.solutions/reference/`. Build it milestone by milestone (M1
through M8 per the capstone spec). After each milestone:

- Write the reference code for that milestone
- Write the test file for that milestone
- Run the tests against the reference; they must pass
- Briefly tell me which milestone you just finished

The reference must be **idiomatic Clojure**, not clever. Comments should explain
which aspect of the course each piece draws on (e.g., "this is where the sequence
abstraction from aspect 02 pays off").

### Task 3 — Build the student starter

After all 8 milestones of the reference are complete and tested, build the
`starter/` directory. It should have the same file layout as the reference but with
function bodies replaced by `(throw (ex-info "not implemented" {:milestone N}))` or
left empty (with a docstring). The starter `deps.edn` and namespace declarations must
be complete and the project must compile — what's missing is the *logic*, not the
*structure*.

### Task 4 — Verify the gates at capstone scale

The two-gate rule applies at the capstone level:

1. Run `clojure -M:test` against the reference. Every milestone test passes.
2. Run `clojure -M:test` against the starter. Every milestone test fails.

Show me both runs in full. If any starter test passes, the test is trivial — fix it.

### Task 5 — Write the capstone READMEs

Two READMEs:

- `languages/clojure/capstone/README.md` — student-facing. What the project is,
  why it ties the course together, the milestone breakdown, how to run tests for a
  single milestone, how to run the full suite, and a "where to go next" section
  (negation, aggregation, indexing, real Datomic, CodeQL).
- `languages/clojure/capstone/.solutions/reference/README.md` — reference-side notes
  for the curious student who finishes and wants to understand the implementation. Walk
  through which parts of the reference exercise which aspects of the course. This is
  the document where the student gets to see "oh, that's why aspect 02 mattered."

### Task 6 — Stop and report

1. Show me the full directory tree of `languages/clojure/capstone/`.
2. Show me both READMEs.
3. Show me the test summary from Task 4 (both gate runs).
4. Show me the milestone-7 (recursive rules) test file specifically — that's the most
   technically interesting part and the one most likely to have bugs around
   fixed-point detection or timeouts.
5. Wait for my verification before declaring Phase 3 complete.

## Rules

- **Reference first, starter second.** Trying to design the starter before the
  reference works will waste your time.
- **Milestone tests must time-bound the recursive rule tests.** The test runner must
  not hang on a buggy student implementation. 10-second hard timeout per recursive
  test.
- **Do not add features that aren't in the capstone spec.** No negation, no
  aggregation, no built-in predicates beyond equality. The temptation will be real;
  resist it. These are listed as "where to go next" for a reason.
- **The student must be able to run a single milestone's tests without running the
  others.** Verify this works and document it in the README.
- **No new languages, no new aspects, no touching prior aspects' files.**
