(ns datalog.syntax
  "Milestone 8 — Surface syntax macros.

   Two thin macros that let the student write rules and queries
   without quoting everything. The lesson from aspect 07 governs
   the design: the macros do *no* work — they just rearrange syntax
   so the engine in `datalog.rules` can do its job. If you find
   logic creeping into a macro, that's the signal it should be a
   function instead."
  (:require [datalog.rules :as r]))

(def ^:dynamic *rules*
  "A registry of currently-defined rules. `defrule` conjoins to it,
   `?-` reads from it. Held in an atom so additive rule definitions
   compose."
  (atom []))

(defn reset-rules!
  "Clear the rule registry. Useful at the top of a query script or
   between tests."
  []
  (reset! *rules* []))

(defmacro defrule
  "Add a rule to the global registry. Quotes the head and body so the
   student doesn't have to. Example:

       (defrule (ancestor ?a ?d) [?a :parent ?d])
       (defrule (ancestor ?a ?d)
         [?a :parent ?x]
         (ancestor ?x ?d))"
  [head & body]
  `(swap! *rules* conj '[~head ~@body]))

(defmacro ?-
  "Run a query without quoting. Example:

       (?- facts [?a] (ancestor ?a :dave))

   Expands to a call into the engine using the rules registered with
   `defrule`."
  [facts find-vec & where-clauses]
  `(r/query ~facts @*rules*
            {:find  '~find-vec
             :where '~(vec where-clauses)}))
