(ns smoke.hello-test
  (:require [clojure.test :refer [deftest is]]))

(deftest hello-test
  (is (= 4 (+ 2 2))))
