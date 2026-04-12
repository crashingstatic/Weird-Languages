# Bootstrap (one-time human setup)

Run these steps once on your host machine before kicking off any Claude Code
agents. Most of the project is built by the agents themselves; this file only
covers the steps that touch your devcontainer config and skill registry.

## 1. Create the project repo

```bash
mkdir paradigms-course
cd paradigms-course
git init
```

The agents will fill it out from this point. Commit early and often so you
can diff/revert agent work between phases.

## 2. Drop the bootstrap directory into the project as `.bootstrap/`

The agents read every file from `.bootstrap/` inside the project repo. So
just copy this entire bootstrap directory there:

```bash
cp -r /path/to/paradigms-course-bootstrap .bootstrap
git add .bootstrap
git commit -m "Bootstrap: spec, skills, aspect briefs, capstone spec, agent prompts"
```

After this, `paradigms-course/.bootstrap/` should contain `COURSE_AUTHORING_SPEC.md`,
`skills/`, `clojure/`, `agent-prompts/`, etc. — the full contents of the
bootstrap dir.

You do **not** need to install the custom skills yourself — Phase 1 of the
agent workflow will copy them into `~/.claude/skills/` automatically.

## 3. Clone reference repos for inspiration (optional but recommended)

These give the agents (and you) high-quality examples to read. They are
not skills you install — they sit on disk for reference.

```bash
mkdir -p ~/refs
git clone https://github.com/anthropics/skills.git ~/refs/anthropic-skills
git clone https://github.com/obra/superpowers.git  ~/refs/superpowers
```

`anthropic-skills` has high-quality SKILL.md examples (`docx/`, `pdf/`,
`skill-creator/`) that demonstrate the canonical format.

`superpowers` has battle-tested TDD and debugging skills. The TDD discipline
inspired our `exercise-author` skill's write→test→verify loop. The agents
may reference it in Phase 4 when looking at how an opinionated test-first
workflow is encoded.

## 4. Confirm your devcontainer will let the agent install Clojure tooling

Phase 1 will install `clojure`, `clj-kondo`, a JDK, and a few small utilities
into the agents' current environment (the host devcontainer they're running
in). If your devcontainer doesn't have `apt`/`brew`/`curl` available or
you've locked it down, fix that first or the agents will get stuck on Task 2
of Phase 1.

You said agents can fuck up the devcontainer — confirmed. No DinD needed.
Agents install directly. If they break cross-language compatibility later,
reset the devcontainer between v1 and v2.

## 5. Optional: prep a fresh git branch per phase

```bash
git checkout -b phase-1-scaffolding
```

Makes it easy to review the agent's diff after each phase and either merge
to `main` or throw it away and re-run cleanly.

## You're ready

Open Claude Code inside the `paradigms-course` repo. Open
`.bootstrap/agent-prompts/PHASE-1-scaffolding.md` and paste its contents into
a fresh Claude Code session.

Read the relevant section of `.bootstrap/VERIFICATION_CHECKLIST.md` so you
know what "done" looks like before you hit go.
