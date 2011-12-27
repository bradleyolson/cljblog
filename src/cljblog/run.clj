(ns cljblog.run
  (:use [ring.adapter.jetty :as ring])
  (:require [cljblog.core :as core]))

(defn -main
  []
  (ring/run-jetty core/app {:port (or (Integer/parseInt (System/getenv "PORT")) 2323) :join? false}))
