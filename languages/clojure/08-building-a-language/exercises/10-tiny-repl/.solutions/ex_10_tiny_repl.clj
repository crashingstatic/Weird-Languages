(ns clojure-course.building-a-language.ex-10-tiny-repl)

;; === PROVIDED: parser ===

(defn tokenize [s]
  (re-seq #"\"[^\"]*\"|\(|\)|'|[^\s()\"]+" s))

(declare parse-expr)

(defn parse-atom [token]
  (cond
    (re-matches #"-?\d+" token) (Long/parseLong token)
    (= token "true") true
    (= token "false") false
    (.startsWith ^String token "\"") (subs token 1 (dec (count token)))
    :else (symbol token)))

(defn- parse-list [tokens]
  (loop [tokens tokens, items []]
    (when (empty? tokens)
      (throw (ex-info "Unexpected end of input" {})))
    (if (= (first tokens) ")")
      [(apply list items) (rest tokens)]
      (let [[item remaining] (parse-expr tokens)]
        (recur remaining (conj items item))))))

(defn parse-expr [tokens]
  (let [token (first tokens)
        remaining (rest tokens)]
    (case token
      "(" (parse-list remaining)
      "'" (let [[quoted rest-tokens] (parse-expr remaining)]
            [(list 'quote quoted) rest-tokens])
      [(parse-atom token) remaining])))

(defn parse [s]
  (first (parse-expr (tokenize s))))

;; === PROVIDED: evaluator ===

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
               "cons" {:type :primitive :fn cons}
               "car" {:type :primitive :fn first}
               "cdr" {:type :primitive :fn rest}
               "null?" {:type :primitive :fn empty?}})))

(declare tiny-eval)

(defn- self-evaluating? [exp]
  (or (number? exp) (string? exp) (boolean? exp) (nil? exp)))

(defn- eval-sequence [exps env]
  (cond
    (empty? exps) nil
    (empty? (rest exps)) (tiny-eval (first exps) env)
    :else (do (tiny-eval (first exps) env)
              (recur (rest exps) env))))

(defn tiny-apply [proc args]
  (case (:type proc)
    :primitive (apply (:fn proc) args)
    :compound (eval-sequence (:body proc) (extend-env (:env proc) (:params proc) args))
    (throw (ex-info (str "Not a procedure: " (pr-str proc)) {:proc proc}))))

(defn- desugar-let [exp]
  (let [bindings (second exp)
        body (drop 2 exp)
        names (map first bindings)
        vals (map second bindings)]
    (cons (list* 'lambda (apply list names) body) vals)))

(defn- desugar-let* [exp]
  (let [bindings (second exp)
        body (drop 2 exp)]
    (cond
      (empty? bindings) (cons 'begin body)
      (empty? (rest bindings)) (list* 'let (list (first bindings)) body)
      :else (list 'let (list (first bindings))
                  (list* 'let* (apply list (rest bindings)) body)))))

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
                                {:type :compound :params params :body body-exps :env env}))))
        (= tag 'lambda) {:type :compound
                         :params (mapv name (second exp))
                         :body (vec (drop 2 exp))
                         :env env}
        (= tag 'begin) (eval-sequence (rest exp) env)
        (= tag 'let) (tiny-eval (desugar-let exp) env)
        (= tag 'let*) (tiny-eval (desugar-let* exp) env)
        :else
        (let [proc (tiny-eval tag env)
              args (mapv #(tiny-eval % env) (rest exp))]
          (tiny-apply proc args))))
    :else (throw (ex-info (str "Unknown expression: " (pr-str exp)) {:exp exp}))))

;; === YOUR TASK ===

(defn run-program
  "Evaluate a sequence of program lines (strings) in order.
   All lines share the same environment, so defines persist.
   Returns a vector of results (one per line)."
  [lines]
  (let [env (make-global-env)]
    (mapv (fn [line] (tiny-eval (parse line) env)) lines)))
