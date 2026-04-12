# Clojure Aspects

The 8 aspects of the Clojure module, in order, with the 10-exercise progression for each.
SICP chapter mappings shown where the alignment is clear; mappings are guidance, not gospel.

Each aspect's exercises follow the scaffolding tier rule:
- **T1 (exercises 01–03):** fill-in-the-blank
- **T2 (exercises 04–07):** guided
- **T3 (exercises 08–10):** open

The first aspect ramps gentler than the others — exercise 01 of aspect 01 is the most
heavily scaffolded thing in the entire course.

---

## Aspect 01 — Functional Foundations

**SICP:** Chapter 1.1–1.2
**Big idea:** Programs as expressions that produce values, not statements that mutate state.
Functions are the primary unit of abstraction.
**Why it matters:** A C-family programmer's reflex is "what does this *do*?" A Lisp
programmer's reflex is "what does this *evaluate to*?" Until that switch flips, none of
the rest will land. Aspect 01 exists to flip it.
**Prerequisites:** None.
**Vocabulary introduced:** `def`, `let`, `defn`, `if`, `cond`, `fn`, basic arithmetic,
numeric tower basics.

**Exercises:**

1. **[T1] hello-values** — Fill in `def` forms to bind names to numeric and string
   values. Test asserts on the bindings. Goal: get the student to type their first
   parens and see them load. Heavy hand-holding in PROBLEM.md.
2. **[T1] let-binding** — Fill in a `let` form that computes the area of a circle from a
   given radius, using a local binding for `pi`. Introduces lexical scope.
3. **[T1] absolute-value** — Fill in the missing branch of an `if` form. Introduces
   conditionals as expressions (not statements).
4. **[T2] basic-arithmetic-fns** — Implement `square`, `cube`, `average`, and
   `distance-2d` from scratch. No starter beyond signatures. The point: `defn` is just
   sugar for `def` + `fn`.
5. **[T2] newton-sqrt** — Implement square root via Newton's method, given the recursive
   `improve` and `good-enough?` helpers. Direct port of SICP 1.1.7. The student writes
   the recursive `sqrt-iter` themselves.
6. **[T2] power-recursive** — Implement `(power b n)` recursively. Tests: small inputs
   pass, n=0 returns 1.
7. **[T2] gcd-euclid** — Implement Euclid's algorithm. Tests cover swapped argument
   order, identical inputs, and one-argument-zero edge cases.
8. **[T3] fast-power** — Implement `(power b n)` in O(log n) time using the
   square-and-halve trick (SICP 1.2.4 exercise). Test asserts the function completes
   quickly on n=1000000 (a naive O(n) solution will time out).
9. **[T3] fib-two-ways** — Implement `(fib-tree n)` (tree-recursive) and `(fib-iter n)`
   (linear iterative). Both must return correct values; the test for `fib-iter` includes
   n=80 to ensure it doesn't blow up.
10. **[T3] count-change** — SICP 1.2.2 coin-change problem. Open implementation. Test
    covers the SICP example (count of ways to change $1 in US coins = 292) plus edge
    cases.

---

## Aspect 02 — Higher-Order Functions and the Sequence Library

**SICP:** Chapter 1.3
**Big idea:** Functions are values. Functions take functions and return functions. The
sequence library (`map`, `filter`, `reduce`, `comp`, `partial`) is how Clojure programmers
express most computation that a C programmer would write as a `for` loop.
**Why it matters:** The for-loop reflex is the single biggest tell of a non-functional
programmer in a functional language. This aspect breaks that reflex by showing how every
common loop pattern decomposes into sequence operations, then has the student build the
sequence operations from scratch so they understand they're not magic.
**Prerequisites:** Aspect 01.
**Vocabulary introduced:** `map`, `filter`, `reduce`, `comp`, `partial`, anonymous
function shorthand `#()`, varargs, `apply`.

**Exercises:**

1. **[T1] map-double** — Fill in a single `map` call to double every element of a list.
2. **[T1] filter-evens** — Fill in a `filter` call with a predicate.
3. **[T1] reduce-sum** — Fill in `reduce` with `+` and an initial value.
4. **[T2] my-map** — Implement `map` from scratch using recursion. No use of the
   built-in `map`. Tests check laziness is not required.
5. **[T2] my-filter** — Same exercise for `filter`.
6. **[T2] my-reduce** — Same for `reduce`. Tests cover the with-init and without-init
   arities.
