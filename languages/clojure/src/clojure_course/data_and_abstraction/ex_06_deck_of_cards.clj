(ns clojure-course.data-and-abstraction.ex-06-deck-of-cards)

(defn make-deck
  "Return a vector of 52 cards, each {:suit s :rank r}."
  []
  (throw (ex-info "not implemented" {})))

(defn shuffle-deck
  "Return deck in random order."
  [deck]
  (throw (ex-info "not implemented" {})))

(defn deal
  "Deal n cards from the top. Returns [hand remaining-deck]."
  [deck n]
  (throw (ex-info "not implemented" {})))

(defn cards-of-suit
  "Return only cards matching the given suit."
  [deck suit]
  (throw (ex-info "not implemented" {})))
