(ns clojure-course.data-and-abstraction.ex-10-inventory-system)

;; Implement a store inventory system.
;; You choose the data shape — tests only use the public interface.

(def empty-inventory {})

(defn add-stock
  [inv item price qty supplier]
  inv) ;; TODO: implement

(defn sell-item
  [inv item]
  inv) ;; TODO: implement

(defn low-stock-items
  [inv threshold]
  #{}) ;; TODO: implement

(defn total-value
  [inv]
  0) ;; TODO: implement
