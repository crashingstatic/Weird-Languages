(ns clojure-course.data-and-abstraction.ex-03-destructure-args)

;; Associative destructuring: {:keys [first last]} pulls :first and
;; :last out of the map and binds them as locals.
(defn full-name [{:keys [first last]}]
  (str first " " last))

;; Sequential destructuring: [x1 y1] and [x2 y2] bind the elements
;; of each vector positionally.
(defn midpoint [[x1 y1] [x2 y2]]
  [(/ (+ x1 x2) 2.0)
   (/ (+ y1 y2) 2.0)])
