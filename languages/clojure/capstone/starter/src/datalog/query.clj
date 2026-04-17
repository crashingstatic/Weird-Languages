(ns datalog.query
  "Milestones 3–5 — Single-clause queries, joins, and :find projection.

   Build the query engine on top of `datalog.unify/match`. M3 is one
   pattern over the fact stream — a `keep` pipeline. M4 folds multiple
   clauses, threading bindings between them so shared lvars implement
   joins. M5 projects the resulting binding maps onto the requested
   find-vars and returns a deduplicated set of tuples."
  (:require [datalog.facts :as facts]
            [datalog.unify :as u]))

(defn query-clause
  "Milestone 3. Match a single pattern against every fact in the
   fact-base. Returns a sequence of binding maps, one per matching
   fact. The 3-arg form extends an existing bindings map."
  ([_fb _pattern]
   (throw (ex-info "not implemented" {:milestone 3})))
  ([_fb _bindings _pattern]
   (throw (ex-info "not implemented" {:milestone 3}))))

(defn query-where
  "Milestone 4. Run a sequence of where-clauses against the fact-base
   and return all binding maps that satisfy every clause. Implementation
   hint: fold over the clauses, expanding each accumulated binding by
   substituting it into the next pattern and re-querying."
  [_fb _clauses]
  (throw (ex-info "not implemented" {:milestone 4})))

(defn project
  "Project a sequence of binding maps onto a vector of find-vars,
   returning a set of result tuples. Sets give free deduplication —
   the standard Datalog semantics for :find."
  [_find-vars _bindings-seq]
  (throw (ex-info "not implemented" {:milestone 5})))

(defn query
  "Milestone 5. Run a query map of shape {:find [...] :where [...]}
   against the fact-base. Returns a set of result tuples projected
   onto the find vars. (No-rules version; rules come in M6.)"
  [_fb _query-map]
  (throw (ex-info "not implemented" {:milestone 5})))
