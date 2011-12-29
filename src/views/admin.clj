(ns views.admin
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use hiccup.core) 
  (:use hiccup.form-helpers)
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

(defn new-post
  []
  (partials/layout
    (form-to [:post "/panel/post"]
      [:section
        (label "title" "Title:")
        (text-field "title")]
      [:section
        (label "body" "Body:")
        [:p { :class "sub"} "this area relies on (or will) markdown."]
        (text-area "body")]
      (submit-button "New Post"))))

(defn post
  [opts]
  (blog/create opts))
