# Verification Checklist

This is **your** checklist, not the agent's. Run it after each aspect ships in Phase 2,
after the capstone in Phase 3, and after the student devcontainer in Phase 4. Do not
sign off on a phase or aspect that has any item unchecked.

The checklist is designed to catch the failure modes that agents most commonly produce:
trivial tests, broken starters, forward references, and inflated difficulty curves.

---

## Per-aspect checklist (Phase 2)

Run this after the agent reports an aspect complete. Estimated time: 15–30 minutes
per aspect.

### Structural

- [ ] Aspect directory exists at `languages/clojure/NN-<slug>/`
- [ ] `README.md` exists and follows the spec's required sections
- [ ] `exercises/` contains exactly 10 subdirectories named `01-` through `10-`
- [ ] Each exercise directory contains `PROBLEM.md`, `starter.clj`, `test.clj`, and
      `.solutions/solution.clj`
- [ ] No stray files (no `.bak`, `.tmp`, `*.orig`, leftover test runs, etc.)

### Per-exercise spot checks

Pick exercises **01, 05, and 10** for full review. Skim the others.

For each spot-checked exercise:

- [ ] PROBLEM.md has all required sections in the correct order
- [ ] PROBLEM.md "The interface" section names the exact functions the test calls
- [ ] PROBLEM.md "Run the tests" gives the exact shell command
- [ ] starter.clj compiles (`clojure -e "(load-file \"starter.clj\")"`)
- [ ] solution.clj compiles
- [ ] solution.clj is idiomatic — would I be embarrassed to show it to a Clojure
      programmer? If yes, push back.
- [ ] solution.clj has paradigm-point comments, not syntax explanations
- [ ] Run the test against the solution: `cp .solutions/solution.clj starter.clj && clojure -M:test`
      → must PASS
- [ ] Restore the starter, then run the test against the unmodified starter →
      must FAIL
- [ ] The failure message in the previous step is something a student could act on,
      not "java.lang.NullPointerException" with no context

### Difficulty curve

- [ ] Exercise 01 is a fill-in-the-blank with detailed hints
- [ ] Exercise 05 is a guided implementation (function signature + docstring + body
      to write)
- [ ] Exercise 10 is open (no hand-holding)
- [ ] Exercise N+1 is genuinely harder than exercise N — read the PROBLEM.md of
      exercises 04, 05, 06, 07 in sequence and confirm the curve doesn't flatten

### Forward references

- [ ] Read every solution.clj quickly. Note any Clojure feature or function you don't
      recognize.
- [ ] For each unfamiliar feature, check whether it was introduced in this aspect's
      vocabulary or in a prior aspect's. If not, it's a forward reference. Reject and
      have the agent fix it.

### Aspect README

- [ ] Accurately describes what the 10 exercises actually test (not the planning brief)
- [ ] States the prerequisite aspects correctly
- [ ] "When you're done" section reflects what a student can actually do after these
      10 exercises

### Final gate

- [ ] Run the agent's self-review summary tables (Tasks 4 from Phase 2 prompt). Both
      tables look right.

If all boxes are checked: ✅ Aspect ships. Move on to the next aspect.
If any box is unchecked: ❌ Send the specific items back to the agent. Do not ship.

---

## Capstone checklist (Phase 3)

Run after the agent completes Phase 3. Estimated time: 1–2 hours.

### Structural

- [ ] `languages/clojure/capstone/` exists with `starter/`, `test/`, and
      `.solutions/reference/`
- [ ] Both READMEs exist (capstone-level and reference-level)
- [ ] Test files are organized by milestone (`m1_*_test.clj` through `m8_*_test.clj`)

### Reference implementation review

- [ ] Reference compiles cleanly: `cd .solutions/reference && clojure -M:test`
- [ ] All 8 milestone test files pass against the reference
- [ ] Reference is broken into the milestone files described in the spec, not one
      mega-namespace
- [ ] Reference has paradigm-point comments showing which course aspects it draws on
- [ ] Reference is idiomatic — read `query.clj` (the most complex file) and confirm
      no clever tricks where boring code would work

### Starter review

- [ ] Starter compiles (project loads, even though tests fail)
- [ ] Starter has the same file structure as the reference
- [ ] Each unimplemented function throws or has an empty stub — no half-broken
      implementations
- [ ] Starter `deps.edn` is identical to reference `deps.edn`

### Gate verification

