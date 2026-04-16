(ns clojure-course.building-a-language.ex-03-env-lookup)

(def ^:private ___ nil)

;; An environment is a list of frames.
;; Each frame is an atom containing a map of {name-string -> value}.
;;
;; Example:
;;   (list (atom {"x" 10, "y" 20})
;;         (atom {"z" 30}))
;;
;; The first frame is the innermost (most recent) scope.

;; TASK: Fill in lookup-var. Walk the frame chain from innermost to
;; outermost. Return the value if found; throw if not.

(defn lookup-var [env var-name]
  (cond
    (empty? env)
    (throw (ex-info (str "Unbound variable: " var-name) {:var var-name}))

    (contains? @(first env) var-name)
    (get @(first env) var-name)

    :else
    (recur (rest env) var-name)))
