(ns cljblog.core
  (:use [views.common :as common])
  (:use compojure.core)
  (:use hiccup.core) 
  (:use hiccup.page-helpers)
  (:use ring.util.response)
  (:use ring.middleware.reload)
  (:use ring.middleware.file)
  (:use ring.middleware.file-info))

(defroutes handler
  (GET "/" []
    (common/index))

  (GET "/404" []
    (common/fourohfour))

  (ANY "/*" []
    (redirect "/404"))
  )

(def app
  (-> #'handler
    (wrap-reload '[cljblog.core])
    (wrap-file "public")))
