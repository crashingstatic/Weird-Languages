(ns clojure-course.macros-and-code-as-data.ex-10-when-not-to-write-a-macro)

;; --- PROVIDED: the over-engineered macro ---
(defmacro apply-transforms-macro [val transforms]
  `(-> ~val ~@transforms))

;; TASK: Write the simplest function that applies a sequence of
;; functions to a value. (Hint: reduce.)

(defn apply-transforms [val transforms]
  (throw (ex-info "not implemented" {})))
