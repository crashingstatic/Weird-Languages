# Capstone: Mini-Datalog Query Engine

A small Datalog query engine built milestone by milestone. By the end of this
project you'll be running queries like:

```clojure
(def facts
  [[:alice :parent :bob]
   [:bob   :parent :carol]
   [:carol :parent :dave]])

(def rules
  '[[(ancestor ?a ?d) [?a :parent ?d]]
    [(ancestor ?a ?d) [?a :parent ?x] (ancestor ?x ?d)]])

(query facts rules
       '{:find  [?a]
         :where [(ancestor ?a :dave)]})
;; => #{[:alice] [:bob] [:carol]}
```

…and you'll have written every line of the engine yourself.

## Why this capstone exists

You already built a metacircular evaluator in aspect 08. That's the canonical
SICP capstone, and it taught you how a language interprets *expressions*. The
mini-Datalog capstone is a different shape of language tool — it interprets
*relations*. It also pulls together every aspect of the course in a way the
evaluator didn't:

- **Aspect 02 (sequence library)** — query execution is a `keep`/`mapcat`
  pipeline over the fact stream.
- **Aspect 03 (recursion + recur)** — multi-clause joins are a fold; the
  rule engine is a fixed-point loop.
- **Aspect 04 (data and abstraction)** — facts and rules are *just data*.
  No objects, no wrappers. You design the shapes.
- **Aspect 05 (polymorphism)** — clauses come in two shapes (data patterns
  and rule invocations); dispatching on shape is a polymorphism decision.
- **Aspect 07 (macros)** — the surface syntax macros must stay thin.
  Real logic in a macro is a smell. You'll feel the temptation; resist it.

Datalog also has direct downstream value: it's the query language behind
Datomic, the foundation of CodeQL (a security-research staple), and a recurring
pattern in static analysis tooling.

## The 8 milestones

| # | File | What you build |
|---|------|----------------|
| M1 | [`facts.clj`](starter/src/datalog/facts.clj) | Fact storage on a Clojure set |
| M2 | [`unify.clj`](starter/src/datalog/unify.clj) | Pattern matching with logic variables |
| M3 | [`query.clj`](starter/src/datalog/query.clj) | Single-clause queries |
| M4 | [`query.clj`](starter/src/datalog/query.clj) | Multi-clause joins |
| M5 | [`query.clj`](starter/src/datalog/query.clj) | `:find` projection (set semantics) |
| M6 | [`rules.clj`](starter/src/datalog/rules.clj) | Non-recursive rules |
| M7 | [`rules.clj`](starter/src/datalog/rules.clj) | Recursive rules (fixed-point) |
| M8 | [`syntax.clj`](starter/src/datalog/syntax.clj) | `defrule` and `?-` macros |

Each milestone is described in the corresponding starter file's docstrings and
exercised by an independently-runnable test namespace.

## Working on the capstone

Edit your code under [`starter/src/datalog/`](starter/src/datalog/). All
commands below run from `starter/`:

```bash
cd starter
```

### Run all milestone tests

```bash
clojure -M:test
```

You'll see every test fail at first — that's expected. Each milestone you
finish unlocks more passing tests.

### Run one milestone's tests

Each milestone has its own test namespace. To run only milestone 3:

```bash
clojure -M:test -n datalog.m3-query-clause-test
```

The full list:

```
datalog.m1-facts-test
datalog.m2-unify-test
datalog.m3-query-clause-test
datalog.m4-query-where-test
datalog.m5-find-test
datalog.m6-rules-test
datalog.m7-recursive-rules-test
datalog.m8-syntax-test
```

This is how you'll spend most of your time: pick a milestone, run only its
test, iterate until it passes, move on.

### A note on milestone 7

The recursive-rules tests have a 10-second hard timeout per test. If your
implementation has no fixed-point detection it *will* infinite-loop, and the
test will catch that without hanging your terminal. The test will fail with
the result `:datalog.m7-recursive-rules-test/timeout` rather than spinning
forever. That's your signal to add termination logic.

## When you're stuck

The reference implementation lives at
[`.solutions/reference/`](.solutions/reference/). It's checked into git, not
encrypted, not secret. Look at it whenever you're stuck — the dot is a gentle
nudge, not a lock. Each file in the reference is annotated with which aspects
of the course it draws on; reading it is itself a worthwhile review.

## Where to go next

Once everything passes, consider extending the engine in any of these
directions. None of them are required, all of them are real:

- **Negation** (`not`) — surprisingly subtle; needs stratification to be
  sound.
- **Aggregation** (`count`, `min`, `sum`) — straightforward additions over the
  result-set step.
- **Built-in predicates** beyond equality (`<`, `>`, `string/starts-with?`) —
  another clause shape for `match-clause` to dispatch on.
- **Indexing** — replace the linear scan in `query-clause` with an index by
  attribute or entity. Measure the speedup on a few thousand facts.
- **Real Datomic** — try the same queries against
  [Datomic](https://www.datomic.com/) or [DataScript](https://github.com/tonsky/datascript).
  The query syntax is nearly identical; the engine underneath is what you
  just learned.
- **CodeQL** — a Datalog descendant used for security analysis at scale.
  Now that you know how the engine works, the documentation reads very
  differently.

## What's out of scope

Not in this capstone (and not because they're hard — because they're separate
lessons):

- Negation, aggregation, comparison built-ins.
- Indexing for performance (linear scan is fine; this is a learning tool).
- Stratified evaluation for negation.
- A persistent fact store. Facts live in memory.
- Concurrent queries.

These are all listed above as "where to go next" if you want them.
