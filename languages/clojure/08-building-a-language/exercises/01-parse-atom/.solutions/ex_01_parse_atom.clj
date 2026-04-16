(ns clojure-course.building-a-language.ex-01-parse-atom)

(def ^:private ___ nil)

;; === PROVIDED ===

(defn tokenize
  "Split a string into tokens: parens, string literals, and atoms."
  [s]
  (re-seq #"\"[^\"]*\"|\(|\)|'|[^\s()\"]+" s))

(declare parse-expr)

;; === YOUR TASK ===

(defn parse-atom
  "Convert a single token string into the correct Clojure value."
  [token]
  (cond
    (re-matches #"-?\d+" token)        (Long/parseLong token)
    (= token "true")                   true
    (= token "false")                  false
    (.startsWith ^String token "\"")   (subs token 1 (dec (count token)))
    :else                              (symbol token)))

;; === PROVIDED ===

(defn- parse-list [tokens]
  (loop [tokens tokens, items []]
    (when (empty? tokens)
      (throw (ex-info "Unexpected end of input — missing )" {})))
    (if (= (first tokens) ")")
      [(apply list items) (rest tokens)]
      (let [[item remaining] (parse-expr tokens)]
        (recur remaining (conj items item))))))

(defn parse-expr
  "Parse one expression from a token sequence. Returns [expr, remaining-tokens]."
  [tokens]
  (let [token (first tokens)
        remaining (rest tokens)]
    (case token
      "(" (parse-list remaining)
      "'" (let [[quoted rest-tokens] (parse-expr remaining)]
            [(list 'quote quoted) rest-tokens])
      [(parse-atom token) remaining])))

(defn parse
  "Parse a string into a Clojure data structure representing the program."
  [s]
  (first (parse-expr (tokenize s))))
