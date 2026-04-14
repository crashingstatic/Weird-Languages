(ns clojure-course.state-identity-concurrency.ex-08-thread-safe-queue-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-course.state-identity-concurrency.ex-08-thread-safe-queue :as sut]))

(deftest basic-operations-test
  (testing "new queue is empty"
    (let [q (sut/make-queue)]
      (is (true? (sut/queue-empty? q)))
      (is (= 0 (sut/queue-size q)))))
  (testing "enqueue and dequeue FIFO order"
    (let [q (sut/make-queue)]
      (sut/enqueue q :a)
      (sut/enqueue q :b)
      (sut/enqueue q :c)
      (is (= 3 (sut/queue-size q)))
      (is (= :a (sut/dequeue q)))
      (is (= :b (sut/dequeue q)))
      (is (= :c (sut/dequeue q)))
      (is (true? (sut/queue-empty? q)))))
  (testing "dequeue from empty returns nil"
    (is (nil? (sut/dequeue (sut/make-queue))))))

(deftest concurrent-producers-test
  (testing "concurrent enqueue preserves all items"
    (let [q (sut/make-queue)
          n 1000
          futures (doall (for [i (range n)]
                           (future (sut/enqueue q i))))]
      (doseq [f futures] @f)
      (is (= n (sut/queue-size q))))))

(deftest concurrent-consumers-test
  (testing "concurrent dequeue — no item lost or duplicated"
    (let [q (sut/make-queue)
          n 500]
      (dotimes [i n] (sut/enqueue q i))
      (let [results (atom [])
            futures (doall (for [_ (range n)]
                             (future
                               (when-let [item (sut/dequeue q)]
                                 (swap! results conj item)))))]
        (doseq [f futures] @f)
        (is (true? (sut/queue-empty? q)))
        (is (= n (count @results)))
        (is (= (set (range n)) (set @results)))))))
