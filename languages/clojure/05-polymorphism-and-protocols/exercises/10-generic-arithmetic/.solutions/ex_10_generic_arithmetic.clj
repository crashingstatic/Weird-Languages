(ns clojure-course.polymorphism-and-protocols.ex-10-generic-arithmetic)

(defn make-integer [n]
  {:type :integer :value n})

(defn make-rational [n d]
  (let [g (loop [a (Math/abs n) b (Math/abs d)]
            (if (clojure.core/zero? b) a (recur b (mod a b))))]
    (if (clojure.core/zero? g)
      {:type :rational :value [0 1]}
      {:type :rational :value [(/ n g) (/ d g)]})))

(defn make-real [x]
  {:type :real :value (double x)})

(defn type-of [x] (:type x))
(defn value-of [x] (:value x))

(def ^:private type-tower [:integer :rational :real])

(defn- type-rank [t]
  (.indexOf type-tower t))

(defn- raise-integer [{:keys [value]}]
  (make-rational value 1))

(defn- raise-rational [{:keys [value]}]
  (let [[n d] value]
    (make-real (/ (double n) (double d)))))

(defn- raise [x]
  (case (type-of x)
    :integer (raise-integer x)
    :rational (raise-rational x)
    x))

(defn- coerce-to-same [a b]
  (let [ra (type-rank (type-of a))
        rb (type-rank (type-of b))]
    (cond
      (= ra rb) [a b]
      (< ra rb) (coerce-to-same (raise a) b)
      :else     (coerce-to-same a (raise b)))))

(defmulti add-same (fn [a b] (type-of a)))

(defmethod add-same :integer [a b]
  (make-integer (+ (value-of a) (value-of b))))

(defmethod add-same :rational [a b]
  (let [[an ad] (value-of a)
        [bn bd] (value-of b)]
    (make-rational (+ (* an bd) (* bn ad)) (* ad bd))))

(defmethod add-same :real [a b]
  (make-real (+ (value-of a) (value-of b))))

(defmulti mul-same (fn [a b] (type-of a)))

(defmethod mul-same :integer [a b]
  (make-integer (* (value-of a) (value-of b))))

(defmethod mul-same :rational [a b]
  (let [[an ad] (value-of a)
        [bn bd] (value-of b)]
    (make-rational (* an bn) (* ad bd))))

(defmethod mul-same :real [a b]
  (make-real (* (value-of a) (value-of b))))

(defn add [a b]
  (let [[a' b'] (coerce-to-same a b)]
    (add-same a' b')))

(defn mul [a b]
  (let [[a' b'] (coerce-to-same a b)]
    (mul-same a' b')))

(defn equ? [a b]
  (let [[a' b'] (coerce-to-same a b)]
    (= (value-of a') (value-of b'))))

(defn zero? [a]
  (case (type-of a)
    :integer (clojure.core/zero? (value-of a))
    :rational (clojure.core/zero? (first (value-of a)))
    :real (clojure.core/zero? (value-of a))))
