# Clojure Module

This module teaches functional programming, data-driven design, and
metaprogramming through Clojure — a modern Lisp on the JVM. The pedagogical
sequence is aligned with SICP (*Structure and Interpretation of Computer
Programs*), adapted for idiomatic Clojure rather than Scheme.

By the end of the module you will have built a working interpreter for a tiny
Lisp (aspect 08) and a mini-Datalog query engine (capstone). You will think
about programs as expressions that produce values rather than statements that
mutate state.

> **Beta testing this course?** Each aspect folder has a `SURVEY.md` —
> ~45 seconds, fill it in when you finish that aspect. A longer
> `COURSE-SURVEY.md` lives at the module root for after the capstone. All
> questions are optional. Save and send back to the course author.

## Prerequisites

- An undergraduate CS degree or equivalent systems knowledge.
- ~3 years of professional experience in C-family languages (C, C++, Java,
  Python, Go, Rust, etc.).
- Comfort with closures, recursion, hash tables, and unit tests.
- A working terminal and text editor.

You do **not** need prior Lisp or Clojure experience. That's what this module
teaches.

## Getting started

### Dev environment

The student devcontainer in `.devcontainer/` has everything pre-installed:
JDK 21, Clojure CLI 1.12.0.1530, clj-kondo (linter), and rlwrap (REPL
ergonomics).

**Prerequisites:**

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (or any
  Docker-compatible runtime)
- [VS Code](https://code.visualstudio.com/) with the
  [Dev Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
  extension installed

**Setup:**

1. Open the `languages/clojure/` folder in VS Code.
2. When prompted, click **"Reopen in Container"** — or open the Command
   Palette (`Ctrl+Shift+P` / `Cmd+Shift+P`) and run
   **Dev Containers: Reopen in Container**.
3. Wait for the container to build (first time takes a few minutes; subsequent
   opens are fast).
4. Open a terminal inside VS Code — you're ready to go.

The container includes the [Calva](https://calva.io/) and
[clj-kondo](https://github.com/clj-kondo/clj-kondo) VS Code extensions
pre-configured.

**Smoke test:** after the container is running, verify the toolchain:

```bash
bash .devcontainer/smoke-test.sh
```

**Troubleshooting:**

- **Container won't build?** Make sure Docker is running. Try
  **Dev Containers: Rebuild Container** from the Command Palette.
- **Tests fail with "command not found"?** You're probably running outside the
  container. Reopen in the devcontainer first.
- **First REPL start is slow?** The image pre-downloads Clojure core jars, but
  project dependencies are fetched on first use. This is normal.

### Running tests

From this directory (`languages/clojure/`):

```bash
# Run all tests
clojure -M:test

# Run a single exercise's test
clojure -M:test --focus 'clojure-course.functional-foundations.ex-01-hello-values-test'
```

Each exercise's `PROBLEM.md` includes the exact command to run its tests.

## Module structure

See [SYLLABUS.md](SYLLABUS.md) for the full ordered list with descriptions and
time estimates. The short version:

| # | Aspect | SICP alignment |
|---|--------|---------------|
| 01 | Functional Foundations | Ch. 1.1-1.2 |
| 02 | Higher-Order Functions and the Sequence Library | Ch. 1.3 |
| 03 | Recursion, Iteration, and `recur` | Ch. 1.2 (Clojure-specific) |
| 04 | Data and Abstraction | Ch. 2.1-2.2 |
| 05 | Polymorphism and Protocols | Ch. 2.4-2.5 |
| 06 | State, Identity, and Concurrency | Ch. 3.1-3.4 |
| 07 | Macros and Code-as-Data | Ch. 4 (framing) |
| 08 | Building a Language: A Tiny Evaluator | Ch. 4.1 |
| -- | **Capstone:** Mini-Datalog Query Engine | Original |

Each aspect has 10 exercises that progress from fill-in-the-blank (exercises
01-03) to guided implementation (04-07) to open-ended problems (08-10). The
scaffolding decreases as you go — by the end of each aspect, you're writing
Clojure from scratch.

## How exercises work

Everything you need for a single exercise lives in one folder:

```
01-functional-foundations/exercises/05-newton-sqrt/
├── PROBLEM.md      # what to build and why
├── starter.clj     # your working file — edit this
├── test.clj        # behavioral tests — read-only
└── .solutions/
    └── solution.clj    # reference implementation
```

`starter.clj` and `test.clj` are **symlinks** into the standard Clojure source
layout — the real files live at `src/clojure_course/<aspect>/ex_NN_<name>.clj`
and `test/clojure_course/<aspect>/ex_NN_<name>_test.clj`, because that's where
the Clojure CLI expects to find them on the classpath. Editing either the
symlink or the real file does the same thing; the symlinks are just so you
don't have to hunt across three directory trees for every exercise.

1. Read `PROBLEM.md`.
2. Edit `starter.clj` in the same folder.
3. Run the test command from `PROBLEM.md`.
4. Iterate until tests pass.

> If you cloned the repo on a Windows host without symlink support enabled in
> Git, `starter.clj` and `test.clj` may appear as small text files containing
> the target path instead of real symlinks. In that case, edit the real file
> under `src/clojure_course/...` directly, or enable Git symlinks:
> `git config --global core.symlinks true` and re-clone.

### About solutions

Reference solutions live in `.solutions/` (dot-prefixed, hidden from plain
`ls`). They are checked into git, not encrypted, not secret. Look at them
whenever you're stuck — the dot is a gentle nudge, not a lock.

## Where to go next

*After* completing the capstone (not before):

**Libraries and ecosystems:**
- **core.async** — CSP-style channels and go blocks for async programming
- **ClojureScript** — Clojure compiled to JavaScript for browser/Node work
- **Datomic** — an immutable database built on Datalog (your capstone is a toy
  version of its query engine)
- **Reagent / Re-frame** — ClojureScript React wrappers for frontend apps

**Books:**
- *Programming Clojure* (Miller, Halloway) — the standard reference
- *Clojure Applied* (Vandgrift, Miller) — practical patterns for real projects
- *The Joy of Clojure* (Fogus, Houser) — deeper into the philosophy

**Community:**
- [Clojurians Slack](https://clojurians.slack.com) — the main community hub
- [ClojureVerse](https://clojureverse.org) — forum for longer discussions
- [Clojure Style Guide](https://guide.clojure.style/) — the idiomaticity
  reference used throughout this course
