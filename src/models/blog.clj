(ns models.blog
  (:require [clojure.java.jdbc :as sql]))

(def posts-per-page 1)

(defn page-offset
  [page post-count]
  (str (interpose " " ["offset" (* page post-count)])))

(defn retrieve-all
  []
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      ["select * from blog order by id desc"]
      (into [] results))))

(defn retrieve
  [& limit]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog order by id desc" (str " limit " limit))]
      (into [] results))))

(defn retrieve-with
  [& args]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog" (apply str " " (interpose " " args)))]
      (into [] results))))

(defn create [shout]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/insert-values :shouts [:body] [shout])))
