# Clojure Capstone — Mini-Datalog

A small Datalog query engine, implemented in Clojure, that the student builds across
roughly 8 milestones. This capstone is **one project**, not 10 exercises — but it's
broken into milestones so the student has a sense of progress and the integration tests
can be staged.

## Why this capstone

The course already includes a metacircular evaluator (aspect 08), which is the canonical
SICP capstone. Rather than reuse that, the capstone uses a different paradigm that ties
the rest of the course together:

- **Data > functions > macros** (aspect 04): facts and rules are *just data*. The
  student designs the data shapes.
- **Sequence operations** (aspect 02): query execution is a pipeline of `map`/`filter`/
  `mapcat` over fact streams.
- **Recursion** (aspect 03): rules can be recursive (transitive closure). The query
  engine itself recurses.
- **Polymorphism** (aspect 05): clauses come in different shapes (data clauses, rule
  invocations, predicates) and dispatching on shape is a multimethod-or-protocol
  decision.
- **Macros** (aspect 07): the surface query syntax is more pleasant with a tiny macro
  layer, but the student must keep the macro layer thin (the lesson from aspect 07).
- **A real-world flavor**: Datalog is used in static analysis, code-query tools
  (CodeQL is a Datalog descendant), and some database query languages (Datomic). For a
  vulnerability researcher, this capstone has direct downstream value.

It deliberately does **not** reuse the SICP evaluator from aspect 08. The student already
built that. The capstone shows them a different shape of language tool.

## What "mini-Datalog" means here

A subset of Datalog with the following features:

- **EAV facts.** Facts are `[entity attribute value]` triples, represented as Clojure
  vectors of three elements.
- **Queries** with `:find`, `:where`, and logic variables (symbols starting with `?`).
- **Joins** via shared variables.
- **Rules** that bind a head pattern to a conjunction of body clauses.
- **Recursive rules** (e.g., `ancestor` defined in terms of `parent` and itself).
- **No negation**, **no aggregation**, **no built-in predicates beyond equality**. These
  are mentioned in the README as "where to go next" but out of scope. The student can
  add them on their own time.

A complete query example:

```clojure
(def facts
  [[:alice :parent :bob]
   [:bob   :parent :carol]
   [:carol :parent :dave]])

(def rules
  '[[(ancestor ?a ?d)
     [?a :parent ?d]]
    [(ancestor ?a ?d)
     [?a :parent ?x]
     (ancestor ?x ?d)]])

(query facts rules
       '{:find  [?a]
         :where [(ancestor ?a :dave)]})
;; => #{[:alice] [:bob] [:carol]}
```

## The 8 milestones

Milestones are not exercises in the strict sense — they don't follow the tier
progression. They're a guided breakdown of the project for the student. Each milestone
has a sub-test in `test/` that the student can run independently.

### Milestone 1 — Fact storage

Define the fact data shape and write `add-fact`, `add-facts`, `remove-fact`,
`all-facts`. Backed by a simple set or vector. Tests: facts round-trip, duplicates are
deduplicated.

### Milestone 2 — Pattern matching with logic variables

Implement `(match pattern fact)`, where a pattern is like a fact but may contain
logic variables (symbols starting with `?`). Returns a binding map on success or `nil`
on failure. The bindings unify a logic variable to the value it matched.

Tests:
- `(match '[?e :name "Alice"] [:alice :name "Alice"])` returns `{?e :alice}`
- `(match '[?e :name ?n] [:alice :name "Alice"])` returns `{?e :alice ?n "Alice"}`
- `(match '[?e :name ?n] [:alice :age 30])` returns `nil`
- A pattern with the same variable twice requires consistency.

### Milestone 3 — Single-clause queries

Implement `(query-clause facts pattern)` that returns a sequence of binding maps, one
per matching fact. This is `(keep #(match pattern %) facts)`. Tests cover the basic
cases. (Notice: this is just a `map`/`filter` pipeline. Aspect 02 paid off.)

### Milestone 4 — Multi-clause queries (joins)

Implement `(query-where facts where-clauses)` where `where-clauses` is a vector of
patterns. The result is the join of all clauses by shared variables. Implementation:
fold over clauses, threading the binding maps. For each new clause, for each existing
binding, substitute the binding into the new pattern and `query-clause` the result; the
new binding maps merge the old plus the new.

