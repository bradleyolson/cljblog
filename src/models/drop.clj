(ns models.drop
  (:require [clojure.java.jdbc :as sql]))

(def db (System/getenv "DATABASE_URL"))

(defn drop-table
  []
  (sql/with-connection db
    (try
      (do
        (sql/drop-table :blog)
        (sql/drop-table :tags)) 
      (catch Exception _))))

(defn -main []
  (println (str "Dropping tables blog, tags in \"" db "\"")) (flush)
  (drop-table)
  (println "done"))
