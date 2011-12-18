(ns cljblog.core
  (:use [views.partial :as partials])
  (:use compojure.core)
  (:use hiccup.core) 
  (:use hiccup.page-helpers)
  (:use ring.util.response)
  (:use ring.middleware.reload)
  (:use ring.middleware.file)
  (:use ring.middleware.file-info))

(defn index []
  (partials/layout
    [:h2 "foo"]))

(defn fourohfour []
  (partials/layout
    [:h2 "The requested file could not be found"]))

(defroutes handler
  (GET "/" []
    (index))

  (GET "/404" []
    (fourohfour))

  (ANY "/*" []
    (redirect "/404"))

  )

(def app
  (-> #'handler
    (wrap-reload '[cljblog.core])
    (wrap-file "public")))
