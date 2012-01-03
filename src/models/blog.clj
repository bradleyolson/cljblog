(ns models.blog
  (:use [cljblog.globals :as globals])
  (:use [clojure.contrib.math :as math]) 
  (:require [clojure.java.jdbc :as sql]))

(defn page-offset
  [page]
  "returns current posts on specific page given [page posts-per-page]"
  (* globals/posts-per-page (- page 1)))

(defn retrieve-all
  []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from blog order by id desc"]
      (into [] results))))

(defn retrieve-with
  [& args]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog" (apply str " " (interpose " " args)))]
      (into [] results))))

(defn page-post-count
  [page]
  (count (retrieve-with "offset" (page-offset page)))) 

(defn last-page
  []
  (math/ceil (/ (count (retrieve-all)) globals/posts-per-page)))

(defn prepare-slug
  [slug]
  (clojure.string/lower-case
     (clojure.string/replace (clojure.string/replace slug #"\ " "-") #"[^a-zA-Z0-9\-]" "")))

(defn create-slug
  "takes title and converts spaces to hyphens, if slug already exists it loops through to add -num to it."
  [title & versions]
  (let [slug (prepare-slug title)
        version (first versions)]
    (if version
      (str slug "-" (+ 1 version))
      slug)))

(defn convert-md
  [text]
  text)

(defn create
  [post]
  (let [post (conj post { :slug (create-slug (post :title)) :body (convert-md (post :mdbody)) })]
    (sql/with-connection (System/getenv "DATABASE_URL")
      (sql/insert-values :blog (keys post) (vals post)))))
