# Exercise 10: Inventory System

## What you're learning

Open data-modeling: you choose the data shape, then implement operations over
it. The tests assert on operation results, not on the internal representation,
so you have freedom in how you structure the data.

## The problem

Design and implement a small store inventory system. The system tracks items,
each with a name, price (in cents), quantity in stock, and supplier.

Implement:

- `empty-inventory`: a constant representing an empty inventory.
- `add-stock`: given inventory, an item keyword, a price, a quantity, and a
  supplier string, return the inventory with that item added or its quantity
  increased if it already exists (price and supplier come from the first
  `add-stock` call for that item).
- `sell-item`: given inventory and an item keyword, decrement quantity by 1.
  If the item doesn't exist or is out of stock, return the inventory unchanged.
- `low-stock-items`: given inventory and a threshold `n`, return a set of item
  keywords whose quantity is <= `n`.
- `total-value`: given inventory, return the sum of `(price * quantity)` for
  all items.

## The interface

```clojure
empty-inventory                              ;; the empty inventory value
(defn add-stock [inv item price qty supplier])
(defn sell-item [inv item])
(defn low-stock-items [inv threshold])
(defn total-value [inv])
```

## Run the tests

```bash
clojure -M:test -n clojure-course.data-and-abstraction.ex-10-inventory-system-test
```

**Starter file:** `src/clojure_course/data_and_abstraction/ex_10_inventory_system.clj`
