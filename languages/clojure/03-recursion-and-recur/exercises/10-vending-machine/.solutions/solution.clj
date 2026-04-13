(ns clojure-course.recursion-and-recur.ex-10-vending-machine)

;; Process one command, returning the updated state.
(defn- process-command [state cmd]
  (let [[action arg] cmd]
    (case action
      :insert (update state :balance + arg)

      :select (let [item-info (get-in state [:inventory arg])]
                (if (and item-info
                         (pos? (:qty item-info))
                         (>= (:balance state) (:price item-info)))
                  (-> state
                      (update :balance - (:price item-info))
                      (update-in [:inventory arg :qty] dec)
                      (update :dispensed conj arg))
                  state))

      :refund (assoc state :balance 0)

      ;; unknown command — pass through unchanged
      state)))

;; Fold the command sequence into the final state. This is the
;; loop/recur pattern applied to event processing — the same shape
;; as every state machine driver you'll write in Clojure.
(defn run-machine [state commands]
  (loop [s   state
         cs  commands]
    (if (empty? cs)
      s
      (recur (process-command s (first cs)) (rest cs)))))
