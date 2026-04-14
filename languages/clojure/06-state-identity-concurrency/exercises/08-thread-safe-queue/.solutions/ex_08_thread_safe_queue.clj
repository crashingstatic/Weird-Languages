(ns clojure-course.state-identity-concurrency.ex-08-thread-safe-queue)

(defn make-queue []
  (atom clojure.lang.PersistentQueue/EMPTY))

(defn enqueue [q item]
  (swap! q conj item))

(defn dequeue [q]
  (loop []
    (let [current @q]
      (if (empty? current)
        nil
        (if (compare-and-set! q current (pop current))
          (peek current)
          (recur))))))

(defn queue-size [q]
  (count @q))

(defn queue-empty? [q]
  (empty? @q))
