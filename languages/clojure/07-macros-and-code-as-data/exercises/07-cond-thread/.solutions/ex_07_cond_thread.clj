(ns clojure-course.macros-and-code-as-data.ex-07-cond-thread)

(defmacro my-cond-> [val & clauses]
  (assert (even? (count clauses)) "cond-> requires an even number of clauses")
  (let [g (gensym "val__")
        steps (for [[test form] (partition 2 clauses)]
                `(if ~test (-> ~g ~form) ~g))]
    `(let [~g ~val
           ~@(interleave (repeat g) steps)]
       ~g)))
