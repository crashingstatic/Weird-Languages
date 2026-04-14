(ns clojure-course.state-identity-concurrency.ex-09-agent-counter-ring)

(defn make-ring
  "Creates a ring of n agents. Each agent's state is its index.
   counter is an atom that gets incremented each time a token arrives."
  [n counter]
  (let [agents (vec (for [i (range n)] (agent i)))]
    {:agents agents :counter counter :size n}))

(defn- pass-token [ring-agents counter size done hops-remaining current-idx agent-state]
  (swap! counter inc)
  (if (<= (dec hops-remaining) 0)
    (deliver done true)
    (let [next-idx (mod (inc current-idx) size)]
      (send (nth ring-agents next-idx)
            (partial pass-token ring-agents counter size done (dec hops-remaining) next-idx))))
  agent-state)

(defn start-token
  "Sends a token into the ring that will be passed `hops` times.
   Returns after all hops complete."
  [ring hops]
  (if (zero? hops)
    nil
    (let [{:keys [agents counter size]} ring
          done (promise)]
      (send (first agents)
            (partial pass-token agents counter size done hops 0))
      @done)))
