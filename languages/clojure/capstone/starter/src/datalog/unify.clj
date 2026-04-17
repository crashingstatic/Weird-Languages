(ns datalog.unify
  "Milestone 2 — Pattern matching with logic variables.

   A logic variable is a symbol whose name starts with '?'. A pattern
   is shaped like a fact but its slots may be lvars or literals.
   `match` unifies a pattern against a fact, returning a binding map
   on success or nil on failure. `substitute` is the inverse direction:
   replace any bound lvar in a pattern with its value.")

(defn lvar?
  "True iff `x` is a logic variable: a symbol whose name starts with '?'."
  [_x]
  (throw (ex-info "not implemented" {:milestone 2})))

(defn match
  "Match `pattern` against `fact`. Returns a bindings map on success,
   nil on failure. The 3-arg form extends an existing bindings map —
   it's how multi-clause queries thread bindings between clauses."
  ([_pattern _fact]
   (throw (ex-info "not implemented" {:milestone 2})))
  ([_bindings _pattern _fact]
   (throw (ex-info "not implemented" {:milestone 2}))))

(defn substitute
  "Walk `pattern` and replace any lvar that appears in `bindings` with
   its bound value. Lvars not in bindings are left as-is."
  [_bindings _pattern]
  (throw (ex-info "not implemented" {:milestone 2})))
