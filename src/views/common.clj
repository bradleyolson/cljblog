(ns views.common
  (:use [views.partial :as partials])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn index
  []
  (partials/layout
    [:h2 "hi"]
    ))

(defn fourohfour
  []
  (partials/layout
    [:h2 "fourohfour"]
    ))
