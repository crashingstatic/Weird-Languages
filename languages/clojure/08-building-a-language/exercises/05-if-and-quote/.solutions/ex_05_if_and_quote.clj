(ns clojure-course.building-a-language.ex-05-if-and-quote)

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

;; === PROVIDED: minimal eval ===

(declare tiny-eval)

(defn- self-evaluating? [exp]
  (or (number? exp) (string? exp) (boolean? exp) (nil? exp)))

;; === YOUR TASK ===

(defn eval-quote
  "Return the quoted datum without evaluating it."
  [exp env]
  (second exp))

(defn eval-if
  "Evaluate the predicate. If truthy, evaluate the consequent;
   otherwise evaluate the alternative (if present)."
  [exp env]
  (if (tiny-eval (nth exp 1) env)
    (tiny-eval (nth exp 2) env)
    (when (seq (drop 3 exp))
      (tiny-eval (nth exp 3) env))))

;; === PROVIDED: eval dispatch ===

(defn tiny-eval [exp env]
  (cond
    (self-evaluating? exp) exp
    (symbol? exp) (lookup-var env (name exp))
    (seq? exp)
    (let [tag (first exp)]
      (cond
        (= tag 'quote) (eval-quote exp env)
        (= tag 'if) (eval-if exp env)
        (= tag 'define) (define-var! env (name (second exp)) (tiny-eval (nth exp 2) env))
        :else (throw (ex-info (str "Unknown form: " tag) {:exp exp}))))
    :else (throw (ex-info (str "Unknown expression: " (pr-str exp)) {:exp exp}))))