7. **[T2] pipeline-with-comp** — Given a list of customer records (maps), compute the
   sum of order totals for active customers in a specific region. Must use `comp`,
   `filter`, `map`, and `reduce`. Tests assert on the result.
8. **[T3] frequencies-from-scratch** — Implement Clojure's `frequencies` (returns a map
   from element → count). Cannot use `frequencies`. Tests cover empty input, single
   element, and a long input with ties.
9. **[T3] compose-many** — Implement `(compose-all fs)` that takes a sequence of
   one-arg functions and returns their right-to-left composition. Tests assert on
   identity (empty input) and a multi-step transform.
10. **[T3] running-stats** — Implement `(running-stats numbers)` that returns a sequence
    of `{:count n :sum s :mean m}` maps showing the running statistics through the
    input. Open implementation. The natural solution uses `reductions`. Hint absent.

---

## Aspect 03 — Recursion, Iteration, and `recur`

**SICP:** Chapter 1.2 (the Clojure-specific framing)
**Big idea:** Clojure runs on the JVM, which doesn't do general tail-call optimization.
`recur` is the workaround — it's a language-level "jump to the top of this function or
loop with new arguments" that gives you constant-stack iteration. Understanding when you
need `recur` and when you don't is essential.
**Why it matters:** A Scheme programmer writes naive recursion and the language handles
it. A Clojure programmer who writes naive recursion blows the stack on real input. This
aspect makes the JVM constraint visible and gives the student the tools to work within it.
**Prerequisites:** Aspects 01, 02.
**Vocabulary introduced:** `loop`, `recur`, accumulator pattern, the difference between
linear-recursive and linear-iterative processes (SICP 1.2.1).

**Exercises:**

1. **[T1] convert-to-recur** — A naive recursive `factorial` is provided. Fill in the
   `loop`/`recur` rewrite (most of the structure given, missing the recur args).
2. **[T1] tail-recur-sum** — Fill in a `recur` call in the tail position of a provided
   `loop`.
3. **[T1] accumulator-pattern** — Fill in the accumulator initial value and the recur
   step in a sum-to-n function.
4. **[T2] my-reverse** — Implement `(my-reverse xs)` using `loop`/`recur`. Test asserts
   on small input AND a 100k-element input (would StackOverflow if done naively).
5. **[T2] my-length** — Same. Test covers empty, one element, and 1M elements.
6. **[T2] my-nth** — Implement `(my-nth xs n)` with proper bounds checking. Throw on
   out-of-bounds. Tests cover negatives, beyond-end, exact-end.
7. **[T2] tree-sum** — Sum all numbers in a nested vector structure. This one is
   *naturally tree-recursive* and cannot be expressed with `recur` alone. The point: not
   every recursion can be flattened. Tests include a deeply nested structure.
8. **[T3] count-occurrences** — Count occurrences of a target value in an arbitrarily
   nested structure. Open. Combines tree recursion with the lessons from aspect 02.
9. **[T3] my-flatten** — Implement `(my-flatten coll)`. Test against Clojure's built-in
   `flatten` for many inputs.
10. **[T3] vending-machine** — Implement a vending-machine state transition function as
    a `loop`/`recur` driver. Given an initial state and a sequence of inputs (insert
    coin, select item, refund), return the final state. Tests check several scenarios.

---

## Aspect 04 — Data and Abstraction

**SICP:** Chapter 2.1–2.2
**Big idea:** "Data > Functions > Macros." The Clojure community's strong preference is
to model the world as immutable data first, write small functions that transform it, and
reach for protocols/records/macros only when data isn't enough. The persistent collection
literals (vectors, maps, sets) are not afterthoughts — they're the substrate.
**Why it matters:** A Java programmer reaches for a class. A C programmer reaches for a
struct. A Clojure programmer reaches for a map. This shift — from "what is the noun?" to
"what is the shape of the data?" — is the single biggest day-to-day difference in how
Clojure code is written.
**Prerequisites:** Aspects 01, 02, 03.
**Vocabulary introduced:** vectors, maps, sets, keywords, `assoc`, `dissoc`, `update`,
`get`, `get-in`, `assoc-in`, `update-in`, destructuring (positional and associative),
`select-keys`, `merge`.

**Exercises:**

1. **[T1] person-map** — Fill in a `def` to bind a map representing a person with `:name`,
   `:age`, and `:email`.
2. **[T1] assoc-update** — Fill in calls to `assoc` and `update` to modify a person map.
3. **[T1] destructure-args** — Fill in destructured argument lists in two function
   definitions.
