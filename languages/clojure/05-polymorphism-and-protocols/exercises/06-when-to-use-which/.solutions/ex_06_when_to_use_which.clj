(ns clojure-course.polymorphism-and-protocols.ex-06-when-to-use-which)

(defprotocol Renderable
  (render [this]))

(defrecord Text [content]
  Renderable
  (render [this] (str "<p>" content "</p>")))

(defrecord Image [url alt]
  Renderable
  (render [this] (str "<img src=\"" url "\" alt=\"" alt "\">")))

(defrecord Link [url label]
  Renderable
  (render [this] (str "<a href=\"" url "\">" label "</a>")))

;; When would you choose the multimethod version?
;; Use multimethods when dispatch is on a value (like a :type keyword in a map)
;; rather than on the Java type — e.g., when data arrives as plain maps from
;; JSON and you don't control the types.
