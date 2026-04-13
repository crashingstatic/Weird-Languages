(ns clojure-course.polymorphism-and-protocols.ex-10-generic-arithmetic)

;; SICP 2.5.1 — Generic arithmetic package.
;;
;; Build a generic arithmetic system over three number types:
;;   :integer, :rational, :real
;;
;; Implement:
;; - Type constructors: make-integer, make-rational, make-real
;; - Type accessors: type-of, value-of
;; - Coercion: integers promote to rationals, rationals promote to reals
;; - Generic operations: add, mul, equ?, zero?
;;
;; The dispatch should handle mixed types by coercing to the "higher" type.

(defn make-integer [n]
  (throw (ex-info "not implemented" {})))

(defn make-rational [n d]
  (throw (ex-info "not implemented" {})))

(defn make-real [x]
  (throw (ex-info "not implemented" {})))

(defn type-of [x]
  (throw (ex-info "not implemented" {})))

(defn value-of [x]
  (throw (ex-info "not implemented" {})))

(defn add [a b]
  (throw (ex-info "not implemented" {})))

(defn mul [a b]
  (throw (ex-info "not implemented" {})))

(defn equ? [a b]
  false)

(defn zero? [a]
  false)
