(ns datalog.facts
  "Milestone 1 — Fact storage.

   Facts are EAV triples: 3-element vectors of [entity attribute value].
   Implement the fact-base as a Clojure set so duplicates dedupe for
   free.")

(defn fact-base
  "Construct an empty fact-base, optionally seeded with an initial coll
   of facts."
  ([] (throw (ex-info "not implemented" {:milestone 1})))
  ([_facts] (throw (ex-info "not implemented" {:milestone 1}))))

(defn add-fact
  "Return a new fact-base with `fact` added."
  [_fb _fact]
  (throw (ex-info "not implemented" {:milestone 1})))

(defn add-facts
  "Return a new fact-base with all `facts` added."
  [_fb _facts]
  (throw (ex-info "not implemented" {:milestone 1})))

(defn remove-fact
  "Return a new fact-base with `fact` removed."
  [_fb _fact]
  (throw (ex-info "not implemented" {:milestone 1})))

(defn all-facts
  "Return all facts in the fact-base as a sequence."
  [_fb]
  (throw (ex-info "not implemented" {:milestone 1})))
