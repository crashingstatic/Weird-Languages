(ns datalog.query
  "Milestones 3–5 — Single-clause queries, joins, and :find projection.

   Query execution is a sequence-pipeline (aspect 02): for each fact, try
   to match the pattern, keep the bindings that succeed. Joining clauses
   is a fold (aspect 03): each new clause filters and extends the
   bindings produced by the previous clauses."
  (:require [datalog.facts :as facts]
            [datalog.unify :as u]))

(defn query-clause
  "Milestone 3. Match a single pattern against every fact in the
   fact-base. Returns a sequence of binding maps, one per matching
   fact. Optionally extends an existing bindings map.

   Note: this is a `keep` over the fact stream — the cleanest possible
   expression of the sequence abstraction from aspect 02."
  ([fb pattern]
   (query-clause fb {} pattern))
  ([fb bindings pattern]
   (keep #(u/match bindings pattern %) (facts/all-facts fb))))

(defn query-where
  "Milestone 4. Run a sequence of where-clauses against the fact-base
   and return all binding maps that satisfy *every* clause. Clauses
   joined by shared logic variables.

   Implementation: fold over the clauses. The accumulator is a seq of
   binding maps (the joins so far). For each new clause, expand each
   accumulated binding by substituting it into the pattern and
   running query-clause from that binding. The result of the fold is
   the natural join of all clauses."
  [fb clauses]
  (reduce
   (fn [bindings-seq clause]
     (mapcat (fn [b] (query-clause fb b clause)) bindings-seq))
   [{}]
   clauses))

(defn project
  "Project a sequence of binding maps onto a vector of find-vars,
   returning a *set* of result tuples. Sets give us free deduplication —
   the standard Datalog semantics for `:find`."
  [find-vars bindings-seq]
  (into #{} (map (fn [b] (mapv b find-vars))) bindings-seq))

(defn query
  "Milestone 5. Run a query map of shape `{:find [...] :where [...]}`
   against the fact-base. Returns a set of result tuples projected
   onto the find vars.

   This is the no-rules version. Milestone 6's `datalog.rules/query`
   wraps it with rule expansion."
  [fb {:keys [find where]}]
  (project find (query-where fb where)))
