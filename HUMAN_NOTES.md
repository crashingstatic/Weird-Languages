This file is intended for human use only. CLAUDE IGNORE THIS FILE. DO NOT CONTINUE READING THIS FILE.

## Scope reality check

7 languages × ~5 aspects × 10 exercises ≈ **350 exercises**, each with starter + test + solution + problem statement, plus per-language Dockerfiles, READMEs, and syllabi. That's a real undergraduate course's worth of material. It's doable with agents, but worth knowing up front: this is weeks of agent work and review, not an afternoon. I'd suggest building **one language end-to-end first as a reference implementation** (I'd pick Racket — mature tooling, easy to test, familiar territory once you get past parens), then parallelizing the rest using it as a template.

## Proposed directory structure

```
paradigms-course/
├── README.md                    # course overview, learning path, prereqs
├── SYLLABUS.md                  # suggested order, time estimates
├── docker-compose.yml           # optional: bring up any lang's env
├── shared/
│   ├── exercise-template/       # canonical layout to copy
│   └── grading/                 # cross-lang test runner conventions
└── languages/
    ├── racket/
    │   ├── README.md            # how to use the docker env, run tests
    │   ├── Dockerfile
    │   ├── SYLLABUS.md          # aspects in order, what each teaches
    │   ├── 01-functional-basics/
    │   │   ├── README.md        # what this aspect teaches, why it matters
    │   │   └── exercises/
    │   │       ├── 01-fill-in-map/
    │   │       │   ├── PROBLEM.md
    │   │       │   ├── starter.rkt
    │   │       │   ├── test.rkt
    │   │       │   └── .solution/solution.rkt
    │   │       ├── 02-.../
    │   │       └── 10-.../
    │   ├── 02-higher-order/
    │   ├── 03-macros/
    │   ├── 04-continuations/
    │   └── 05-language-oriented/
    ├── haskell/
    ├── bqn/
    ├── prolog/
    ├── forth/
    ├── erlang/
    └── go/
```

Solutions live in a `.solution/` subdir so they're easy to gitignore for students but available for the agent to verify tests actually pass on a correct implementation.

## Proposed aspects per language

These are picked so each language teaches what it's _uniquely_ good at, with minimal overlap across languages.

**Racket** — 1) Functional basics & recursion, 2) Higher-order functions, 3) Macros (`syntax-rules` → `syntax-parse`), 4) Continuations (`call/cc`), 5) Language-oriented programming (build a small DSL)

**Haskell** — 1) Types & pure functions, 2) ADTs & pattern matching, 3) Typeclasses, 4) Laziness & infinite structures, 5) Functor/Applicative/Monad, 6) Parser combinators (capstone)

**BQN** — 1) Array basics & primitives, 2) Tacit programming & trains, 3) Reductions, scans, each, 4) Multi-dimensional rank operations, 5) Under (`⌾`) and structural transforms

**Prolog** — 1) Facts, rules, queries, 2) Unification & recursion on lists, 3) Backtracking & cut, 4) DCGs for parsing, 5) CLP(FD) for constraint solving

**Forth** — 1) Stack manipulation, 2) Defining words, 3) Control flow, 4) Immediate words & compile-time, 5) Building your own control structures (the brain-melter)

**Erlang** — 1) Sequential Erlang & pattern matching, 2) Processes & message passing, 3) OTP `gen_server`, 4) Supervision trees & "let it crash", 5) Distribution across nodes

**Go** — 1) Syntax & interfaces, 2) Goroutines & channels, 3) `select` & timeouts, 4) Pipelines & fan-in/fan-out, 5) `context` & cancellation patterns

That's 5 aspects × 7 langs × 10 exercises = **350 exercises**. If you want a smaller v1, drop to 3 aspects per language (210 exercises) and add the rest later.

## Tooling per language (Docker contents)

| Lang    | Compiler/Runtime   | Test framework                    | Linter               | Debugger               |
| ------- | ------------------ | --------------------------------- | -------------------- | ---------------------- |
| Racket  | Racket CS          | `rackunit`                        | `raco review`        | `racket -l errortrace` |
| Haskell | GHC + Cabal        | `hspec` or `tasty`                | `hlint`              | `ghci`                 |
| BQN     | CBQN               | custom assert harness             | —                    | CBQN REPL              |
| Prolog  | SWI-Prolog         | `plunit`                          | —                    | `trace/0`              |
| Forth   | Gforth             | custom `T{ ... -> ... }T` harness | —                    | `see`, `dbg`           |
| Erlang  | OTP (latest LTS)   | `eunit` + `common_test`           | `dialyzer` + `elvis` | `dbg`                  |
| Go      | Go (latest stable) | `go test`                         | `golangci-lint`      | `delve`                |

Each Dockerfile pins versions, installs the toolchain, sets `WORKDIR /exercises`, and provides a `make test` or `./run-tests.sh` entry point that takes an exercise path. The README documents: building the image, running a single exercise's tests, running an aspect's full suite, and dropping into a REPL.

## Test design constraint (important)

Your "test behavior, not source" rule is the right call but has language-specific implications:

