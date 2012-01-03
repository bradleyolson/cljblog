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
  (:use ring.middleware.params)
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

  (GET "/page/last" []
    (common/page 1))

  (GET "/page/:page-num" [page-num]
    (try (let [current-page (Integer/parseInt page-num)]
           (if (> (model/page-post-count current-page) 0)
             (common/page current-page)
             (redirect (str "/page/" (model/last-page)))))
      (catch Exception _ 
        (redirect "/404"))))

  (GET "/post/:slug" [slug]
    (try 
      (common/single slug)
      (catch Exception _
        (redirect "/404"))))

  (GET "/login" []
    (admin/login))

  (GET "/panel" []
    (admin/panel))

  (GET "/panel/new" []
    (admin/new-post))

  (GET "/panel/post" []
    (admin/panel))

  (POST "/panel/post" 
    [title mdbody]
    (admin/post {:title title :mdbody mdbody}))

  (GET "/404" []
    (common/fourohfour))

  (ANY "/*" []
    (redirect "/404"))
  )

(def app
  (-> #'handler
    (wrap-params routes)
    (wrap-reload '[cljblog.core])
    (wrap-file "public")))
