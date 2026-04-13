(ns clojure-course.polymorphism-and-protocols.ex-06-when-to-use-which)

;; The multimethod version below dispatches on the :type field of a map.
;; Your task: convert this to use a protocol instead.
;;
;; 1. Define a Renderable protocol with a `render` method
;; 2. Create Text, Image, and Link records
;; 3. Each record should implement `render` with the same behavior
;;    as the multimethod version
;;
;; The tests call `render` and constructors — make sure your public API matches.

;; -- Reference: multimethod version (delete or comment out when done) --
;; (defmulti render :type)
;; (defmethod render :text [{:keys [content]}]
;;   (str "<p>" content "</p>"))
;; (defmethod render :image [{:keys [url alt]}]
;;   (str "<img src=\"" url "\" alt=\"" alt "\">"))
;; (defmethod render :link [{:keys [url label]}]
;;   (str "<a href=\"" url "\">" label "</a>"))

;; TODO: define the Renderable protocol and records below
(defprotocol Renderable
  (render [this]))

(defrecord Text [content]
  Renderable
  (render [this] nil))

(defrecord Image [url alt]
  Renderable
  (render [this] nil))

(defrecord Link [url label]
  Renderable
  (render [this] nil))