- [ ] Run all milestone tests against the reference → all PASS
- [ ] Run all milestone tests against the starter → all FAIL
- [ ] Specifically verify the milestone-7 (recursive rules) tests have a hard timeout
      and don't hang on a buggy implementation. Test by deliberately breaking the
      recursive rule code in the reference and running the test — it should fail
      cleanly within 10 seconds, not hang.

### Out-of-scope check

- [ ] Reference does NOT implement negation
- [ ] Reference does NOT implement aggregation
- [ ] Reference does NOT implement built-in predicates beyond equality
- [ ] If any of the above are present, reject and have the agent remove. The capstone
      is teaching, not engineering.

### READMEs

- [ ] Capstone README explains how to run a single milestone's tests
- [ ] Capstone README has a "where to go next" section
- [ ] Reference README walks through which course aspects each piece of the
      implementation exercises

If all boxed checked: ✅ Capstone ships.

---

## Student devcontainer checklist (Phase 4)

Run after the agent completes Phase 4. Estimated time: 30 minutes.

### Files

- [ ] `languages/clojure/.devcontainer/Dockerfile` exists
- [ ] `languages/clojure/.devcontainer/devcontainer.json` exists
- [ ] `languages/clojure/.devcontainer/smoke-test.sh` exists and is executable

### Dockerfile review

- [ ] Base image has a specific version tag (no `:latest`)
- [ ] Every install has a pinned version
- [ ] Container runs as a non-root user
- [ ] No Claude Code, no MCP, no agent tooling
- [ ] Reasonable size — under 1.5 GB ideally
- [ ] Comments explain non-obvious lines

### devcontainer.json review

- [ ] References the local Dockerfile
- [ ] Recommends Calva and clj-kondo extensions (only — no extension sprawl)
- [ ] `remoteUser` matches the Dockerfile's non-root user
- [ ] No firewall hooks, no init scripts beyond what students need

### Build verification (you do this on the host)

- [ ] `docker build` from `languages/clojure/.devcontainer/` succeeds
- [ ] Image size is reasonable (note actual size: __________)
- [ ] Run the smoke test inside the built container — it exits 0 and prints expected
      output
- [ ] Open the project in VS Code, click "Reopen in Container," confirm the IDE works
      and Calva connects to a REPL

### Walk-through test

- [ ] As a fake student, open exercise 01 of aspect 01 in the devcontainer, follow
      PROBLEM.md, fill in the blanks, run the test, confirm it passes
- [ ] Repeat for one exercise from aspect 04 (guided tier) and one from aspect 08
      (open tier) — verify the workflow is smooth, the test output is readable, and
      nothing requires arcane knowledge

If all boxes checked: ✅ Phase 4 ships. v1 is complete.

---

## What "ship" means

For Phase 2, "ship" means: commit the aspect's files to git with a descriptive commit
message ("Aspect 01 — Functional Foundations: 10 exercises authored and verified").

For Phase 3: same. Commit the capstone.

For Phase 4: same. Commit the devcontainer. Tag the repo `v1.0-clojure`.

After Phase 4 ships, v1 is complete. Take a break. When you're ready for v2 (Haskell),
come back and we'll plan the Haskell aspects from scratch — they will not look the
same as Clojure's.

---

## Common failure modes the checklist catches

Things I have seen agents do that this checklist exists to catch:

1. **Trivial tests** — `(is (some? (s/some-fn input)))` instead of asserting on the
   actual return value. Caught by: "test fails against starter."
2. **Tests that grep source** — `(is (re-find #"reduce" (slurp "starter.clj")))`.
   Caught by: file inspection of any test.clj. Forbidden by spec.
3. **Forward references** — using `core.async` channels in aspect 03. Caught by:
   reading every solution and noting unfamiliar features.
4. **Difficulty cliffs** — exercise 04 is wildly harder than 03 because the agent
   ran out of intermediate ideas. Caught by: reading 04, 05, 06, 07 in sequence.
5. **Difficulty inversions** — exercise 08 is easier than 06. Caught by: same.
6. **Solutions that hide the lesson** — clever one-liners in the early aspects.
   Caught by: idiomatic-vs-clever judgment call on each spot-checked solution.
7. **Half-broken starters** — starter that "almost works" so the test passes against
   it. Caught by: "test fails against starter" gate.
8. **Aspect README that says one thing while exercises do another** — agent wrote
   the README from the brief, not from the actual exercises. Caught by: reading the
   README *after* reviewing the exercises and asking "does this match?"
