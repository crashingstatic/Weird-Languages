(ns clojure-course.building-a-language.ex-04-extend-env-and-define)

;; === PROVIDED ===

(defn lookup-var [env var-name]
  (cond
    (empty? env)
    (throw (ex-info (str "Unbound variable: " var-name) {:var var-name}))
    (contains? @(first env) var-name)
    (get @(first env) var-name)
    :else
    (recur (rest env) var-name)))

;; TASK: Implement extend-env and define-var!.

(defn extend-env
  "Create a new frame binding names to values, and cons it onto env.
   A frame is an atom containing a map."
  [env names values]
  (throw (ex-info "not implemented" {})))

(defn define-var!
  "Add or update a binding in the first (current) frame of env.
   Returns the value."
  [env var-name value]
  (throw (ex-info "not implemented" {})))
