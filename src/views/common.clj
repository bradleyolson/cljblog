(ns views.common
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use [cljblog.globals :as globals])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn index
  []
  (partials/layout
    [:h2 "Home"]
    [:section
      (partials/all-posts (blog/retrieve-with "order by" "id desc" "limit" globals/posts-per-page))]))

(defn page
  [page]
  (partials/layout
    [:h2 (str "Page: " page)]
    [:section
      (partials/all-posts (blog/retrieve-with "order by" "id desc" "limit" globals/posts-per-page "offset" (blog/page-offset page)))]))

(defn about
  []
  (partials/layout
    [:h2 "About"]))

(defn fourohfour
  []
  (partials/layout
    [:h2 "fourohfour"]
    [:p "The page you request could not be found."]))
