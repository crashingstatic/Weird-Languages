(ns clojure-course.macros-and-code-as-data.ex-09-state-machine-dsl)

;; TASK: Implement `defstate-machine`.
;; (defstate-machine turnstile
;;   {:locked   {:coin :unlocked}
;;    :unlocked {:push :locked}})
;;
;; This should define a function `turnstile` that takes an initial state
;; and a sequence of events, and reduces over them using the transition map.

(defmacro defstate-machine [name definition]
  `(defn ~name [& ~'_] nil))
