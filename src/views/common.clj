(ns views.common
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn index
  []
  (partials/layout
    [:h2 "Home"]
    (partials/posts)))

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
