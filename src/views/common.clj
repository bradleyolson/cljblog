(ns views.common
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use clojure.pprint)
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn index
  []
  (partials/layout
    [:h2 "Home"]
    [:section
     (partials/all-posts (blog/retrieve-with "limit" 10 "title"))]))

(defn about
  []
  (partials/layout
    [:h2 "About"]))

(defn fourohfour
  []
  (partials/layout
    [:h2 "fourohfour"]))
