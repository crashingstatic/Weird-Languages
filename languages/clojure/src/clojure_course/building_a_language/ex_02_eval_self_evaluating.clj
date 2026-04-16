(ns clojure-course.building-a-language.ex-02-eval-self-evaluating)

(def ^:private ___ nil)

;; TASK: Fill in self-evaluating? and the return value in tiny-eval.
;; self-evaluating? should return true for numbers, strings, and booleans.
;; tiny-eval should return the expression itself if it's self-evaluating.

(defn self-evaluating? [exp]
  ___)

(defn tiny-eval [exp]
  (cond
    (self-evaluating? exp) ___
    :else (throw (ex-info (str "Cannot evaluate: " (pr-str exp)) {:exp exp}))))
