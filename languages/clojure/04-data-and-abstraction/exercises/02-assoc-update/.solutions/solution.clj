(ns clojure-course.data-and-abstraction.ex-02-assoc-update)

;; assoc and update both return new maps — the original is untouched.
;; This is the basic vocabulary for "modifying" immutable data.
(defn set-name [person new-name]
  (assoc person :name new-name))

(defn birthday [person]
  (update person :age inc))

(defn update-email [person new-email]
  (assoc person :email new-email))
