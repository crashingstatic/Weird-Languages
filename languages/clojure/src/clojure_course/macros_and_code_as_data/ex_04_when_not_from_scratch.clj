(ns clojure-course.macros-and-code-as-data.ex-04-when-not-from-scratch)

;; TASK: Implement `my-when-not` from scratch.
;; (my-when-not condition body1 body2 ...) evaluates the body forms
;; only when `condition` is false/nil. Returns nil otherwise.

(defmacro my-when-not [condition & body]
  nil)
