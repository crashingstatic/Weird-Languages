(ns clojure-course.polymorphism-and-protocols.ex-09-tagged-arithmetic)

;; SICP 2.4.2 — Tagged data system.
;;
;; Implement:
;; - `attach-tag` — wraps a datum with a type tag
;; - `type-tag`   — retrieves the tag
;; - `contents`   — retrieves the datum
;; - `add` multimethod — dispatches on the types of both arguments

(defn attach-tag [tag datum]
  (throw (ex-info "not implemented" {})))

(defn type-tag [tagged]
  (throw (ex-info "not implemented" {})))

(defn contents [tagged]
  (throw (ex-info "not implemented" {})))

(defn add [a b]
  (throw (ex-info "not implemented" {})))
