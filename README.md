The art of programming is not just about making things that work.
The art of programming is essentially how to _think_ about _thinking_.

You have probably specialized in only a certain family of languages your entire life.
Mostly likely those languages were C-based. When you moved from C to C++ or Java, you were taught to abstract objects:
"Don't think about the internals of _how_ something is done. Think only of the _inputs_ and _outputs_."
This object-oriented manner of thinking introduced you to a plethora of design patterns that further stretched your mind and how you approach problems.
The purpose of this course is to introduce you to different kinds of minds by re-wiring your brain's problem solving center.

Different paradigms force you to solve problems in different ways.
By the end of this course, you will have been introduced to seven **incredibly** different languages.
You will approach problems differently. You might even start reaching for some of these tools when approaching new problems.

# Paradigms Course

A hands-on course that teaches you new programming paradigms through
crash courses in some very weird languages.
You already know how to code — this course is about learning to think differently.
Each language module walks you through the paradigm shift that language embodies,
from first principles up to a capstone project.

## Get started (Clojure module) - vsCode

Each language module ships with its own **student devcontainer** — a Docker
image with the toolchain pre-installed, pinned to the versions the exercises
were tested against. You don't install a JDK, Clojure CLI, or linter on your
host machine; the container has them. This keeps the "environment was weird"
class of problem off the table.

Here's the full getting-started loop, using the Clojure module as the example.

### 1. Install the prerequisites (one time, on your host)

