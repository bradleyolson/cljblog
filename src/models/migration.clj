(ns models.migration
  (:use [cljblog.globals :as globals])
  (:require [clojure.java.jdbc :as sql]))

(defn table-exists?
  [table-name]
  (sql/with-connection globals/db
    (sql/with-query-results results
      [(str "SELECT * FROM pg_class WHERE relname = '" table-name "'")]
      (> (count results) 0))))

(defn create-posts 
  []
  (sql/with-connection globals/db
    (sql/create-table :blog
                      [:id :serial "PRIMARY KEY"]
                      [:body :varchar "NOT NULL"]
                      [:mdbody :varchar "NOT NULL"]
                      [:title :varchar "NOT NULL"]
                      [:slug :varchar "NOT NULL"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn create-tags
  []
  (sql/with-connection globals/db
    (sql/create-table :tags
                      [:id :serial "PRIMARY KEY"]
                      [:postid :integer "NOT NULL"]
                      [:type :varchar "NOT NULL"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn -main []
  (println (str "Migrating database... to... \"" db "\"")) 
  (flush)
  (if-not (table-exists? "blog")
    ((create-posts) (println "create table 'blog'"))
    (println "table 'blog' exists"))
  (if-not (table-exists? "tags")
    ((create-tags) (println "create table 'tags'"))
    (println "table 'tags' exists"))
  (println "\r\n Ending migrations"))
