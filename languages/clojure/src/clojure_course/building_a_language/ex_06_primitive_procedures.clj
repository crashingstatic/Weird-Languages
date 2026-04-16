(ns clojure-course.building-a-language.ex-06-primitive-procedures)

;; === PROVIDED: environment ===

(defn lookup-var [env var-name]
  (cond
    (empty? env)
    (throw (ex-info (str "Unbound variable: " var-name) {:var var-name}))
    (contains? @(first env) var-name)
    (get @(first env) var-name)
    :else
    (recur (rest env) var-name)))

(defn extend-env [env names values]
  (cons (atom (zipmap names values)) env))

(defn define-var! [env var-name value]
  (swap! (first env) assoc var-name value)
  value)

;; === PROVIDED: eval ===

(declare tiny-eval)

(defn- self-evaluating? [exp]
  (or (number? exp) (string? exp) (boolean? exp) (nil? exp)))

;; TASK: Implement make-global-env and apply-primitive.
;; make-global-env should return an environment with primitives bound.
;; Each primitive is a map {:type :primitive :fn <clojure-fn>}.
;; Bind at least: +, -, *, /, =, <, >, not.

(defn make-global-env []
  (throw (ex-info "not implemented" {})))

(defn apply-primitive [proc args]
  (throw (ex-info "not implemented" {})))

;; === PROVIDED: eval dispatch ===

(defn tiny-eval [exp env]
  (cond
    (self-evaluating? exp) exp
    (symbol? exp) (lookup-var env (name exp))
    (seq? exp)
    (let [tag (first exp)]
      (cond
        (= tag 'quote) (second exp)
        (= tag 'if) (if (tiny-eval (nth exp 1) env)
                      (tiny-eval (nth exp 2) env)
                      (when (seq (drop 3 exp))
                        (tiny-eval (nth exp 3) env)))
        (= tag 'define) (define-var! env (name (second exp)) (tiny-eval (nth exp 2) env))
        :else
        (let [proc (tiny-eval (first exp) env)
              args (mapv #(tiny-eval % env) (rest exp))]
          (if (= (:type proc) :primitive)
            (apply-primitive proc args)
            (throw (ex-info "Not a procedure" {:proc proc}))))))
    :else (throw (ex-info (str "Unknown expression: " (pr-str exp)) {:exp exp}))))
