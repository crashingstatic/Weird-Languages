(ns clojure-course.building-a-language.ex-07-lambda-and-application)

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

(defn make-global-env []
  (list (atom {"+" {:type :primitive :fn +}
               "-" {:type :primitive :fn -}
               "*" {:type :primitive :fn *}
               "/" {:type :primitive :fn /}
               "=" {:type :primitive :fn =}
               "<" {:type :primitive :fn <}
               ">" {:type :primitive :fn >}
               "not" {:type :primitive :fn not}
               "list" {:type :primitive :fn list}
               "null?" {:type :primitive :fn empty?}
               "car" {:type :primitive :fn first}
               "cdr" {:type :primitive :fn rest}})))

;; === PROVIDED: eval framework ===

(declare tiny-eval)

(defn- self-evaluating? [exp]
  (or (number? exp) (string? exp) (boolean? exp) (nil? exp)))

(defn- eval-sequence [exps env]
  (cond
    (empty? exps) nil
    (empty? (rest exps)) (tiny-eval (first exps) env)
    :else (do (tiny-eval (first exps) env)
              (recur (rest exps) env))))

;; === YOUR TASK ===

(defn make-procedure
  "Create a compound procedure value that captures the current environment."
  [params body env]
  {:type :compound
   :params params
   :body body
   :env env})

(defn tiny-apply
  "Apply a procedure (primitive or compound) to a list of arguments."
  [proc args]
  (case (:type proc)
    :primitive (apply (:fn proc) args)
    :compound (let [{:keys [params body env]} proc]
                (eval-sequence body (extend-env env params args)))
    (throw (ex-info (str "Not a procedure: " (pr-str proc)) {:proc proc}))))

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
        (= tag 'define) (let [target (second exp)]
                          (if (symbol? target)
                            (define-var! env (name target) (tiny-eval (nth exp 2) env))
                            (let [fname (name (first target))
                                  params (mapv name (rest target))
                                  body-exps (vec (drop 2 exp))]
                              (define-var! env fname
                                (make-procedure params body-exps env)))))
        (= tag 'begin) (eval-sequence (rest exp) env)
        (= tag 'lambda) (make-procedure
                          (mapv name (second exp))
                          (vec (drop 2 exp))
                          env)
        :else
        (let [proc (tiny-eval tag env)
              args (mapv #(tiny-eval % env) (rest exp))]
          (tiny-apply proc args))))
    :else (throw (ex-info (str "Unknown expression: " (pr-str exp)) {:exp exp}))))
