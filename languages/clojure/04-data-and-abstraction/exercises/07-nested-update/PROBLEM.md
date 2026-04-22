# Exercise 07: Nested Update

## What you're learning

`update-in` and `get-in` let you reach into deeply nested immutable maps and
transform values without manually threading the intermediate maps. This is the
standard tool for working with nested data in Clojure.

## The problem

You're given a data structure representing teams of players with stats:

```clojure
{:red  {:alice {:goals 3 :assists 5}
        :bob   {:goals 1 :assists 2}}
 :blue {:carol {:goals 7 :assists 1}}}
```

Implement:

- `get-stat`: given the data, a team, a player, and a stat keyword, return
  the stat value.
- `inc-stat`: given the data, a team, a player, and a stat keyword, return
  the data with that stat incremented by 1.
- `add-player`: given the data, a team, and a player keyword, return the data
  with a new player added to that team with `{:goals 0 :assists 0}`.

## The interface

```clojure
(defn get-stat [data team player stat])
(defn inc-stat [data team player stat])
(defn add-player [data team player])
```

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-07-nested-update-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/data_and_abstraction/ex_07_nested_update.clj`.
