(ns datalog.syntax
  "Milestone 8 — Surface syntax macros.

   Two thin macros that let the user write rules and queries without
   quoting everything. The lesson from aspect 07 governs the design:
   the macros do *no* work beyond rearranging syntax. All the logic
   lives in `datalog.rules`. If you find yourself doing real work in
   a macro body, it should be a function instead.

   Implementation note: keep `*rules*` as the registry your `defrule`
   updates. `?-` reads from it when expanding."
  (:require [datalog.rules :as r]))

(def ^:dynamic *rules*
  "A registry of currently-defined rules. `defrule` conjoins to it,
   `?-` reads from it."
  (atom []))

(defn reset-rules!
  "Clear the rule registry."
  []
  (reset! *rules* []))

(defmacro defrule
  "Add a rule to the global registry. Quotes the head and body so the
   caller doesn't have to. Implement so:

       (defrule (ancestor ?a ?d) [?a :parent ?d])

   adds the rule `[(ancestor ?a ?d) [?a :parent ?d]]` to *rules*.

   The macro must expand to compilable code; have it expand to a
   runtime throw until you implement it."
  [_head & _body]
  `(throw (ex-info "not implemented" {:milestone 8})))

(defmacro ?-
  "Run a query without quoting. Implement so:

       (?- facts [?a] (ancestor ?a :dave))

   expands to a call into `datalog.rules/query` using the rules
   registered with `defrule`.

   The macro must expand to compilable code; have it expand to a
   runtime throw until you implement it."
  [_facts _find-vec & _where-clauses]
  `(throw (ex-info "not implemented" {:milestone 8})))
