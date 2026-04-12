# Phase 2 — Aspect Authoring

**Run this prompt ONCE per aspect.** You'll run it 8 times total (aspects 01–08), one
session per aspect. Do not try to author multiple aspects in one session — context bleed
causes inconsistency.

**Before each run:** fill in the `<<ASPECT_NUMBER>>` placeholder below with the two-digit
aspect number (e.g., `01`). Do not author aspect N until aspect N-1 has passed the
verification checklist and you have confirmed it.

---

You are authoring aspect `<<ASPECT_NUMBER>>` of the Clojure module of the Paradigms
Course. This is one of 8 aspects. Your job is to produce the per-aspect README and all
10 exercises in this aspect, fully verified.

## Read first (in this order)

1. `.bootstrap/COURSE_AUTHORING_SPEC.md` — re-skim the file contracts and the
   scaffolding tier rule.
2. `.bootstrap/clojure/ASPECTS.md` — find the section for aspect `<<ASPECT_NUMBER>>`
   and read it in full. Read the **previous** aspect's section too — you need to know
   what vocabulary the student already has.
3. The actual contents of `languages/clojure/<NN>-<previous-aspect-slug>/exercises/` for
   every prior aspect, if any. This is the source of truth for what the student knows.
   Do not reference the planning file alone.
4. The three custom skills: `paradigm-course-spec`, `exercise-author`, `clojure-testing`.
   They should auto-load based on the file types you'll be touching, but if they don't,
   read them explicitly from `~/.claude/skills/`.

## Tasks

### Task 1 — Write the aspect README

`languages/clojure/<<ASPECT_NUMBER>>-<slug>/README.md` per the spec's "Per-aspect
README" section. Use the brief in `.bootstrap/clojure/ASPECTS.md` as your source. Do
not just copy it verbatim — rewrite for the student audience. The brief is for the
agent (you); the README is for the student.

### Task 2 — Author all 10 exercises in order

For each exercise 01 through 10:

1. Re-read the corresponding entry in `.bootstrap/clojure/ASPECTS.md`. It tells you the
   tier, the slug, and what concept the exercise targets.
2. Run the **`exercise-author` workflow** in full. Do not skip steps. The two gates
   (test passes against solution, test fails against starter) are mandatory.
3. After each exercise is complete and gated, briefly tell me which exercise you just
   finished and what the test output looked like. One line is fine. This is so I can
   tell if you're stuck.

The exercise directory layout per the spec:

```
languages/clojure/<<ASPECT_NUMBER>>-<slug>/exercises/<NN>-<exercise-slug>/
├── PROBLEM.md
├── starter.clj
├── test.clj
└── .solutions/
    └── solution.clj
```

### Task 3 — Self-review

After all 10 exercises are done, run this self-review and produce a short report:

1. Did exercise N+1 actually feel harder/more open than exercise N? List any cases
   where the difficulty curve flattens or inverts.
2. Are there any forward references — exercises that use a Clojure feature not yet
   introduced in this or a prior aspect? List them.
3. Are there any duplicate or near-duplicate exercises within this aspect? List them.
4. Does the aspect README accurately describe what the 10 exercises actually test?
5. For tier-3 exercises (08–10), is PROBLEM.md still understandable without hints?

If any of the answers reveal a problem, **fix it**, then re-run the affected exercise's
verification gates. Do not paper over issues.

### Task 4 — Run all aspect tests in sequence

Run every exercise's test against its own solution, in order, and produce a summary:

```
01-<slug>: PASS
02-<slug>: PASS
...
10-<slug>: PASS
```

Then run every exercise's test against its own starter and produce the same summary —
every result should be FAIL. If any exercise's starter test passes, that exercise has
a trivial test. Fix it.

### Task 5 — Stop and report for verification

When complete:

1. Show me the directory tree of the aspect.
2. Show me the aspect README.
3. Show me the PROBLEM.md and solution.clj of exercise 01 and exercise 10 (the easiest
   and the hardest), so I can spot-check the difficulty curve.
4. Show me the two summary tables from Task 4.
5. Tell me which Clojure features you used in this aspect that were not introduced in
   prior aspects (excluding the features this aspect itself introduces).
6. Wait for my verification before starting the next aspect.

## Rules

- **Author exercises in order.** Do not jump around. Each exercise builds on the prior.
- **The two verification gates are non-negotiable.** Skipping them is the single
  fastest way to break this project.
- **Stuck on an exercise?** If an exercise as planned in `ASPECTS.md` turns out to be a
  bad fit (too thin, too overlapping with another, too forward-referencing), do NOT
  ship a weak version. Instead, stop and tell me what's wrong and propose either a
  replacement or a re-scoping. I will decide.
- **Don't invent new conventions.** If the spec is silent on something, ask. If the
  spec contradicts your instinct, the spec wins.
- **Idiomatic, not clever.** Solutions are teaching material. Boring is correct.
- **Comment the paradigm point in solutions, not the syntax.** Per the spec.
- **Do not modify any aspect's content other than aspect `<<ASPECT_NUMBER>>`.** If you
  notice a bug in a prior aspect, tell me; do not silently fix it in this session.
- **Do not touch the capstone.** That's Phase 3.
- **Do not touch the student devcontainer.** That's Phase 4.
