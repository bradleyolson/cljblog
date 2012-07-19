(ns views.admin
  (:use [views.partial :as partials])
  (:use [models.blog :as blog])
  (:use [models.tag :as tags])
  (:use hiccup.core) 
  (:use hiccup.form)
  (:use hiccup.page))

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

(defn editable-post
  [post]
  [:article
   [:h1 [:a { :href (str "/panel/edit/" (post :id)) }
          (post :title)]]])

(defn build-posts-panel
  [posts]
  (if-not (empty? posts)
    (map (fn [post] (editable-post post))
         posts)))

(defn panel
  []
  (partials/layout
    [:a { :href "/panel/new" } "New Post"]
    [:section
     (build-posts-panel
       (blog/retrieve-all))]))

(defn tag-checklist
  [items]
  (println items)
  (map (fn [item] (if-not (and (empty? (item :slug)) (empty? (item :tag)))
                     [:section { :class "taglist" }
                       (check-box (item :slug) nil "tag")
                       [:label { :for (:slug item) } (:tag item)]]))
       items))

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