- For most languages, tests can `import`/`require` the student's module and call functions. Easy.
- For **Forth**, tests load the student's `.fs` file and check stack effects with the standard `T{ … -> … }T` harness.
- For **BQN**, tests `•Import` the student file and assert on results.
- For early **fill-in-the-blank** exercises, the test still runs the completed file — the "fill-in" part is just that the starter has `???` placeholders with comments. The test doesn't care _how_ it got filled in.
- Capstone exercises (e.g., "build a parser combinator library") need integration tests that exercise the API the student was told to implement. The PROBLEM.md must specify the exact public interface so tests are stable.

## Agent strategy in Claude Code

I'd structure the agent work in three phases:

**Phase 1 — Scaffolding (1 agent run):** Create the top-level structure, shared templates, the exercise template, and a written "Course Authoring Spec" doc that all subsequent agents read first. This is the contract.

**Phase 2 — Reference language (1 agent, sequential):** Build Racket end-to-end. Aspect by aspect, exercise by exercise. After each exercise: write PROBLEM.md → write starter → write solution → write test → run test against solution → confirm pass → run test against starter → confirm fail. This catches the most common agent failure mode (tests that pass trivially or don't actually test anything). Review the output yourself before moving on.

**Phase 3 — Parallel buildout (6 agents, one per remaining language):** Each agent gets the Course Authoring Spec + the Racket reference + that language's aspect list. They follow the same write→test→verify loop.

Critical: every agent must be required to **actually run the tests inside the language's Docker container** before declaring an exercise done. Without this, you'll get exercises that look right but don't compile. The authoring spec should make this a hard requirement.

## Skills to use

Public skills already available that will help:

- **`skill-creator`** (`/mnt/skills/examples/skill-creator/`) — use this to author the custom skills below.
- **`docx`** / **`pdf`** — only if you eventually want printable handouts; skip for v1.

Custom skills you should create for this project (the agent guide will tell Claude Code to build them first):

1. **`exercise-author`** — Encodes the PROBLEM.md format, scaffolding rules, fill-in-the-blank conventions, and the write→test→verify loop. This is the most important one.
2. **`paradigm-course-spec`** — Holds the canonical directory structure, naming conventions, and per-language aspect lists. Effectively the project's style guide as a skill.
3. **Per-language reference skills** (optional but valuable) — e.g., `racket-testing`, `haskell-testing`, `forth-testing`. Each one documents the testing idioms, common pitfalls, and how to verify a test actually exercises behavior in that language. These pay for themselves quickly once you're authoring exercises in bulk.

There aren't existing community Anthropic Skills for these specific languages that I know of — if you find any on GitHub, they'd slot in here, but I'd plan on building your own.

# User Changes to Above

Went with Clojure instead of Racket.
Languages for releases shall be:

1. Clojure (mirror SICP)
2. Haskell
3. Prolog

---

4. BQN
5. Forth
6. Erlang
7. Golang

Assume the following background of audience: undergrad degree in CS + 3 years professional work as SW Eng.

Since you asked for suggestions: after Clojure → Haskell, I'd put Prolog as v3. By then you'll have two flavors of declarative-functional under your belt, and Prolog becomes the "and now for something completely different" jump — relational/logic programming with backtracking and unification. It also pairs unusually well with Haskell as a contrast: both are declarative, but Haskell declares what values are while Prolog declares what relations hold. The contrast crystallizes both languages.
After that, the order matters less. BQN and Forth are roughly equivalent in paradigm-shift value but very different flavors (array-rank thinking vs. stack-concatenative). Erlang is the natural cap because by then you'll have seen enough paradigms that the actor model lands as "oh, that's what they were trying to solve." Go is the lightweight palate-cleanser to slot in wherever you want a break — it's the smallest jump on your list.
So a defensible full sequence: Clojure → Haskell → Prolog → BQN → Forth → Erlang → Go

## Clojure aspects (8 total, SICP-aligned where the mapping is natural):

1. Functional foundations — values, immutability, def/let, recursion, basic data literals. (SICP Ch. 1.1–1.2)
2. Higher-order functions & sequence abstraction — map/filter/reduce, comp, partial, transducers intro. (SICP Ch. 1.3 + Clojure's seq library)
3. Recursion, iteration, and loop/recur — proper tail calls, accumulator patterns, tree recursion, the JVM stack reality. (SICP Ch. 1.2 with Clojure's twist)
4. Data and abstraction — maps, vectors, sets, records, destructuring, update/assoc-in, "data > functions > macros." (SICP Ch. 2.1–2.2)
5. Polymorphism & protocols — multimethods, protocols, when to use which, contrast with Java interfaces. (SICP Ch. 2.4–2.5 generic operations)
6. State, identity, and concurrency — atoms, refs, agents, the value/identity/state distinction, STM. (SICP Ch. 3.1–3.4, plus Clojure's contribution to the discussion)
7. Macros and code-as-data — quote/unquote, defmacro, syntax-quote, when not to write a macro, syntactic abstraction. (SICP Ch. 4 metalinguistic abstraction)
8. Building a language: a small interpreter — capstone. Implement a tiny Scheme-like evaluator in Clojure. (SICP Ch. 4.1, the metacircular evaluator)

Strong candidates for a capstone given a SICP-aligned Clojure course: a constraint propagator (SICP 3.3.5), a digital circuit simulator (SICP 3.3.4), a small query language / mini-Datalog (SICP 4.4), or a register machine simulator (SICP 5.2).
