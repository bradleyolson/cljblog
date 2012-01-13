(ns models.tag
  (:use [cljblog.globals :as globals])
  (:use [clojure.contrib.math :as math]) 
  (:require [clojure.java.jdbc :as sql]))

(defn tag-slug
  [slug]
  (clojure.string/lower-case 
    (clojure.string/replace (clojure.string/replace slug #"\ " "-") #"[^a-zA-Z0-9\-]" "")))

(defn all-tags
  []
  (sql/with-connection globals/db
    (sql/with-query-results results
      ["select * from tags"]
      (into [] results))))

(defn unique-tags
  []
  (let [tags (map (fn [tag] (hash-map :tag (:type tag) :slug (:slug tag))) (all-tags))]
    (distinct tags)))

(defn tags-by-slug
  [slug]
  (sql/with-connection globals/db
    (sql/with-query-results results
      [(str "select * from tags where slug='" slug "'")]
      (into [] results))))

(defn tags-by-post
  [id]
  (sql/with-connection globals/db
    (sql/with-query-results results
      [(str "select * from tags where postid='" id "'")]
      (into [] results))))

(defn add-tag
  [id tagname]
  (let [tag-vars { :postid id :type tagname :slug (tag-slug tagname)}]
    (sql/with-connection globals/db
      (sql/insert-values :tags (keys tag-vars) (vals tag-vars)))
    tag-vars))

(defn format-tags
  [id tags-list]
  (map #(add-tag id %) tags-list))

(defn create-tags
  [id tags-list]
  (if-not (empty? tags-list)
    (format-tags id (distinct tags-list))))
