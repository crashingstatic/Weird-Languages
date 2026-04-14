(ns clojure-course.state-identity-concurrency.ex-07-memoize-with-atom)

(defn memoize-with-atom [f]
  (let [cache (atom {})]
    (fn [& args]
      (if-let [cached (find @cache args)]
        (val cached)
        (let [result (apply f args)]
          (swap! cache assoc args result)
          result)))))
