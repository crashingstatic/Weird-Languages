(ns datalog.unify
  "Milestone 2 — Pattern matching with logic variables.

   A pattern is shaped like a fact but may contain logic variables —
   symbols whose name starts with '?'. Matching a pattern against a
   fact returns a binding map (lvar -> value) on success or nil on
   failure. The polymorphism lesson from aspect 05 is in play here:
   we dispatch on whether each pattern slot is an lvar or a literal.")

(defn lvar?
  "True iff `x` is a logic variable: a symbol whose name starts with '?'."
  [x]
  (and (symbol? x)
       (.startsWith (name x) "?")))

(defn- match-slot
  "Match a single pattern slot against the corresponding fact value.
   Returns the (possibly extended) bindings map on success, or nil on
   failure. A repeated lvar must bind to a consistent value."
  [bindings pat-slot fact-val]
  (cond
    (lvar? pat-slot)
    (if-let [existing (find bindings pat-slot)]
      (when (= (val existing) fact-val) bindings)
      (assoc bindings pat-slot fact-val))

    (= pat-slot fact-val)
    bindings

    :else nil))

(defn match
  "Match `pattern` against `fact`. Returns a bindings map on success,
   nil on failure. Optionally takes an existing bindings map to
   extend (so we can match incrementally during joins)."
  ([pattern fact]
   (match {} pattern fact))
  ([bindings pattern fact]
   (when (= (count pattern) (count fact))
     (reduce (fn [acc [p f]]
               (if-let [acc' (match-slot acc p f)]
                 acc'
                 (reduced nil)))
             bindings
             (map vector pattern fact)))))

(defn substitute
  "Walk `pattern`, replacing any lvar that appears in `bindings` with
   its bound value. Lvars not in bindings are left alone. This is how
   we propagate join state from earlier clauses into later ones."
  [bindings pattern]
  (mapv (fn [slot]
          (if (and (lvar? slot) (contains? bindings slot))
            (get bindings slot)
            slot))
        pattern))
