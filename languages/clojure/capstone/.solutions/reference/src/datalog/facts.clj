(ns datalog.facts
  "Milestone 1 — Fact storage.

   Facts are EAV triples represented as 3-element Clojure vectors:
   [entity attribute value]. The fact-base is a set, which gives us
   deduplication for free and aligns with the data-orientation lesson
   from aspect 04: facts are *just data*, no objects, no wrappers.")

(defn fact-base
  "Construct an empty fact-base, optionally seeded with an initial coll
   of facts."
  ([] #{})
  ([facts] (into #{} facts)))

(defn add-fact
  "Return a new fact-base with `fact` added. Adding a duplicate is a
   no-op because sets dedupe."
  [fb fact]
  (conj fb fact))

(defn add-facts
  "Return a new fact-base with all `facts` added."
  [fb facts]
  (into fb facts))

(defn remove-fact
  "Return a new fact-base with `fact` removed."
  [fb fact]
  (disj fb fact))

(defn all-facts
  "Return all facts in the fact-base as a sequence. Order is not
   guaranteed (sets are unordered)."
  [fb]
  (seq fb))
