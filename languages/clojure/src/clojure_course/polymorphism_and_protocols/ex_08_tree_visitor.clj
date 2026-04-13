(ns clojure-course.polymorphism-and-protocols.ex-08-tree-visitor)

;; Implement a polymorphic expression tree with a visitor that computes
;; both the result and a log of every operation performed.
;;
;; Expression nodes:
;;   (->Num 5)          — a literal number
;;   (->Add left right) — addition of two sub-expressions
;;   (->Mul left right) — multiplication of two sub-expressions
;;
;; Implement:
;; - Expr protocol with `evaluate` and `visit`
;; - `evaluate` returns the numeric result
;; - `visit` returns {:result n :ops [list-of-strings]}
;;   where ops logs each operation as "add" or "mul" in evaluation order

(defprotocol Expr
  (evaluate [this])
  (visit [this]))

(defrecord Num [value]
  Expr
  (evaluate [this] (throw (ex-info "not implemented" {})))
  (visit [this] (throw (ex-info "not implemented" {}))))

(defrecord Add [left right]
  Expr
  (evaluate [this] (throw (ex-info "not implemented" {})))
  (visit [this] (throw (ex-info "not implemented" {}))))

(defrecord Mul [left right]
  Expr
  (evaluate [this] (throw (ex-info "not implemented" {})))
  (visit [this] (throw (ex-info "not implemented" {}))))
