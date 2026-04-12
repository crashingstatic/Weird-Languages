---
name: paradigm-course-spec
description: Use this skill whenever working on the Paradigms Course project — any task that involves creating, editing, or reviewing course content under languages/<lang>/, including exercises, aspect READMEs, syllabi, starter files, test files, solutions, or the language Dockerfile/devcontainer. ALSO use this skill any time the user mentions the "paradigms course", "paradigm course", "Clojure course", "exercise scaffolding", or asks to add/modify an exercise or aspect. This skill encodes the canonical directory layout, file naming, the T1/T2/T3 scaffolding tiers, and the .solutions visibility convention. Read it before touching any course file.
---

# Paradigm Course Spec

This skill encodes the structural conventions for the Paradigms Course
project. It does NOT encode the write→test→verify workflow (that's the
`exercise-author` skill) or Clojure-specific testing idioms (that's the
`clojure-testing` skill). Use all three together.

## Read the canonical spec first

The full spec lives at `.bootstrap/COURSE_AUTHORING_SPEC.md` in the project
repo. Read it before doing anything course-related. The summary below is a
quick reference, not a substitute.

```
view .bootstrap/COURSE_AUTHORING_SPEC.md
```

If `.bootstrap/COURSE_AUTHORING_SPEC.md` does not exist in the current repo,
stop and tell the user — the project hasn't been bootstrapped yet.

## Quick reference: directory layout

```
languages/<lang>/
├── README.md
├── SYLLABUS.md
├── deps.edn                            # or equivalent for non-Clojure langs
├── .devcontainer/
│   ├── Dockerfile
│   ├── devcontainer.json
│   └── smoke-test.sh
├── NN-aspect-slug/
│   ├── README.md
│   └── exercises/
│       ├── NN-exercise-slug/
│       │   ├── PROBLEM.md
│       │   ├── starter.<ext>
│       │   ├── test.<ext>
│       │   └── .solutions/
│       │       └── solution.<ext>
│       └── ... (10 exercises total)
└── capstone/
    ├── README.md
    ├── starter/
    ├── test/
    └── .solutions/
        └── reference/
```

Aspect and exercise directories use two-digit zero-padded prefixes
(`01-`, `02-`, ..., `10-`) so lexicographic sort matches numeric order.
The capstone is `capstone/` (no slug suffix) and is structurally different
from aspects — it is one project, not 10 exercises.

## Quick reference: scaffolding tiers

| Tier | Exercises | Help level |
|---|---|---|
| **T1** | 01–03 | Fill-in-the-blank with explanatory comments |
| **T2** | 04–07 | Signature + docstring given, body empty |
| **T3** | 08–10 | Behavior described in PROBLEM.md, student picks signatures |

Special cases:

- **Aspect 1 ramps gentler.** Exercise 01 of aspect 01 is the most heavily
  scaffolded thing in the entire course.
- **A new-syntax aspect (e.g., aspect 03 with `loop`/`recur`, aspect 06 with
  `atom`, aspect 07 with `defmacro`)** MAY use a T1-style fill-in-the-blank
  for exercise 01 even if the rest of the aspect starts at T2.
- **Aspect 08 is cumulative.** Each exercise extends the previous one's code.

If you ever feel the need to add MORE help to a later exercise than an
earlier one, the exercises are in the wrong order. Renumber them.

## Quick reference: exercise file conventions

### `PROBLEM.md` required sections (in order)
1. `# Exercise NN: <Title>`
2. `## What you're learning` (one paragraph)
3. `## The problem` (concrete I/O description)
4. `## The interface` (exact function names + signatures the test calls)
5. `## Constraints` (only if any exist)
6. `## Hints` (optional, fewer as exercises progress; usually absent in T3)
7. `## Run the tests` (the exact shell command)

### `starter.<ext>`
- Namespace/module declaration matches the exercise path.
- T1: fill-in-the-blank with `___` (three underscores) and adjacent comments.
- T2: signature + docstring, body is `(throw (ex-info "not implemented" {}))`.
- T3: namespace declaration plus a comment listing the public functions; the
  student writes the signatures.

### `test.<ext>`
- Tests behavior only. NEVER parses student source. NEVER asserts on the
  presence of specific function calls or syntax. NEVER `slurp`/`read-string`
  on the starter file.
- Imports the student module and calls its public API.
- Has reasonable timeouts so an infinite loop in student code doesn't hang.

### `.solutions/solution.<ext>`
- Idiomatic, complete reference implementation.
- Same namespace as the starter.
- Tests MUST pass against this file.
- Comments explain the **paradigm point**, not the syntax.

## Aspect README required sections

```markdown
# Aspect NN: <Title>

## What this teaches
## Why it matters
## SICP mapping
## Prerequisites
## Vocabulary introduced
## Exercises
1. **<title>** — <one-line summary>
...
10. **<title>** — <one-line summary>

## When you're done
## Where to go deeper
```

The brief in `.bootstrap/clojure/ASPECTS.md` is the agent's input. The
aspect README is the student's output. They are not the same document — the
agent rewrites the brief for the student, dropping agent-only context like
explicit T1/T2/T3 tier markers in exercise titles.

## .solutions/ visibility

The dot in `.solutions/` is a soft signal, not security. Solutions are
checked into git, not gitignored, not encrypted. Students can read them
whenever they want. The language-level README must mention this so students
aren't surprised.

`ls` doesn't show `.solutions/`. `ls -a`, `git status`, `git ls-files`,
`find .`, and language build tools all see them normally.

For the capstone, the reference lives at `capstone/.solutions/reference/`
and is a full mini-project (multiple files, its own `deps.edn`), not a
single solution file.

## Common mistakes to avoid

- **Forgetting the namespace prefix.** Clojure namespaces must match file
  paths. `01-functional-foundations/exercises/03-absolute-value/starter.clj`
  must declare
  `(ns clojure-course.functional-foundations.ex-03-absolute-value)`.
- **Putting hints in starter.clj instead of PROBLEM.md.** All prose lives in
  PROBLEM.md. starter.clj has only code, brief task comments, and blanks.
- **Test files that test the wrong thing.** A test that asserts
  `(re-find #"reduce" (slurp "starter.clj"))` is a structural test, not a
  behavioral test. Forbidden by spec.
- **Renumbering only some files when reordering.** If you rename `03-foo`
  to `04-foo`, also update its `PROBLEM.md` heading, its starter namespace,
  its test namespace, and any cross-references in the aspect README.
- **Capstone in the wrong place.** The capstone is `capstone/` at the
  language root, NOT `09-capstone/`. It is structurally different from
  aspects (single big project, not 10 small exercises).
- **Skipping "The interface" in PROBLEM.md.** Even T3 exercises need to tell
  the student the exact function names the test will call. Without this,
  the test won't find their code.
- **Skipping "When you're done" in the aspect README.** The verification
  checklist will catch this.

## When in doubt

Read `.bootstrap/COURSE_AUTHORING_SPEC.md` end to end. If still unclear,
stop and ask the human rather than guess. The spec explicitly lists
situations where stopping is the correct action (section 11).
