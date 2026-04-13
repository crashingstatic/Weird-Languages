(ns clojure-course.recursion-and-recur.ex-06-my-nth)

;; Walk the sequence, decrementing the index each step. When i reaches 0,
;; the current head is the answer. If we run out of elements first, or if
;; n was negative, throw.
(defn my-nth [xs n]
  (when (neg? n)
    (throw (IndexOutOfBoundsException. (str "Index: " n))))
  (loop [remaining xs
         i         n]
    (cond
      (empty? remaining)
      (throw (IndexOutOfBoundsException. (str "Index: " n)))

      (zero? i)
      (first remaining)

      :else
      (recur (rest remaining) (dec i)))))
