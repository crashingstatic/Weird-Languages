(ns clojure-course.macros-and-code-as-data.ex-04-when-not-from-scratch)

(defmacro my-when-not [condition & body]
  `(when (not ~condition) ~@body))
