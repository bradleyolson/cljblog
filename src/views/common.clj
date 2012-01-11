(ns views.common
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use [models.tag :as tags])
  (:use [cljblog.globals :as globals])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn index
  []
  (partials/layout
    [:section
      (partials/all-posts (blog/retrieve-with "order by" "id desc" "limit" globals/posts-per-page))]
      (partials/pagination 1)))

(defn single
  [slug]
  (let [post (blog/retrieve-with "WHERE" (str "slug = '" slug "'"))]
    (if-not (empty? post)
      (partials/layout (partials/single-page-post post))
      false)))

(defn page
  [page]
  (partials/layout
    [:h2 "Page: " page]
    [:section
      (partials/all-posts (blog/retrieve-with "order by" "id desc" "limit" globals/posts-per-page "offset" (blog/page-offset page)))]
      (partials/pagination page)
      (partials/tags-list page)))

(defn posts-by-tag
  [tag & page]
  (let [posts (map (fn [tag] (blog/post-by-id (:postid tag))) (tags/tags-by-slug tag))]
    (partials/layout 
      (partials/all-posts posts))))

(defn about
  []
  (partials/layout
    [:h2 "About"]))

(defn fourohfour
  []
  (partials/layout
    [:h2 "fourohfour"]
    [:p "The page you request could not be found."]))
