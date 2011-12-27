(ns models.blog
  (:use [cljblog.globals :as globals])
  (:require [clojure.java.jdbc :as sql]))

(defn page-offset
  [page]
  ; returns current posts on specific page given "page" and "posts-per-page".
  (* globals/posts-per-page (- page 1)))

(defn retrieve-all
  []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from blog order by id desc"]
      (into [] results))))

(defn retrieve
  [& limit]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog order by id desc" (str " limit " limit))]
      (into [] results))))

(defn retrieve-with
  [& args]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog" (apply str " " (interpose " " args)))]
      (into [] results))))

(defn create
  [post]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/insert-values :blog [:title] [post])))
