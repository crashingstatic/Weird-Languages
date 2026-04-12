(ns clojure-course.higher-order-seqs.ex-09-compose-many)

;; reduce left-to-right over the function list, building up right-to-left
;; composition: each new function becomes the innermost layer.
;; identity is the neutral element for composition.
(defn compose-all
  [fs]
  (reduce (fn [composed f]
            (fn [x] (composed (f x))))
          identity
          fs))
