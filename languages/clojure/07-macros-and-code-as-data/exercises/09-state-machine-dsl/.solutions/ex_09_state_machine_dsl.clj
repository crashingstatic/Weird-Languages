(ns clojure-course.macros-and-code-as-data.ex-09-state-machine-dsl)

(defmacro defstate-machine [name definition]
  `(defn ~name [initial-state# events#]
     (reduce
       (fn [state# event#]
         (let [transitions# (get ~definition state#)]
           (if-let [next-state# (get transitions# event#)]
             next-state#
             state#)))
       initial-state#
       events#)))
