(ns clojure-course.macros-and-code-as-data.ex-07-cond-thread)

;; TASK: Implement `my-cond->`.
;; (my-cond-> val
;;   test1 form1
;;   test2 form2)
;; Works like clojure.core/cond->: threads val through each form
;; whose corresponding test is truthy.

(defmacro my-cond-> [val & clauses]
  nil)
