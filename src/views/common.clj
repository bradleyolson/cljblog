(ns views.common
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

; add globals file

(defn index
  []
  (partials/layout
    [:h2 "Home"]
    [:section
      (partials/all-posts (blog/retrieve-with "order by" "id desc" "limit" 1))]))

(defn page
  [page]
  (partials/layout
    [:h2 (str "Page: " page)]
    [:section
      (partials/all-posts (blog/retrieve-with "order by" "id desc" "limit" 2 "offset" (blog/page-offset page)))]))

(defn about
  []
  (partials/layout
    [:h2 "About"]))

(defn fourohfour
  []
  (partials/layout
    [:h2 "fourohfour"]))
