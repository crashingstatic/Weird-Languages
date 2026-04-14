(ns clojure-course.state-identity-concurrency.ex-10-deadlock-and-fix)

;; A buggy transfer implementation using locks is shown below.
;; It can deadlock when two threads transfer in opposite directions.
;;
;; Your task: implement a correct `transfer` using refs and STM
;; that is immune to deadlock.
;;
;; ;; BUGGY version (for reference — DO NOT USE):
;; (defn buggy-transfer [from to amount]
;;   (locking from
;;     (locking to
;;       (when (>= @from amount)
;;         (swap! from - amount)
;;         (swap! to + amount)
;;         true))))

(defn make-account [initial-balance]
  (throw (ex-info "not implemented" {})))

(defn get-balance [account]
  (throw (ex-info "not implemented" {})))

(defn transfer [from to amount]
  (throw (ex-info "not implemented" {})))
