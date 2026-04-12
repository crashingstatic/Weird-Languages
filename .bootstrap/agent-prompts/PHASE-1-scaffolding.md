# Phase 1 — Scaffolding

**Paste this into Claude Code from inside `~/projects/paradigms-course/`.**

---

You are bootstrapping the Paradigms Course project. This is Phase 1 of 4. Your job in
this phase is to create the project skeleton, install three custom skills, and produce
a working `languages/clojure/` directory tree ready for content.

## Read first (in this order)

1. `.bootstrap/COURSE_AUTHORING_SPEC.md` — the canonical project spec. Read it in full.
2. `.bootstrap/clojure/ASPECTS.md` — the 8 Clojure aspects. Skim, don't memorize. You'll
   re-read each aspect's brief in Phase 2 when you author it.
3. `.bootstrap/clojure/CAPSTONE.md` — the mini-Datalog capstone. Skim.
4. `.bootstrap/skills/paradigm-course-spec/SKILL.md`
5. `.bootstrap/skills/exercise-author/SKILL.md`
6. `.bootstrap/skills/clojure-testing/SKILL.md`

**Note on Aspect 8 (interpreter) cumulation:** The spec says each exercise extends the
previous one's code. This is a strong nice-to-have, not a hard requirement. If cumulation
makes an exercise confusing or weakens the teaching point, break the chain — instead,
sprinkle in reintroduction of concepts from earlier aspects to reinforce mastery. Teaching
the concept clearly always wins over maintaining the cumulative code chain.

After reading, summarize back to me in 5 bullets what the project is and what your
constraints are. I will correct you if anything is off before you proceed.

## Tasks

### Task 1 — Install the three custom skills

```bash
mkdir -p ~/.claude/skills
cp -r .bootstrap/skills/paradigm-course-spec ~/.claude/skills/
cp -r .bootstrap/skills/exercise-author ~/.claude/skills/
cp -r .bootstrap/skills/clojure-testing ~/.claude/skills/
ls ~/.claude/skills/
```

Verify all three are present. If Claude Code requires a restart to pick up new skills,
tell me — do not proceed silently.

### Task 2 — Install Clojure tooling in your devcontainer

Install:
- The Clojure CLI (`clojure` command, latest stable)
- `clj-kondo` (linter)
- `rlwrap` (for REPL ergonomics)
- A JDK if not already present (Temurin 21 LTS or similar)

Use the official installer from `https://download.clojure.org/install/linux-install.sh`
or your distribution's package manager. Verify with:

```bash
clojure --version
clj-kondo --version
java --version
```

If any install fails, stop and tell me. Do not proceed with broken tooling.

### Task 3 — Create the top-level project skeleton

Create:

```
README.md                       <- top-level course README (orientation only)
languages/
└── clojure/
    ├── README.md
    ├── SYLLABUS.md
    ├── deps.edn                <- shared deps for all Clojure exercises
    ├── 01-functional-foundations/
    │   ├── README.md           <- placeholder, populated in Phase 2
    │   └── exercises/          <- empty directory for now
    ├── 02-higher-order-and-sequences/
    │   ├── README.md
    │   └── exercises/
    ├── 03-recursion-and-recur/
    │   ├── README.md
    │   └── exercises/
    ├── 04-data-and-abstraction/
    │   ├── README.md
    │   └── exercises/
    ├── 05-polymorphism-and-protocols/
    │   ├── README.md
    │   └── exercises/
    ├── 06-state-identity-concurrency/
    │   ├── README.md
    │   └── exercises/
    ├── 07-macros-and-code-as-data/
    │   ├── README.md
    │   └── exercises/
    ├── 08-building-a-language/
    │   ├── README.md
    │   └── exercises/
    └── capstone/
        ├── README.md
        ├── starter/
        ├── test/
        └── .solutions/reference/
```

The `.devcontainer/` directory is **not** created in Phase 1. That's Phase 4.

### Task 4 — Write the top-level README.md

Orientation only. What the course is, who it's for (audience: undergrad CS + 3 yrs
C-family experience), the language list (Clojure now, Haskell next, others TBD), and
how to navigate. Keep it under 100 lines.

### Task 5 — Write `languages/clojure/README.md` and `SYLLABUS.md`

`README.md` — module-level orientation. Cover:
- What the Clojure module teaches and the SICP alignment
- Audience prerequisites (assume what the project spec assumes)
- How to use the student devcontainer (will be added in Phase 4 — add a placeholder
  pointing at `.devcontainer/`)
- How to run a single exercise's tests
- "Where to go next" — call out core.async, ClojureScript, Datomic, Reagent/Re-frame,
  and recommend *Programming Clojure* (Miller/Halloway) and *Clojure Applied*
  (Vandgrift/Miller) as follow-on books. Mention the "Where to go next" applies *after*
  the capstone, not during.

`SYLLABUS.md` — the ordered list of the 8 aspects with one-paragraph descriptions
(extract from `.bootstrap/clojure/ASPECTS.md`), per-aspect time estimates (rough — say
"~4–8 hours per aspect for an experienced engineer"), the dependency graph (which
aspects require which prior aspects), and the capstone at the end.

### Task 6 — Write `languages/clojure/deps.edn`

Module-shared `deps.edn` with a `:test` alias using cognitect-labs/test-runner. This
is the file every exercise's test invocation depends on. Pin `test-runner` to a recent
SHA. Verify it works by creating a throwaway `hello.clj` with one trivial `deftest`
and running `clojure -M:test` from the module root — it should find the test and pass.
Delete the throwaway when done.

### Task 7 — Write each per-aspect README.md placeholder

For each of the 8 aspect directories, create a stub `README.md` with just the title and
"This aspect is not yet authored. See `.bootstrap/clojure/ASPECTS.md` for the planned
content." Phase 2 will replace these with real READMEs.

### Task 8 — Stop and report

When all of the above is done:

1. Run `tree languages/clojure/ -L 3` (or `find languages/clojure -type d`) and show me
   the structure.
2. Show me the contents of `languages/clojure/SYLLABUS.md`.
3. Confirm `clojure -M:test` works from the module root.
4. Ask me to verify before proceeding to Phase 2.

## Rules

- Do not author any exercises in this phase. Phase 1 is structural only.
- Do not create the student devcontainer. That's Phase 4.
- Do not skip the verification step at the end. Stop and ask.
- If anything in `.bootstrap/COURSE_AUTHORING_SPEC.md` is unclear, ask before guessing.
- Tooling installs that fail are blocking. Tell me; do not proceed with workarounds.
