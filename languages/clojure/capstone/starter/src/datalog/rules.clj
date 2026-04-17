(ns datalog.rules
  "Milestones 6 & 7 — Rules and recursive rules.

   A rule is a vector `[head & body]` where `head` is a list shaped
   like `(rel-name ?arg1 ?arg2 ...)` and `body` is a sequence of
   clauses. Clauses are either data patterns (vectors) or rule
   invocations (lists) — your `match-clause` dispatches on shape.

   For M7, recursion is the hard part. The reference uses bottom-up
   saturation: iterate the rules over the data + already-derived rule
   tuples until no rule produces a new tuple. Because the deductive
   closure of a finite fact base is finite, this terminates — even
   for left-recursive rules. A naive top-down expander will infinite-
   loop on left recursion, which is why this milestone is the hardest."
  (:require [datalog.unify :as u]
            [datalog.query :as q]))

(defn rule-invocation?
  "True iff `clause` is a rule invocation: a seq whose first element
   is a non-lvar symbol. Data patterns are vectors, so they don't
   qualify."
  [_clause]
  (throw (ex-info "not implemented" {:milestone 6})))

(defn match-clause
  "Match a single clause against the fact-base + derived relations,
   extending `bindings`. Dispatch on whether the clause is a data
   pattern or a rule invocation."
  [_fb _derived _bindings _clause]
  (throw (ex-info "not implemented" {:milestone 6})))

(defn query-where
  "Like datalog.query/query-where, but understands rule invocations
   by looking them up in the derived-relations map."
  [_fb _derived _clauses]
  (throw (ex-info "not implemented" {:milestone 6})))

(defn saturate
  "Iterate the rules until no new tuples are derived. Returns a map
   of rel-name -> set-of-arg-tuples."
  [_fb _rules]
  (throw (ex-info "not implemented" {:milestone 7})))

(defn query
  "Run a query of shape {:find [...] :where [...]} against the
   fact-base and the rules. The where clauses may reference rules.
   Returns a set of result tuples."
  [_fb _rules _query-map]
  (throw (ex-info "not implemented" {:milestone 6})))
