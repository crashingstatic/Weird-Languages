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

;; === YOUR TASK ===

(defn extend-env
  "Create a new frame binding names to values, and cons it onto env."
  [env names values]
  (cons (atom (zipmap names values)) env))

(defn define-var!
  "Add or update a binding in the first (current) frame of env."
  [env var-name value]
  (swap! (first env) assoc var-name value)
  value)
