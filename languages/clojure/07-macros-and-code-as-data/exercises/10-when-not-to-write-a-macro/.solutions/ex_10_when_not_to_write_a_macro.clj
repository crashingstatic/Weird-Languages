(ns clojure-course.macros-and-code-as-data.ex-10-when-not-to-write-a-macro)

;; --- PROVIDED: the over-engineered macro ---
(defmacro apply-transforms-macro [val transforms]
  `(-> ~val ~@transforms))

;; --- The simplest function version ---
(defn apply-transforms [val transforms]
  (reduce (fn [v f] (f v)) val transforms))

;; Why the macro is wrong:
;; The macro splices `transforms` at compile time, so it only works with
;; literal lists of forms — it cannot accept a runtime-computed sequence of
;; functions. A function handles both cases with `reduce`.
