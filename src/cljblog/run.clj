(ns cljblog.run
  (:use [ring.adapter.jetty :as ring])
  (:require [cljblog.core :as core]))

(defn -main
  []
  (let [port (if-not (nil? (System/getenv "PORT"))
               (Integer/parseInt (System/getenv "PORT"))
               2323)]
    (ring/run-jetty core/app {:port port :join? false})))
