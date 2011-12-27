(ns cljblog.core
  (:use [views.common :as common])
  (:use [views.admin :as admin])
  (:require [models.blog :as model])
  (:use clojure.core)
  (:use hiccup.core) 
  (:use hiccup.page-helpers)
  (:use compojure.core)
  (:use [ring.adapter.jetty :as ring])
  (:use ring.util.response)
  (:use ring.middleware.reload)
  (:use ring.middleware.file)
  (:use ring.middleware.file-info))

(defroutes handler
  (GET "/" []
    (common/index))

  (GET "/about" []
    (common/about))

  (GET "/page" []
    (common/page 1))

  (GET "/page/:page-num" [page-num]
    (try (let [current-page (Integer/parseInt page-num)]
           (common/page current-page))
      (catch Exception _ 
        (redirect "/404"))))

  (GET "/login" []
    (admin/login))

  (GET "/panel" []
    (admin/panel))

  (GET "/panel/new" []
    (admin/newpost))

  (POST "/panel/post" []
    (admin/post))

  (GET "/404" []
    (common/fourohfour))

  (ANY "/*" []
    (redirect "/404"))

  )

(def app
  (-> #'handler
    (wrap-reload '[cljblog.core])
    (wrap-file "public")))

(defn start [port]
  (ring/run-jetty app {:port (or port 2323) :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))
