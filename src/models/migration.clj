(ns models.migration
  (:use [cljblog.globals :as globals])
  (:require [clojure.java.jdbc :as sql]))

(defn table-exists?
  [table-name]
  (sql/with-connection globals/db
    (sql/with-query-results results
                            (println (str "select relname from pg_class where relname='" table-name "'"))
      [(str "SELECT * FROM pg_class WHERE relname = '" table-name "'")]
      (println results))))

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
  (print (str "Migrating database... to... \"" db "\"")) 
  (table-exists? "blog")  
  (flush)
  (println "\r\n done"))
