(ns clojure-course.higher-order-seqs.ex-10-running-stats)

;; reductions produces the intermediate accumulator values — the "scan"
;; pattern. rest drops the seed value so we get one output per input.
(defn running-stats
  [numbers]
  (rest
    (reductions
      (fn [{:keys [count sum]} n]
        (let [new-count (inc count)
              new-sum   (+ sum n)]
          {:count new-count
           :sum   new-sum
           :mean  (double (/ new-sum new-count))}))
      {:count 0 :sum 0 :mean 0.0}
      numbers)))