4. **[T2] point-2d-abstract** — Implement a 2D point as a vector `[x y]` with a
   constructor `make-point`, selectors `point-x`, `point-y`, and a `distance` function.
   The point: data abstraction barriers. Tests treat the point as opaque.
5. **[T2] rational-arithmetic** — SICP 2.1.1 rational number package. `make-rat`,
   `numer`, `denom`, `add-rat`, `mul-rat`, `equal-rat?`. Reduce to lowest terms.
6. **[T2] deck-of-cards** — Model a 52-card deck as data. Implement `make-deck`,
   `shuffle-deck`, `deal n`, `cards-of-suit`. Test for correctness, not for randomness.
7. **[T2] nested-update** — Given a map of teams to maps of player names to stats, write
   a function that increments a specific stat for a specific player. Use `update-in`.
8. **[T3] binary-tree-of-data** — Implement an immutable binary search tree as nested
   maps `{:value v :left t :right t}`. Functions: `insert`, `contains?`, `in-order-seq`.
   No protocols, no records — just nested maps.
9. **[T3] interval-arithmetic** — SICP 2.1.4 interval arithmetic package. `make-interval`,
   `add-interval`, `mul-interval`, `width`. Tests cover the gotchas SICP highlights
   (subtraction, division by intervals containing zero).
10. **[T3] inventory-system** — Open data-modeling exercise. Given a description of a
    small store inventory (items, quantities, prices, suppliers), design the data shape
    and implement operations: `add-stock`, `sell-item`, `low-stock-items`, `total-value`.
    Test asserts on operation results, not data shape, so the student has freedom in
    design.

---

## Aspect 05 — Polymorphism and Protocols

**SICP:** Chapter 2.4–2.5 (generic operations, tagged data, dispatch)
**Big idea:** Polymorphism in Clojure comes in two flavors: **multimethods** (open
dispatch on any function of the arguments — totally flexible, slightly slower) and
**protocols** (closed dispatch on the type of the first argument — fast, JVM-friendly,
the workhorse). They solve different problems and the student needs to know when to use
which.
**Why it matters:** A Java programmer's reflex is "make a class hierarchy." A Clojure
programmer's reflex is "what's the actual dispatch axis?" Sometimes it's type. Sometimes
it's a tag in the data. Sometimes it's a runtime computed value. Clojure separates the
*choice of dispatch axis* from the *implementation* — Java can't.
**Prerequisites:** Aspects 01–04.
**Vocabulary introduced:** `defmulti`, `defmethod`, `derive`, `isa?`, `defprotocol`,
`defrecord`, `extend-protocol`, `extend-type`, `reify`.

**Exercises:**

1. **[T1] multimethod-shape-area** — A `defmulti` and two `defmethod` forms are
   provided. Fill in the third (triangle).
2. **[T1] defprotocol-greet** — Fill in a one-method protocol declaration.
3. **[T1] defrecord-person** — Fill in a `defrecord` that implements the protocol from
   exercise 02.
4. **[T2] shape-protocol** — Implement a `Shape` protocol with `area` and `perimeter`.
   Implement Circle, Rectangle, Triangle as records. Tests cover all three plus a
   composite via `reify`.
5. **[T2] animal-multimethods** — Implement an `Animal` taxonomy using multimethods with
   `derive`. Cat and dog inherit from mammal; sparrow inherits from bird; both inherit
   from animal. The `sound` multimethod dispatches on the most specific tag.
6. **[T2] when-to-use-which** — Take a multimethod-based file (provided) that dispatches
   on the `:type` field of a map and convert it to use a protocol. Then, in a comment in
   the solution, explain in 2 sentences when you'd choose the multimethod version
   anyway. Tests are unchanged across both versions.
7. **[T2] expr-eval-multi** — A small expression type with `:number`, `:sum`, `:product`
   variants. Implement `eval-expr` as a multimethod dispatching on the `:op` key.
8. **[T3] tree-visitor** — Implement a polymorphic tree visitor that walks a tree of
   expressions and computes both the result *and* a list of every operation performed.
   Use protocols. Test against several expression trees.
9. **[T3] tagged-arithmetic** — SICP 2.4.2 tagged-data system: implement
   `attach-tag`/`type-tag`/`contents` and an `add` operation that dispatches on the
   types of both arguments via a multimethod.