Tests cover:
- Single clause (degenerates to milestone 3)
- Two clauses with a shared variable (an actual join)
- Three clauses with chained shared variables
- A clause that filters all bindings out (empty result)

### Milestone 5 — `:find` projection

Implement `(query facts {:find vars :where clauses})` that runs the where-query and
projects out only the requested variables, returning a *set* of result tuples (so
duplicates collapse). Tests cover single-variable find, multi-variable find, and the
deduplication behavior.

### Milestone 6 — Rules (non-recursive)

Add support for rules. A rule is a vector `[head body...]` where the head is a logic
expression and the body is a list of clauses. Implement rule expansion: when a where
clause is a rule invocation, expand it by substituting the bound arguments into the
rule body and recursively querying the body.

Tests cover a simple non-recursive rule (e.g., `(grandparent ?g ?c)` defined in terms
of two `parent` clauses).

### Milestone 7 — Recursive rules

Extend the rule machinery to support recursive rules. Implement using **semi-naive
evaluation**: iterate the rule until the result set stops growing.

Tests cover:
- `ancestor` defined recursively from `parent`
- `reachable` from a graph of `edge` facts
- A rule that *would* infinite-loop if you weren't checking for fixed-point

This is the milestone where most students will struggle. Hint that the answer is to
keep applying the rule until no new facts are derived.

### Milestone 8 — Surface syntax macro

Add a `defrule` macro and a `?-` query macro that lets the student write queries without
all the quoting. The macros must be **thin** — they expand to calls into the engine
built in milestones 1–7. The lesson from aspect 07 is in play: if the macro layer is
doing real work, it's wrong.

Tests prove that the macro version produces identical results to the data version.

## Reference solution

The `.solutions/reference/` directory contains a complete working implementation in
~300–500 lines of Clojure. The file structure mirrors the milestones:

```
.solutions/reference/
├── deps.edn
├── src/
│   └── datalog/
│       ├── facts.clj          # milestone 1
│       ├── unify.clj          # milestone 2
│       ├── query.clj          # milestones 3–5
│       ├── rules.clj          # milestones 6–7
│       └── syntax.clj         # milestone 8
└── README.md
```

The reference is **idiomatic, not clever**. It's also commented to highlight where each
aspect of the course is being applied — when a sequence operation is used, when a
multimethod is used, etc. This is intentional pedagogy.

## Student starter

The starter has the same file structure as the reference, but each function body is
either `(throw (ex-info "not implemented" {:milestone N}))` or absent. The `deps.edn`
and the test runner are pre-wired so the student can run `clojure -M:test` from day one
and see what's failing.

The starter README lists the milestones in order with one paragraph each.

## Tests

`test/` contains one test namespace per milestone:

```
test/
├── datalog/
│   ├── m1_facts_test.clj
│   ├── m2_unify_test.clj
│   ├── m3_query_clause_test.clj
│   ├── m4_query_where_test.clj
│   ├── m5_find_test.clj
│   ├── m6_rules_test.clj
│   ├── m7_recursive_rules_test.clj
│   └── m8_syntax_test.clj
```

Each milestone test file is independently runnable. The student can advance milestone
by milestone. The two-gate rule still applies to the capstone as a whole: all tests
pass against the reference, all tests fail against the starter.

For milestone 7 (recursive rules), tests have a hard timeout of 10 seconds — a student
who fails to detect fixed-point will infinite-loop, and the test must catch that
without hanging the test runner.

## What "done" looks like

- All 8 milestone test files pass against the reference.
- All 8 milestone test files fail against the starter.
- The student can run the example query at the top of this file and get the expected
  result, executing against their own implementation.
- The reference solution README walks through which aspects of the course each piece
  draws on.
- The student-facing README explains how to run the capstone tests and milestone-by-
  milestone progression.

## What's out of scope

- Negation, aggregation, comparison built-ins.
- Indexing for performance (linear scan is fine; this is a learning tool).
- Stratified evaluation for negation.
- Datalog-with-functions, magic sets, or any other advanced eval strategies.
- A persistent fact store. Facts live in memory.
- Concurrent queries. Single-threaded.

These are mentioned in the capstone README as "where to go next."
