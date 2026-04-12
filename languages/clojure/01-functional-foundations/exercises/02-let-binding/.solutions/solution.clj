(ns clojure-course.functional-foundations.ex-02-let-binding)

(defn circle-area [r]
  (let [pi 3.14159]
    (* pi r r)))

;; The let binding for base-area references pi — bindings evaluate top to bottom.
(defn cylinder-volume [r h]
  (let [pi        3.14159
        base-area (* pi r r)]
    (* base-area h)))
