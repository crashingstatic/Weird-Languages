(ns clojure-course.macros-and-code-as-data.ex-05-infix-macro)

(defmacro infix [left op right]
  (list op left right))
