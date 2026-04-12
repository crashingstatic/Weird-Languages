# Phase 4 — Student Devcontainer and Dockerfile

**Run this prompt ONCE, after Phases 1–3 have shipped and been verified.**

---

You are building the per-language student devcontainer for Clojure. This is Phase 4 of
4 — the final phase for v1. The student devcontainer is what students will actually use
when working through the course; it is **not** the same container the agents use.

## Read first

1. `.bootstrap/COURSE_AUTHORING_SPEC.md` — re-skim the section on the directory layout
   and the `.devcontainer/` placement.
2. The reference Anthropic devcontainer at `~/refs/anthropic-skills/` (if cloned), or
   browse the official one at the `anthropics/claude-code` repo on GitHub. Use it as a
   structural template — but strip out everything Claude Code specific. Students are
   not running Claude Code in this container.
3. The current state of `languages/clojure/` so you know what tooling the exercises and
   capstone actually need.

## Constraints

- **Single Dockerfile, single devcontainer.json.** No docker-compose, no DinD, no
  multi-stage orchestration.
- **The container is for students writing/running/testing/linting Clojure exercises.**
  That's it. No Claude Code, no agent tooling, no MCP servers.
- **Pin versions.** Every install — JDK, Clojure CLI, clj-kondo, anything else — has a
  specific version. No "latest." Students who pick this up in a year should get the
  same environment we tested with.
- **Reasonable image size.** No installing every editor under the sun. VS Code's Dev
  Containers extension brings the IDE; you bring the toolchain.
- **Non-root user.** Per the standard devcontainer pattern. The user should be able to
  install additional tools without sudo, but the base image runs as a non-root user.

## Tasks

### Task 1 — Choose the base image and pin versions

Decide on:
- Base image (recommend `eclipse-temurin:21-jdk-jammy` or similar — JDK 21 LTS on
  Ubuntu Jammy)
- Clojure CLI version (latest stable as of build time; pin the exact version string)
- clj-kondo version (latest stable; pin)
- Any other tools (rlwrap for REPL ergonomics, git, curl, less)

Tell me what you chose and why. I'll approve before you write the Dockerfile.

### Task 2 — Write the Dockerfile

`languages/clojure/.devcontainer/Dockerfile`. Structure:

1. Base image with version tag
2. System packages (apt-get install of git, curl, rlwrap, etc.)
3. Clojure CLI install from the official installer at the pinned version
4. clj-kondo install at the pinned version
5. Create non-root user
6. Set workdir
7. Switch to non-root user
8. Default `CMD` that drops into bash

Comment every non-obvious line. Students will read this file.

### Task 3 — Write the devcontainer.json

`languages/clojure/.devcontainer/devcontainer.json`. Should:

- Reference the local Dockerfile
- Set the workspace folder to `/workspaces/clojure`
- Mount the language module (not the whole course repo) into the container
- Recommend the Calva VS Code extension (`betterthantomorrow.calva`) and clj-kondo
  extension via `customizations.vscode.extensions`
- Set `remoteUser` to the non-root user from the Dockerfile
- No Claude Code feature, no MCP, no firewall hooks

### Task 4 — Verify the container builds

Build it from inside your authoring environment:

```bash
cd languages/clojure/.devcontainer
docker build -t paradigms-clojure-student .
```

If your authoring environment doesn't have Docker (it probably doesn't, since you're
inside a devcontainer), tell me. I will build it on the host and report back. Do not
fake the verification.

### Task 5 — Write the smoke test

Create a tiny `languages/clojure/.devcontainer/smoke-test.sh` that, when run from
inside the built container, verifies:

- `clojure --version` returns the expected pinned version
- `clj-kondo --version` returns the expected pinned version
- A trivial Clojure expression evaluates: `clojure -e '(+ 1 2)'` returns `3`
- `cd ../01-functional-foundations/exercises/01-hello-values && clojure -M:test` runs
  the first exercise's test and exits 0 (against the solution)

The smoke test should exit 0 on success and non-zero on any failure, with clear
output.

### Task 6 — Update the language README

Update `languages/clojure/README.md` to replace the Phase 1 placeholder for
"how to use the student devcontainer" with the real instructions:

- Prerequisites (Docker Desktop or equivalent, VS Code with Dev Containers extension)
- "Open this folder in VS Code and click 'Reopen in Container'"
- How to run a single exercise's tests once inside the container
- Where the smoke test lives and how to run it
- What to do if the build fails (rebuild, check Docker is running, etc.)

### Task 7 — Stop and report

1. Show me the Dockerfile.
2. Show me the devcontainer.json.
3. Show me the smoke-test.sh.
4. Show me the updated section of `languages/clojure/README.md`.
5. Tell me whether you were able to build the container yourself or whether I need to
   build it. If I need to build it, give me the exact commands.
6. Wait for my verification.

## Rules

- **No DinD, no docker-compose, no orchestration.** One Dockerfile, one
  devcontainer.json per language. That's it.
- **Pin everything.** "Latest" is forbidden in this Dockerfile.
- **No Claude Code in the student container.** Students are learning Clojure, not
  agentic coding.
- **No fake verification.** If you can't build the container, say so. I'd rather build
  it myself than discover a broken Dockerfile after the fact.
- **The smoke test is required.** It is the only thing that catches "Dockerfile builds
  but the toolchain is misconfigured."
