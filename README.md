# Paradigms Course Bootstrap (v1: Clojure)

This directory contains everything needed to author v1 of the Paradigms Course
using Claude Code agents. v1 is **Clojure**, structured as a SICP-aligned
undergraduate course with 8 aspects, 80 scaffolded exercises, and one capstone
(mini-Datalog).

## What's in here

```
paradigms-course-bootstrap/
├── README.md                           # this file
├── BOOTSTRAP.md                        # one-time human setup
├── COURSE_AUTHORING_SPEC.md            # canonical project contract — agents read this first
├── VERIFICATION_CHECKLIST.md           # human gate after each aspect/phase
├── skills/
│   ├── paradigm-course-spec/           # directory layout, file conventions, scaffolding tiers
│   ├── exercise-author/                # the write→test→verify loop
│   └── clojure-testing/                # Clojure-specific test idioms
├── clojure/
│   ├── ASPECTS.md                      # all 8 aspects with 10-exercise progressions
│   └── CAPSTONE.md                     # mini-Datalog spec (8 milestones)
└── agent-prompts/
    ├── PHASE-1-scaffolding.md          # paste once at start
    ├── PHASE-2-aspect-authoring.md     # paste 8 times (one per aspect)
    ├── PHASE-3-capstone.md             # paste once after aspects ship
    └── PHASE-4-student-devcontainer.md # paste last
```

## How to use this

1. Read `BOOTSTRAP.md`. Run the one-time human setup steps.
2. Open Claude Code in the project repo. Paste `agent-prompts/PHASE-1-scaffolding.md`
   into a fresh session. Watch it work. Run the **Per-aspect Phase 1 section**
   of `VERIFICATION_CHECKLIST.md` (the structural bits) before moving on.
3. For each of the 8 Clojure aspects, paste `agent-prompts/PHASE-2-aspect-authoring.md`
   into a **fresh session**, substituting the aspect number where indicated.
   Run the **Per-aspect checklist** in `VERIFICATION_CHECKLIST.md` after each
   one. Do not start aspect N+1 until aspect N has shipped.
4. After all 8 aspects have shipped, paste `agent-prompts/PHASE-3-capstone.md`.
   Run the **Capstone checklist**.
5. Finally, paste `agent-prompts/PHASE-4-student-devcontainer.md`. Run the
   **Student devcontainer checklist**. After it ships, tag the repo
   `v1.0-clojure`.

## Design constraints these files encode

- **Audience:** undergrad CS + ~3 years C-family experience. No babying.
- **SICP alignment:** the 8 Clojure aspects track SICP chapters where the
  mapping is natural. Aspect 8 is a metacircular evaluator. The capstone is
  not an evaluator (different shape).
- **Scaffolding tiers:** every aspect has 10 exercises split into three
  tiers — **T1** (01–03, fill-in-the-blank), **T2** (04–07, guided), **T3**
  (08–10, open). Help decreases monotonically.
- **Tests verify behavior, not source.** Test files import the student's
  module and assert on results. Tests that grep source are forbidden.
- **Solutions visible** in `.solutions/` subdirectories — checked in, not
  gitignored, not encrypted. Soft signal to students that they exist.
- **Per-language student devcontainer** is a deliverable artifact built and
  smoke-tested in Phase 4. Agents do **not** author inside it. Agents install
  Clojure tooling directly in their own environment during Phases 1–3.

## After v1: Haskell is v2

When you're done with v1, come back and we'll plan Haskell from scratch.
Aspects and capstone for Haskell will not be a copy-paste of Clojure — the
languages teach different things and the course should reflect that.
