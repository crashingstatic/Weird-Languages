(ns clojure-course.macros-and-code-as-data.ex-02-unless-macro)

(defmacro unless [condition then-expr else-expr]
  `(if ~condition ~else-expr ~then-expr))
