(ns clojure-course.macros-and-code-as-data.ex-03-when-positive)

(defmacro when-positive [n & body]
  `(when (pos? ~n) ~@body))
