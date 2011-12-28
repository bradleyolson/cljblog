(ns models.migration
  (:require [clojure.java.jdbc :as sql]))

(def db (System/getenv "DATABASE_URL"))

(defn create-posts 
  []
  (sql/with-connection db
    (sql/create-table :blog
                      [:id :serial "PRIMARY KEY"]
                      [:body :varchar "NOT NULL"]
                      [:title :varchar "NOT NULL"]
                      [:slug :varchar "NOT NULL"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn -main []
  (print (str "Migrating database... to... \"" db "\"")) (flush)
  (create-posts)
  (println "\r\n done"))
