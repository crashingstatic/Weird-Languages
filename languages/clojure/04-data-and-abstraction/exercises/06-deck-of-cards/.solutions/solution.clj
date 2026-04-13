(ns clojure-course.data-and-abstraction.ex-06-deck-of-cards)

;; A card is just a map. A deck is just a vector of maps.
;; No classes needed — the shape of the data is the API.
(defn make-deck []
  (vec (for [suit [:hearts :diamonds :clubs :spades]
             rank (range 1 14)]
         {:suit suit :rank rank})))

(defn shuffle-deck [deck]
  (shuffle deck))

(defn deal [deck n]
  [(vec (take n deck)) (vec (drop n deck))])

(defn cards-of-suit [deck suit]
  (filterv #(= suit (:suit %)) deck))