10. **[T3] generic-arithmetic** — SICP 2.5.1: a generic arithmetic package over
    integers, rationals, and real numbers. `add`, `sub`, `mul`, `div`, `equ?`, `zero?`.
    Implement type coercion (an int + a rational becomes a rational).

---

## Aspect 06 — State, Identity, and Concurrency

**SICP:** Chapter 3.1–3.4
**Big idea:** Clojure's "value/identity/state" model: a *value* is immutable, an
*identity* is a logical entity that takes on different values over time, and *state* is
the value an identity has at a given moment. Atoms, refs, and agents are different
**reference types** for managing identity, each suited to a different concurrency story.
**Why it matters:** A Java programmer says "this counter has the value 5." A Clojure
programmer says "the counter identity currently refers to the immutable value 5; in a
moment it may refer to the immutable value 6." This sounds like word games until you've
debugged a race condition that the Clojure formulation makes literally impossible to
write.
**Prerequisites:** Aspects 01–04. (Aspect 05 helpful but not required.)
**Vocabulary introduced:** `atom`, `swap!`, `reset!`, `ref`, `dosync`, `alter`,
`commute`, `agent`, `send`, `send-off`, `await`, `deref` / `@`, watches.

**Exercises:**

1. **[T1] atom-counter** — Fill in `swap!` to increment an atom.
2. **[T1] ref-balance** — Fill in `dosync` and `alter` to modify a ref.
3. **[T1] agent-logger** — Fill in `send` to add a log line to an agent's state.
4. **[T2] thread-safe-counter** — Implement a counter using an atom. Test spawns 100
   futures each incrementing 1000 times and asserts the final value is 100000.
5. **[T2] bank-account** — Single account with `deposit` and `withdraw`. Withdraw fails
   atomically if the balance would go negative. Test under contention.
6. **[T2] bank-transfer** — Two accounts, transfer money between them. Must use refs
   and `dosync` so that money never disappears or appears mid-transfer. Test spawns
   many concurrent transfers and asserts the total balance is invariant.
7. **[T2] memoize-with-atom** — Implement `(memoize-with-atom f)` that returns a memoized
   version of `f` backed by an atom-held cache.
8. **[T3] thread-safe-queue** — Implement a FIFO queue with `enqueue` and `dequeue`
   using a ref. Concurrent producers and consumers must not corrupt it.
9. **[T3] agent-counter-ring** — Build a ring of N agents that pass a token around. The
   token visits each agent in turn and increments a shared counter. Demonstrates `send`
   and message ordering guarantees.
