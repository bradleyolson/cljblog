(ns models.drop
  (:require [clojure.java.jdbc :as sql]))

(def db (System/getenv "DATABASE_URL"))

(defn drop-table
  []
  (sql/with-connection db
    (try
      (sql/drop-table :blog)
      (catch Exception _))))

(defn -main []
  (print (str "Dropping table blog in \"" db "\"")) (flush)
  (drop-table)
  (println "\r\n done"))
