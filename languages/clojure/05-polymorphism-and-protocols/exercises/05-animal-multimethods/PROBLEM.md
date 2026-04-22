# Exercise 05: Animal Multimethods with Hierarchies

## What you're learning

`derive` creates ad-hoc hierarchies that multimethods can dispatch on. A method
installed for `::mammal` handles any species that `isa?` mammal. This lets you
write general behavior at a parent level and specific behavior at a child level.

## The problem

Build an animal taxonomy:

```
::animal
  ::mammal
    ::cat
    ::dog
  ::bird
    ::sparrow
    ::parrot
```

Implement:

- `sound` multimethod (dispatches on `:species`) — each species has a unique sound
- `locomotion` multimethod — mammals walk, birds fly (dispatch at the parent level)
- `describe` function — returns `"<name> the <species> says <sound>"`

Animals are maps like `{:species ::cat :name "Whiskers"}`.

## Hints

- `(derive ::cat ::mammal)` establishes the relationship.
- `(defmulti sound :species)` dispatches on the `:species` value.
- `(name ::cat)` returns `"cat"`.

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-05-animal-multimethods-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_05_animal_multimethods.clj`.
