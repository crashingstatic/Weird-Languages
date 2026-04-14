(ns clojure-course.macros-and-code-as-data.ex-08-log-context)

;; TASK:
;; 1. Define a dynamic var `*log-context*` (initially {}).
;; 2. Write `get-context` — returns the current `*log-context*`.
;; 3. Write `with-log-context` macro — merges bindings into
;;    `*log-context*` for the duration of body.

(defn get-context []
  (throw (ex-info "not implemented" {})))

(defmacro with-log-context [bindings & body]
  `(do ~@body))
