(ns clojure-course.macros-and-code-as-data.ex-01-quote-and-unquote)

;; TASK: Fill in each function body using quote, syntax-quote,
;; unquote (~), and unquote-splicing (~@).

(def ^:private ___ nil)

;; Return the symbol 'hello (not the string, the symbol)
(defn quoted-symbol []
  ___)

;; Given x, return a list like (inc <x>) as DATA, not evaluated
(defn make-inc-form [x]
  ___)

;; Given a vector of args, return (+ arg1 arg2 ...) as data
(defn make-sum-form [args]
  ___)
