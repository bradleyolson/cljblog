(ns views.common
  (:use [views.partial :as partials])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn index
  []
  (partials/layout
    [:h2 "Home"]
    ))

(defn about
  []
  (partials/layout
    [:h2 "About"]
    ))

(defn fourohfour
  []
  (partials/layout
    [:h2 "fourohfour"]
    ))
