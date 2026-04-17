# Reference Implementation Notes

This directory is a complete, working mini-Datalog engine in ~250 lines of
Clojure. It is the answer key for the capstone.

If you're reading this *before* you've finished, stop and go back. The
capstone earns its weight by fighting through the design questions yourself.
Every shortcut here is one you would have invented in 30 more minutes of
struggle.

If you're reading this *after* you've finished, welcome. Below is a tour of
which course aspects each piece of the engine exercises, and a few notes on
the design decisions that aren't obvious from the code.

## The course aspects, by file

### [`facts.clj`](src/datalog/facts.clj) — Aspect 04 (Data and abstraction)

The fact-base is a Clojure set. That's it. No wrapper type, no protocol,
no constructor that hides the representation. Aspect 04's lesson — facts are
*just data* — drives this directly. `add-fact` is `conj`. `remove-fact` is
`disj`. `all-facts` is `seq`. The whole file is 27 lines because it has to
be: there is no machinery to add.

A student arriving from Java instinctively reaches for `class FactBase` and
`addFact()`. Resist that. The cost of the wrapper would be: every other
file would have to call `(.facts fb)` to get back to a sequence, and the
sequence library wouldn't apply directly. The reference shows what you give
up by introducing an opaque type prematurely.

### [`unify.clj`](src/datalog/unify.clj) — Aspect 05 (Polymorphism)

`match-slot` is a `cond` that dispatches on the *shape* of the pattern slot:
lvar, literal-equal, or literal-mismatch. That dispatching is the first
appearance of polymorphism in the engine — and we do it with a `cond`, not
a multimethod or protocol, because there are only three cases and they live
in one function. Aspect 05's other lesson, "use the simplest dispatch that
fits", applies here as much as the polymorphism lesson itself.

`match` is a reduce that short-circuits on first failure (using `reduced`).
This is aspect 02 at work even in the matching layer.

`substitute` is `mapv` over the pattern. It exists because join state has to
flow forward into later clauses — without `substitute`, multi-clause queries
can't communicate bindings across clause boundaries.

### [`query.clj`](src/datalog/query.clj) — Aspects 02 (Sequences) and 03 (Recursion)

`query-clause` is `(keep #(match pattern %) facts)`. One line. This is
where the sequence abstraction from aspect 02 pays off most concretely:
querying is filtering, and `keep` is the right tool for "filter and
transform in one pass".

`query-where` is a `reduce` over clauses. The accumulator is a *seq* of
binding maps (the joins-so-far). Each new clause is applied via `mapcat`:
for each accumulated binding, expand it by re-querying with the binding
pre-loaded. This is a textbook example of the fold from aspect 03 doing
non-trivial structural work — the input is a seq of clauses, the output
is a seq of bindings, and the relationship between the two is the join
semantics.

`project` is `(into #{} (map ...) bindings-seq)`. The use of a transducer
form here is mild aspect 02 polish — a vanilla `(set (map ...))` would do
the same thing, but the transducer avoids a transient intermediate seq.

### [`rules.clj`](src/datalog/rules.clj) — Aspects 03 (Recursion), 04 (Data), 05 (Polymorphism)

This is the densest file. Three things to look at:

**Polymorphism in `match-clause`.** A clause is either a vector (data
pattern) or a list (rule invocation). The dispatch is a single `if` on
`rule-invocation?`. We could have used a multimethod or protocol; we didn't,
because two cases in one function is the threshold below which `if` is
clearer. Compare to your own code on its first pass — did you reach for the
heavier dispatch mechanism?

**Bottom-up evaluation in `saturate`.** The recursion lesson here is
*structural* recursion, not call-stack recursion: `loop`/`recur` walks the
fact-derivation lattice from bottom to top. Each `step` produces a new
derived-relations map; we compare to the previous one for equality and stop
when nothing changed. This is a fixed-point loop, and Clojure's value
equality on persistent maps is what makes the termination check trivial.

A naive top-down evaluator would expand `(ancestor ?x ?d)` recursively in
the body of the same rule, hit infinite recursion on left-recursive rules,
and need either tabling or memoization to terminate. Bottom-up sidesteps
the problem entirely. The cost is computing the *full* deductive closure
for every query, even when only a tiny slice is asked for. A
production-grade engine would mix top-down with magic sets; we don't,
because that's a different lesson.

**Rules-as-data in `rules.clj`.** Notice the file does not destructure
rules into a record or a struct. A rule is a vector. `(first rule)` is the
head, `(rest rule)` is the body. The whole engine consumes rule data
through normal sequence operations. Aspect 04's payoff again.

### [`syntax.clj`](src/datalog/syntax.clj) — Aspect 07 (Macros)

The macros are deliberately *thin*. `defrule` does one thing: it `swap!`s a
quoted form into the registry. `?-` does one thing: it builds a query map
and calls into the engine. Neither macro contains any logic that couldn't
be a function — they exist only to suppress quoting at the call site, which
is the one job that genuinely requires a macro.

The temptation when writing `?-` is to add binding helpers, optional
clauses, or pretty-printing. Don't. Aspect 07's central lesson — "if a
macro is doing real work, it's wrong" — is exactly what's being tested by
this file. The reference is short on purpose.

The atom-based registry (`*rules*`) is a small concession to ergonomics:
it lets the student call `(defrule ...)` interactively in a REPL without
threading a rule collection through every query. The trade-off is that
`*rules*` is mutable global state, which is exactly what aspect 06 told you
to be careful about. It's tolerable here because the registry is a *script
convenience*, not a load-bearing engine component — every test in the
M8 suite calls `(reset-rules!)` first, and the engine itself takes rules
as an explicit argument. If you wanted to remove the atom and pass rules
explicitly to `?-`, you could — and the M8 macro layer would shrink to four
lines.

## Things that aren't in here that arguably should be

A few choices the reference deliberately doesn't make:

- **No deduplication of derived tuples between rules in one iteration.**
  We over-compute on each `step` and rely on set semantics to dedupe. A
  proper semi-naive implementation would track the *delta* (new tuples
  this iteration) and only apply rules to deltas. We don't, because the
  added bookkeeping would obscure the fixed-point lesson — and on the
  small test cases the redundant work is invisible. Real semi-naive is
  one of the "where to go next" extensions.

- **No subgoal expansion for top-down queries.** All rule resolution is
  bottom-up via `saturate`. This means even a query asking for one fact
  computes the full transitive closure first. For graph problems with
  millions of edges this is fatal; for the test sizes here it's
  imperceptible.

- **No type for "binding map" or "fact-base".** Both are bare maps and
  bare sets. If you wanted to add validation or specs, that's where they'd
  go — but the spec deliberately keeps the engine type-free to keep the
  data-orientation lesson visible.

## Reading order

If you want to retrace the engine bottom-up:

1. [`facts.clj`](src/datalog/facts.clj) — 27 lines, sets all the way down.
2. [`unify.clj`](src/datalog/unify.clj) — single-clause matching.
3. [`query.clj`](src/datalog/query.clj) — `keep`, `reduce`, set
   projection.
4. [`rules.clj`](src/datalog/rules.clj) — the only file with non-trivial
   control flow.
5. [`syntax.clj`](src/datalog/syntax.clj) — proof that the macro layer is
   trivial.

Total reading time: 30 minutes. Total writing time, if you're starting
from scratch: a weekend. The gap between those two numbers is what the
capstone is for.
