(ns clojure-course.polymorphism-and-protocols.ex-07-expr-eval-multi)

;; Implement an expression evaluator using multimethods.
;;
;; Expressions are maps with an :op key:
;;   {:op :number :value 5}
;;   {:op :sum :left expr :right expr}
;;   {:op :product :left expr :right expr}
;;
;; Implement:
;; - `eval-expr` multimethod dispatching on :op
;; - `stringify` multimethod that returns a string representation

(defn eval-expr [expr]
  (throw (ex-info "not implemented" {})))

(defn stringify [expr]
  (throw (ex-info "not implemented" {})))
