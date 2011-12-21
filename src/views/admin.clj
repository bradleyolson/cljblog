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
       [:input { :type "text" :id "username" :name "username" }]]
     [:section
       [:label { :for "password" } "Password:"]
       [:input { :type "password" :id "password" :name "password" }]]
     [:input { :type "submit" :value "Login" }]]
    ))

(defn panel
  []
  (partials/layout
    [:a { :href "/panel/new" } "New Post"]))

(defn newpost
  []
  (partials/layout
    [:a { :href "/panel/new" } "New Post"]))

(defn post
  []
  (partials/layout
    [:a { :href "/panel/new" } "New Post"]))  

