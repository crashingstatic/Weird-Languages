(ns clojure-course.macros-and-code-as-data.ex-08-log-context)

(def ^:dynamic *log-context* {})

(defn get-context []
  *log-context*)

(defmacro with-log-context [bindings & body]
  `(binding [*log-context* (merge *log-context* ~bindings)]
     ~@body))
