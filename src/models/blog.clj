(ns models.blog
  (:require [clojure.java.jdbc :as sql]))

(defn retrieve
  [& limit]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog order by id desc" (if (not (nil? limit)) (str " limit " limit)))]
      (into [] results))))

(defn sql-str
  [& args]
  (let [base "select * from blog"]
    (map (fn [arg]
      (str base (str arg "foo")))
       args)))

(defn retrieve-args
  [& args]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/with-query-results results
      [(str "select * from blog order by id desc" (if (not (nil? limit)) (str " limit " limit)))]
      (into [] results))))

(defn create [shout]
  (sql/with-connection (System/getenv "CLJBLOG_DATABASE_URL")
    (sql/insert-values :shouts [:body] [shout])))
