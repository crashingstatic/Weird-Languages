(ns clojure-course.macros-and-code-as-data.ex-06-with-timing)

;; TASK: Implement `with-timing`.
;; It should evaluate the body forms and return a map with:
;;   :result    — the value of the body
;;   :elapsed-ms — how many milliseconds the body took
;; Hint: use System/nanoTime, gensyms (auto-gensym #), and `do`.

(defmacro with-timing [& body]
  nil)
