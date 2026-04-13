(ns clojure-course.data-and-abstraction.ex-03-destructure-args)

;; TASK: Replace each ___ argument with a destructuring form.
;;
;; For full-name: use associative destructuring {:keys [first last]}
;; so that `first` and `last` are bound from the person map.
;;
;; For midpoint: use sequential destructuring [x1 y1] and [x2 y2]
;; so that coordinates are bound from each point vector.
;;
;; The function bodies reference `first`, `last`, `x1`, `y1`, `x2`, `y2`
;; — they won't resolve until you supply the correct destructuring.

(def ^:private ___ nil)

(defn full-name [person]
  ;; Replace this body: use destructuring in the arg list instead of get calls
  ___)

(defn midpoint [p1 p2]
  ;; Replace this body: use destructuring in the arg list
  ___)
