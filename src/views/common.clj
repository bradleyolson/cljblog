(ns views.common
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn index
  []
  (partials/layout
    [:h2 "Home"]
    [:section
     (partials/all-posts (blog/retrieve-with "order by" "id desc" "limit" 1))]))

(defn page
  [page] 
    [:h2 "Home"]
    [:section
      (partials/all-posts 
        (blog/retrieve-with "order by" "id desc" "limit" 1 (blog/page-offset 1 posts-per-page)))])

(defn about
  []
  (partials/layout
    [:h2 "About"]))

(defn fourohfour
  []
  (partials/layout
    [:h2 "fourohfour"]))
