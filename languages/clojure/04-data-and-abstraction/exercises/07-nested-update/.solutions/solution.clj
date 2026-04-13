(ns clojure-course.data-and-abstraction.ex-07-nested-update)

;; get-in and update-in make nested access feel like flat access.
;; The path vector [team player stat] reads like a directory path.
(defn get-stat [data team player stat]
  (get-in data [team player stat]))

(defn inc-stat [data team player stat]
  (update-in data [team player stat] inc))

(defn add-player [data team player]
  (assoc-in data [team player] {:goals 0 :assists 0}))
