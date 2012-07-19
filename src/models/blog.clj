(ns models.blog
  (:use [models.tag :as tags])
  (:use [cljblog.globals :as globals])
  (:use [clojure.contrib.math :as math])
  (:use markdown)
  (:require [clojure.java.jdbc :as sql]))

(defn page-offset
  [page]
  "returns current posts on specific page given [page posts-per-page]"
  (* globals/posts-per-page (- page 1)))

(defn retrieve-all
  []
  (sql/with-connection globals/db
    (sql/with-query-results results
      ["select * from blog order by id desc"]
      (into [] results))))

(defn post-by-id
  [id]
  (sql/with-connection globals/db
    (sql/with-query-results results
      [(str "select * from blog where id=" id)]
      (first results))))

(defn retrieve-with
  [& args]
  (sql/with-connection globals/db
    (sql/with-query-results results
      [(str "select * from blog" (apply str " " (interpose " " args)))]
      (into [] results))))

(defn page-post-count
  [page]
  (count (retrieve-with "offset" (page-offset page))))

(defn last-page
  []
  (math/ceil (/ (count (retrieve-all)) globals/posts-per-page)))

(defn slug-exists?
  [test-slug]
  (> (count (retrieve-with "WHERE" (str "slug = '" test-slug "'"))) 0))

(defn prepare-slug
  [slug & version]
  (let [built-slug (clojure.string/lower-case (clojure.string/replace (clojure.string/replace slug #"\ " "-") #"[^a-zA-Z0-9\-]" ""))]
    (if (first version)
      (str built-slug "-" (first version))
      built-slug)))

(defn post-slug
  "takes title and converts spaces to hyphens, if slug already exists it loops through to add -num to it."
  [title & versions]
  (if (slug-exists? (prepare-slug title (first versions)))
    (post-slug title (inc (or (first versions) 0)))
    (prepare-slug title (first versions))))

(defn create
  [post & tags-list]
  (let [post-vars (conj post { :slug (post-slug (post :title)) :body (markdown/md-to-html-string (post :mdbody)) })
        post (sql/with-connection globals/db
               (sql/insert-values :blog (keys post-vars) (vals post-vars)))]
      (tags/create-tags (:id post) (first tags-list))))