- [Docker Desktop](https://www.docker.com/products/docker-desktop/), running.
- [VS Code](https://code.visualstudio.com/).
- The [Dev Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
  extension for VS Code.

That's it on your host. Everything else lives inside the container.

### 2. Clone the repository

```bash
git clone <this-repo-url> paradigms
```

### 3. Open the **module folder** (not the repo root) in VS Code

```bash
code paradigms/languages/clojure
```

This matters: the student devcontainer lives at
[languages/clojure/.devcontainer/](languages/clojure/.devcontainer/), and Dev
Containers uses the `.devcontainer/` next to the folder you open. Opening the
repo root would pick up the authoring container instead — which is not what
you want as a student.

### 4. Reopen in the container

When VS Code notices the devcontainer config it will prompt you with
**"Reopen in Container"**. Click it. (If you miss the prompt, open the Command
Palette with `Ctrl+Shift+P` / `Cmd+Shift+P` and run
**Dev Containers: Reopen in Container**.)

First build takes a few minutes — it pulls a JDK 21 base image, installs the
Clojure CLI, clj-kondo, and pre-downloads Clojure core jars so your first REPL
isn't slow. Subsequent opens are fast.

The container also installs the [Calva](https://calva.io/) and clj-kondo
VS Code extensions automatically.

### 5. Run the smoke test

Open a terminal inside VS Code (it opens inside the container) and run:

```bash
bash .devcontainer/smoke-test.sh
```

This verifies the Clojure CLI version, clj-kondo version, a trivial
evaluation, and that exercise 01's test suite passes against its reference
solution. If every line says `PASS`, your environment is good. If anything
fails, fix it before moving on — the rest of the course assumes a working
toolchain.

### 6. Start the first exercise

```bash
# From /workspaces/clojure inside the container:
cat 01-functional-foundations/exercises/01-hello-values/PROBLEM.md
```

Edit `starter.clj` in that same folder — it's a symlink into
`src/clojure_course/functional_foundations/` where the Clojure CLI picks it
up on the classpath, but you don't have to navigate there yourself. Then run
the test command listed at the bottom of `PROBLEM.md`. Iterate until it
passes. Move to the next exercise. Repeat.

To run the whole module's test suite:

```bash
clojure -M:test
```

### If something goes wrong

- **Container won't build.** Make sure Docker Desktop is actually running.
  Try **Dev Containers: Rebuild Container** from the Command Palette.
- **`clojure: command not found` in the terminal.** You're in a host terminal,
  not the container's. Look at the bottom-left of VS Code — it should say
  "Dev Container: Paradigms Course — Clojure".
- **Smoke test fails on the exercise step.** That usually means the src tree
  is in a weird state. Run `git status` inside the container and reset any
  unexpected changes to the `src/` files the smoke test touches.
- **First REPL start is still slow.** Core jars are pre-downloaded but
  project-specific deps are fetched on first `clojure -M:test`. This is a
  one-time cost.


## Get started (Clojure module) - no vsCode

Each language module ships with its own **student container** — a Docker
image with the toolchain pre-installed, pinned to the versions the exercises
were tested against. You don't have to install any tools on your
host machine; the container has them.

Here's the full getting-started loop, using the Clojure module as the example.

### 1. Install the prerequisites (one time, on your host)

Linux:
```
sudo apt install docker.io
sudo usermod -aG docker $USER
```

Homebrew:
```
brew install --cask docker
```

That's it on your host. Everything else lives inside the container.

### 2. Clone the repository

```bash
git clone <this-repo-url> paradigms
```

### 3. Build the container for the language

```bash
cd paradigms/languages/clojure
docker build -f .devcontainer/Dockerfile -t weird-langs:clojure .
```

First build takes a few minutes — it pulls a JDK 21 base image, installs the
Clojure CLI, clj-kondo, and pre-downloads Clojure core jars so your first REPL
isn't slow. Once the image is built, deployment is much faster.

 > Versions are currently locked to
 > Java 21
 > Clojure 1.12.0.1530
 > clj-kondo 2024.11.14
 > Updating would require regression testing to ensure none of the exercises break.

### 4. Reopen in the container

```bash
docker run -ti --rm -v$(realpath .):/workspaces/clojure weird-langs:clojure bash
```

### 5. Run the smoke test

From the container's shell:

```bash
cd /workspaces/clojure
bash .devcontainer/smoke-test.sh
```

This verifies the Clojure CLI version, clj-kondo version, a trivial
evaluation, and that exercise 01's test suite passes against its reference
solution. Every line should say `PASS`. If anything fails, fix it before
moving on — the rest of the course assumes a working toolchain.

### 6. Start the first exercise

```bash
# From inside the container:
cd /workspaces/clojure
cat 01-functional-foundations/exercises/01-hello-values/PROBLEM.md
```

Edit `starter.clj` in that same folder — it's a symlink into
`src/clojure_course/functional_foundations/` where the Clojure CLI picks it
up on the classpath, but you don't have to navigate there yourself. Then run
the test command listed at the bottom of `PROBLEM.md`. Iterate until it
passes. Move to the next exercise. Repeat.

To run the whole module's test suite:

```bash
clojure -M:test
```

### If something goes wrong

- **`clojure: command not found` in the terminal.** You're in a host terminal,
  not the container's.
- **Smoke test fails on the exercise step.** That usually means the src tree
  is in a weird state. Run `git status` inside the container and reset any
  unexpected changes to the `src/` files the smoke test touches.
- **First REPL start is still slow.** Core jars are pre-downloaded but
  project-specific deps are fetched on first `clojure -M:test`. This is a
  one-time cost.

## Who this course is for

You have a CS degree (or equivalent) and roughly three years of professional
experience in a C-family language — C, C++, Java, Python, Go, Rust, or
similar. You're comfortable with closures, recursion, hash tables, and unit
tests. You've used a debugger. You are not a beginner.

This course does not teach you to program. It teaches you to program
*differently* — to see problems through the lens of a paradigm your current
toolkit doesn't give you.

## Language modules

| Module | Paradigm focus | Status |
|--------|---------------|--------|
| [Clojure](languages/clojure/) | Functional programming, data-driven design, metaprogramming | In progress |
| Haskell | Type-driven design, purity, lazy evaluation | Planned |
| Others | TBD | TBD |

Modules are self-contained and can be done in any order, but **Clojure first
is recommended** — its syntax is minimal, so the paradigm shift isn't hiding
behind grammar you're also learning.

When new modules land, each one ships its own student devcontainer following
the same pattern as Clojure: open the module folder in VS Code, reopen in
container, run the smoke test, go.

## How the repository is laid out

```
languages/
└── clojure/
    ├── .devcontainer/          # student devcontainer — open this folder in VS Code
    ├── README.md               # module orientation
    ├── SYLLABUS.md             # ordered aspects with time estimates
    ├── deps.edn                # shared dependencies
    ├── 01-functional-foundations/
    │   ├── README.md           # what this aspect teaches
    │   └── exercises/
    │       └── 01-hello-values/
    │           ├── PROBLEM.md
    │           ├── starter.clj  →  ../../../src/clojure_course/functional_foundations/ex_01_hello_values.clj
    │           ├── test.clj     →  ../../../test/clojure_course/functional_foundations/ex_01_hello_values_test.clj
    │           └── .solutions/
    │               └── solution.clj
    ├── src/clojure_course/...  # real working files live here (classpath)
    ├── test/clojure_course/... # real test files live here (classpath)
    └── capstone/               # culminating project
```

`starter.clj` and `test.clj` inside each exercise folder are symlinks to the
real files under `src/` and `test/` — that arrangement is what the Clojure CLI
expects for classpath discovery, and the symlinks are what students expect:
one folder per exercise, everything in it. Edit either side; they're the same
file.

Reference solutions live in `.solutions/` directories — dot-prefixed so a
plain `ls` doesn't spoil them, but they are not encrypted or hidden. Peek
whenever you're stuck. The dot is a nudge, not a lock.

## Where to go after the basics

Start with the module's [README](languages/clojure/README.md), then work
through [SYLLABUS.md](languages/clojure/SYLLABUS.md) in order. Each aspect
has 10 exercises that scaffold down from fill-in-the-blank to open-ended.

## Beta testing note

Each aspect folder in the Clojure module has a short `SURVEY.md` (~45
seconds). A longer `COURSE-SURVEY.md` lives at the module root for after the
capstone. If you're a beta tester, please fill them in and send back to the
course author — all questions are optional.

## Contributing

This course is authored with agent assistance. The canonical spec is in
[.bootstrap/COURSE_AUTHORING_SPEC.md](.bootstrap/COURSE_AUTHORING_SPEC.md).
Read that first if you're adding content.

This whole thing was vibe-coded with Claude Code. This was originally created
to teach me languages I do not speak. Continuing on, the rest of these languages
I don't speak either. Vibe-coding was the way to go for me, but if you are willing
to write exercises by hand, by all means please do.