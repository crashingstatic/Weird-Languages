# Paradigms Course

A hands-on programming course that teaches new programming paradigms through
SICP-inspired exercises. Each language module walks you through the paradigm
shift that language embodies, building from first principles to a capstone
project.

## Who this is for

You have an undergraduate CS degree (or equivalent) and roughly three years of
professional experience in C-family languages (C, C++, Java, Python, Go, Rust,
etc.). You know what closures, recursion, hash tables, and unit tests are. You
have used a debugger. You are not a beginner.

This course does not teach you to program. It teaches you to program
*differently* — to see problems through the lens of a paradigm your current
toolkit doesn't give you.

## Language modules

| Module | Paradigm focus | Status |
|--------|---------------|--------|
| [Clojure](languages/clojure/) | Functional programming, data-driven design, metaprogramming | In progress |
| Haskell | Type-driven design, purity, lazy evaluation | Planned |
| Others | TBD | TBD |

Each module is self-contained. You can do them in any order, though Clojure
is recommended first — its syntax is minimal, which keeps the focus on concepts
rather than grammar.

## How to navigate

```
languages/
└── clojure/
    ├── README.md           # module orientation
    ├── SYLLABUS.md         # ordered aspects with time estimates
    ├── deps.edn            # shared dependencies
    ├── 01-aspect-slug/     # one directory per aspect
    │   ├── README.md       # what this aspect teaches
    │   └── exercises/      # 10 exercises per aspect
    │       └── 01-slug/
    │           ├── PROBLEM.md
    │           ├── starter.clj
    │           ├── test.clj
    │           └── .solutions/
    │               └── solution.clj
    └── capstone/           # culminating project
```

Each language module follows this structure. Start with the module's `README.md`,
then work through the syllabus in order.

## Running exercises

Each module has its own toolchain. For Clojure:

```bash
cd languages/clojure
clojure -M:test    # run all tests
```

See the module README for details on running individual exercises.

## Solutions

Reference solutions live in `.solutions/` directories (dot-prefixed, so `ls`
won't show them by default). They are not hidden or encrypted — check them
whenever you're stuck. The dot prefix is a gentle nudge to try first, not a
security boundary.

## Contributing

This course is built with agent-assisted authoring. The canonical spec lives
in `.bootstrap/COURSE_AUTHORING_SPEC.md`. If you're contributing content,
read that file first.
