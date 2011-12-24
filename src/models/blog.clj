(ns models.blog
  (:require [clojure.java.jdbc :as sql]))

(defn retrieve
  [& limit]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog order by id desc" (if (not (nil? limit)) (str " limit " limit)))]
      (into [] results))))

(defn retrieve-args-holder
  [limit]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog order by id desc" (if (not (nil? limit)) (str " limit " limit)))]
      (into [] results))))

(defn retrieve-with
  [& args]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog order by id desc" (if (not (nil? args)) (apply str " " (interpose " " args))))]
      (into [] results))))

(defn create [shout]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/insert-values :shouts [:body] [shout])))
