(ns views.admin
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use [models.tag :as tags])
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

(defn tag-checklist
  [items]
  (map (fn [item] [:section { :class "taglist" }
                   (check-box (item :slug) nil (:tag item))
                   [:label { :for (:slug item) } (:tag item)]]) items))

(defn new-post
  []
  (partials/layout
    (form-to [:post "/panel/post"]
      [:section
        (label "title" "Title:")
        (text-field "title")]
      [:section
        (label "mdbody" "Body:")
        [:p { :class "sub"} "this area relies on (or will) markdown."]
        (text-area "mdbody")]
      [:section
       [:h1 "Tags"]
       (tag-checklist (tags/unique-tags))
       (text-field "tag") 
       (text-field "tag")]
      (submit-button "New Post"))))

(defn edit-post
  [post]
  (partials/layout
    (form-to [:post "/panel/post"]
      [:section
        (label "title" "Title:")
        (text-field "title" (post :title))]
      [:section
        (label "mdbody" "Body:")
        [:p { :class "sub"} "this area relies on (or will) markdown."]
        (text-area "mdbody" (post :mdbody))]
      (submit-button "Edit Post"))))