10. **[T3] deadlock-and-fix** — A buggy implementation of `transfer` is provided that
    can deadlock under adversarial scheduling. Diagnose and fix it. The fix is to use
    refs (which Clojure's STM handles) instead of nested locks. The lesson is that
    the STM was designed to make this class of bug impossible.

---

## Aspect 07 — Macros and Code-as-Data

**SICP:** Chapter 4 (metalinguistic abstraction, framing)
**Big idea:** Code is data. A Clojure program is a tree of lists, vectors, maps,
symbols, and literals. Macros are functions that take that tree and return a new tree,
which then gets evaluated. The macro system is the Lisp superpower — it lets you extend
the language itself. It is also dangerous and overused, and the most important lesson is
**when not to write a macro.**
**Why it matters:** Every other language has a fixed grammar. In Clojure, you can add
new syntactic forms. This is genuinely something you can't do in any C-family language
(template metaprogramming and Java annotations are not the same thing). It's also the
hardest aspect to teach because the temptation to use macros for things functions can do
is overwhelming, and resisting it is the actual lesson.
**Prerequisites:** Aspects 01–04. Aspect 06 helps for the `with-resource`-style example.
**Vocabulary introduced:** `quote` / `'`, `unquote` / `~`, `unquote-splicing` / `~@`,
syntax-quote / `` ` ``, `defmacro`, `gensym`, `macroexpand`, `macroexpand-1`, hygiene
(or the lack thereof).

**Exercises:**

1. **[T1] quote-and-unquote** — Fill in quoted and unquoted forms inside a syntax-quoted
   template. Tests assert on the resulting list structure (not on evaluation).
2. **[T1] unless-macro** — Fill in the body of a `defmacro unless` that expands to an
   `if` with the branches swapped.
3. **[T1] when-positive** — Fill in a `defmacro when-positive` that takes a number and
   a body, evaluating the body only if the number is positive.
4. **[T2] when-not-from-scratch** — Implement Clojure's `when-not` as a macro. Tests
   prove (a) it returns the right value and (b) it does NOT evaluate its body when the
   condition is truthy. The second test is the killer test.
5. **[T2] infix-macro** — Implement an `infix` macro that takes `(infix (1 + 2 * 3))`
   and evaluates as `(+ 1 (* 2 3))`. Limited operator set; precedence as in math.
6. **[T2] with-timing** — Implement a `with-timing` macro that prints the elapsed time
   after evaluating its body and returns the body's value. Uses `gensym` to avoid
   variable capture.
7. **[T2] cond-thread** — Implement a simplified `cond->` that threads a value through
   conditional steps. Tests cover threading correctness.
8. **[T3] log-context** — Implement a `with-log-context` macro that adds a key/value pair
   to a dynamically scoped log context, evaluates its body, and removes the context on
   exit (even on exception). Will need `try`/`finally` and a dynamic var.
9. **[T3] state-machine-dsl** — Implement a `defstate-machine` macro that takes a
   declarative state-machine definition and emits the functions to run it. The student
   defines the surface syntax; tests run example state machines through the result.
10. **[T3] when-not-to-write-a-macro** — A function-based solution to a problem is
    provided alongside an over-engineered macro version of the same. The student must
    (a) write a third version that's the simplest possible function, (b) explain in 2
    sentences why the macro version is wrong, and (c) write one test that catches a
    bug in the macro version that the function version doesn't have. This exercise has
    no automated grading on (b); the test runner checks (a) and (c). The human reviews
    (b). Flag this in PROBLEM.md.

---

## Aspect 08 — Building a Language: A Tiny Evaluator

**SICP:** Chapter 4.1 (the metacircular evaluator)
**Big idea:** Implement a small Scheme-like language in Clojure. By the end of this
aspect, the student has built `eval` and `apply`, has a working environment model with
proper lexical scoping, and understands what was happening under the hood the entire time
they were writing Clojure. This is where the course earns its "rewires your brain" claim.
**Why it matters:** Most programmers go their entire careers without writing an
interpreter for the language they're writing in. SICP's central pedagogical move is that
once you've done this, you understand evaluation and scoping at a level that nothing else
can teach you. The Clojure host language is a good substrate because the data
representations are so close to s-expressions you can read and write the AST as native
Clojure data.
**Prerequisites:** Aspects 01–07. This is the final aspect for a reason.
**Vocabulary introduced:** AST, environment model, lexical scope, closure, special form,
primitive procedure, REPL.

**Exercises:**

1. **[T1] tokenizer** — Fill in a few branches of a provided tokenizer that converts a
   string `"(+ 1 2)"` into a sequence of tokens.
2. **[T1] eval-self-evaluating** — Fill in the case of an evaluator that handles
   numbers and strings (return them unchanged).
3. **[T1] env-lookup** — Fill in `(lookup-var env name)` that walks a chain of frames
   and returns the bound value.
4. **[T2] extend-env-and-define** — Implement `(extend-env env names values)` and
   `(eval-define exp env)`. The environment is a list of frames (each frame a map).
5. **[T2] if-and-quote** — Implement the `if` and `quote` special forms.
6. **[T2] primitive-procedures** — Implement a primitives table for `+`, `-`, `*`, `/`,
   `=`, `<`, `>`, `not`. Tests evaluate small programs.
7. **[T2] lambda-and-application** — Implement `lambda` (returns a procedure value
   capturing the current environment) and procedure application (extends the captured
   environment with the parameter bindings and evaluates the body). After this exercise
   the student has a working language.
8. **[T3] let-desugaring** — Implement `let` and `let*` as syntactic sugar that desugar
   to lambda application before evaluation. Test that `let*` allows a binding to refer
   to an earlier one in the same form.
9. **[T3] closures-and-lexical-scope** — No new code is added, but a comprehensive test
   suite is provided that exercises lexical scoping: nested lambdas, captured
   variables, shadowing, the classic "closures in a loop" test. The student's evaluator
   from exercises 1–8 must pass all of them. If it doesn't, the bug is in their
   environment handling — and finding it is the lesson.
10. **[T3] tiny-repl** — Implement a REPL function that reads a line of input, tokenizes,
    parses, evaluates, and prints the result. Test feeds a sequence of inputs and
    asserts on the printed outputs. The student now has a working tiny Lisp running on
    top of Clojure, and they wrote every line of it themselves.

---

## End of aspects

After aspect 08, the student proceeds to the capstone (`clojure/CAPSTONE.md`), which
builds a mini-Datalog query engine using everything they've learned.
