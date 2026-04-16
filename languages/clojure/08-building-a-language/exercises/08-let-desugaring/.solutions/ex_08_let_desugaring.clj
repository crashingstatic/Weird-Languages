(ns clojure-course.building-a-language.ex-08-let-desugaring)

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
               "not" {:type :primitive :fn not}})))

;; === PROVIDED: eval/apply ===

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

;; === YOUR TASK ===

(defn desugar-let
  "Transform (let ((x 1) (y 2)) body...) into ((lambda (x y) body...) 1 2).
   Returns the desugared expression — do not evaluate it."
  [exp]
  (let [bindings (second exp)
        body (drop 2 exp)
        names (map first bindings)
        vals (map second bindings)]
    (cons (list* 'lambda (apply list names) body) vals)))

(defn desugar-let*
  "Transform (let* ((x 1) (y expr)) body...) into nested lets.
   (let* ((x 1) (y 2)) body) => (let ((x 1)) (let* ((y 2)) body))
   (let* () body...) => (begin body...)
   Returns the desugared expression — do not evaluate it."
  [exp]
  (let [bindings (second exp)
        body (drop 2 exp)]
    (cond
      (empty? bindings)
      (cons 'begin body)

      (empty? (rest bindings))
      (list* 'let (list (first bindings)) body)

      :else
      (list 'let (list (first bindings))
            (list* 'let* (apply list (rest bindings)) body)))))

;; === PROVIDED: eval dispatch (with let/let* wired to your functions) ===

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
