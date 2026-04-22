# Exercise 08: Thread-Safe Queue

## What you're learning

A FIFO queue can be implemented with an atom holding a persistent queue.
The tricky part is `dequeue` — you need to atomically read *and* remove the
front element, which requires a CAS loop (not just `swap!`).

## The problem

Implement:

- `make-queue` — creates an atom holding an empty queue
- `enqueue` — adds an item to the back
- `dequeue` — removes and returns the front item (nil if empty)
- `queue-size` — number of items
- `queue-empty?` — true if no items

Must be safe under concurrent producers and consumers.

## Hints

- `clojure.lang.PersistentQueue/EMPTY` is Clojure's persistent FIFO queue.
- `conj` adds to the back, `peek` reads the front, `pop` removes the front.
- For dequeue, use `compare-and-set!` to atomically read-and-remove.

## Run the tests

```bash
clojure -M:test -n clojure-course.state-identity-concurrency.ex-08-thread-safe-queue-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/state_identity_concurrency/ex_08_thread_safe_queue.clj`.
