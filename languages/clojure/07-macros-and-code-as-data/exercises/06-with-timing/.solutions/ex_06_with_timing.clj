(ns clojure-course.macros-and-code-as-data.ex-06-with-timing)

(defmacro with-timing [& body]
  `(let [start# (System/nanoTime)
         result# (do ~@body)
         end# (System/nanoTime)]
     {:result result# :elapsed-ms (/ (- end# start#) 1e6)}))
