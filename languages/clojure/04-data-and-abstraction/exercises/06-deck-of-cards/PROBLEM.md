# Exercise 06: Deck of Cards

## What you're learning

Modeling a domain — a standard 52-card deck — as plain Clojure data. Cards are
maps, the deck is a vector. Operations are just functions over collections.
No classes, no inheritance, no special framework.

## The problem

Implement:

- `make-deck`: returns a vector of all 52 cards. Each card is a map
  `{:suit s :rank r}` where suits are `:hearts`, `:diamonds`, `:clubs`,
  `:spades` and ranks are 1–13 (1 = Ace, 11 = Jack, 12 = Queen, 13 = King).
- `shuffle-deck`: returns the deck in a random order. (Use `shuffle`.)
- `deal`: given a deck and `n`, returns a vector of two elements:
  `[hand remaining-deck]` where `hand` is the first `n` cards and
  `remaining-deck` is the rest.
- `cards-of-suit`: given a deck and a suit keyword, returns only cards of
  that suit.

## The interface

```clojure
(defn make-deck [])
(defn shuffle-deck [deck])
(defn deal [deck n])
(defn cards-of-suit [deck suit])
```

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-06-deck-of-cards-test
```

**Starter file:** `src/clojure_course/data_and_abstraction/ex_06_deck_of_cards.clj`
