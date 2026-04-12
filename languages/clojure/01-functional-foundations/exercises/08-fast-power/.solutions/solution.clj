(ns clojure-course.functional-foundations.ex-08-fast-power)

;; Square-and-halve: O(log n) multiplications instead of O(n).
;; Each even step squares and halves; each odd step peels off one factor of b.
(defn fast-power [b n]
  (cond
    (zero? n) 1
    (even? n) (let [half (fast-power b (quot n 2))]
               (*' half half))
    :else     (*' b (fast-power b (dec n)))))
