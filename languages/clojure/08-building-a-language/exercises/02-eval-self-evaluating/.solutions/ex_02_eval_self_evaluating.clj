(ns clojure-course.building-a-language.ex-02-eval-self-evaluating)

(def ^:private ___ nil)

;; TASK: Fill in self-evaluating? and the return value in tiny-eval.

(defn self-evaluating?
  "Returns true if exp is a value that evaluates to itself:
   numbers, strings, and booleans."
  [exp]
  (or (number? exp) (string? exp) (boolean? exp)))

(defn tiny-eval
  "Evaluate a self-evaluating expression. Throws for anything else."
  [exp]
  (cond
    (self-evaluating? exp) exp
    :else (throw (ex-info (str "Cannot evaluate: " (pr-str exp)) {:exp exp}))))
