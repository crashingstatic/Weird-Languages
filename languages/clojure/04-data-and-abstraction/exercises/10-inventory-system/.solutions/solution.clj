(ns clojure-course.data-and-abstraction.ex-10-inventory-system)

;; Representation: a map from item keyword to
;; {:price p :qty q :supplier s}.
;; The map is the inventory. Empty inventory is an empty map.
(def empty-inventory {})

(defn add-stock [inv item price qty supplier]
  (if (contains? inv item)
    (update-in inv [item :qty] + qty)
    (assoc inv item {:price price :qty qty :supplier supplier})))

(defn sell-item [inv item]
  (if (and (contains? inv item) (pos? (:qty (get inv item))))
    (update-in inv [item :qty] dec)
    inv))

(defn low-stock-items [inv threshold]
  (into #{}
        (comp (filter (fn [[_ info]] (<= (:qty info) threshold)))
              (map first))
        inv))

(defn total-value [inv]
  (reduce-kv (fn [sum _ {:keys [price qty]}]
               (+ sum (* price qty)))
             0
             inv))
