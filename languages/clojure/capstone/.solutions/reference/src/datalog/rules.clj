(ns datalog.rules
  "Milestones 6 & 7 — Rules and recursive rules.

   A rule is a vector `[head & body]` where the head is a list shaped
   like `(rel-name ?arg1 ?arg2 ...)` and the body is a sequence of
   clauses. Clauses come in two flavors — data patterns (vectors) and
   rule invocations (lists) — and dispatching on shape (aspect 05) is
   how `match-clause` decides what to do.

   Recursion is handled bottom-up: we iterate the rules over the data
   (and over already-derived rule tuples) until the derived relations
   stop growing — a fixed-point loop (aspect 03). For any finite fact
   base the deductive closure is finite, so termination is guaranteed
   even for left-recursive rules."
  (:require [datalog.unify :as u]
            [datalog.query :as q]))

(defn rule-invocation?
  "True iff `clause` is a rule invocation: a seq whose first element is
   a non-lvar symbol. Data patterns are vectors, so they don't qualify."
  [clause]
  (and (seq? clause)
       (symbol? (first clause))
       (not (u/lvar? (first clause)))))

(defn- head-name [head] (first head))
(defn- head-args [head] (vec (rest head)))

(defn match-clause
  "Match a single clause against the fact-base + derived relations,
   extending `bindings`. Dispatches on whether the clause is a data
   pattern or a rule invocation. Returns a seq of extended bindings."
  [fb derived bindings clause]
  (if (rule-invocation? clause)
    (let [rname     (head-name clause)
          call-args (head-args clause)
          tuples    (get derived rname #{})]
      (keep #(u/match bindings call-args (vec %)) tuples))
    (q/query-clause fb bindings clause)))

(defn query-where
  "Like `datalog.query/query-where`, but understands rule invocations
   by looking them up in the derived-relations map."
  [fb derived clauses]
  (reduce
   (fn [bindings-seq clause]
     (mapcat (fn [b] (match-clause fb derived b clause)) bindings-seq))
   [{}]
   clauses))

(defn- derive-from-rule
  "Apply one rule against the current state. Returns the set of
   argument-tuples derivable for the rule's head."
  [fb derived rule]
  (let [head         (first rule)
        body         (rest rule)
        args         (head-args head)
        bindings-seq (query-where fb derived body)]
    (into #{} (map (fn [b] (mapv b args))) bindings-seq)))

(defn- step
  "One bottom-up iteration: apply every rule once, accumulating new
   tuples into the derived-relations map."
  [fb rules derived]
  (reduce
   (fn [acc rule]
     (let [rname (head-name (first rule))
           tups  (derive-from-rule fb acc rule)]
       (update acc rname (fnil into #{}) tups)))
   derived
   rules))

(defn saturate
  "Iterate `step` until no rule produces a new tuple. For any finite
   fact-base the deductive closure is finite, so this terminates even
   for left-recursive rules."
  [fb rules]
  (loop [derived {}]
    (let [next (step fb rules derived)]
      (if (= next derived)
        derived
        (recur next)))))

(defn query
  "Run a query of shape `{:find [...] :where [...]}` against the
   fact-base and the rules. The where clauses may reference rules.
   Returns a set of result tuples."
  [fb rules {:keys [find where]}]
  (let [derived      (saturate fb rules)
        bindings-seq (query-where fb derived where)]
    (q/project find bindings-seq)))
