(ns clojure-course.macros-and-code-as-data.ex-01-quote-and-unquote)

(defn quoted-symbol []
  'hello)

(defn make-inc-form [x]
  `(inc ~x))

(defn make-sum-form [args]
  `(+ ~@args))
