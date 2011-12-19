(ns views.admin
  (:use [views.partial :as partials])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn login
  []
  (partials/layout
    [:form
     [:section
       [:label { :for "username" } "Username:"]
       [:input { :type "text" :id "username" :name "username" :value "Username"}]]
     [:section
       [:label { :for "password" } "Password:"]
       [:input { :type "password" :id "password" :name "password"}]]
     [:input { :type "submit" :value "Login" }]]
    ))
