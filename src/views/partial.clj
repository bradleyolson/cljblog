(ns views.partial
  (:use [models.blog :as blog])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))


(defn single-post
  [data]
    [:article
      [:h1 (:title data)]
      [:p (:body data)]])

(defn all-posts
  [data]
  (map (fn [post] (single-post post)) 
       data))

(defn head
  []
  [:header
   [:h1 "Simple Blog Test"]])

(defn nav
  [id]
  [:nav { :id id }
    [:a { :href "/" } 
      [:span "Home"]]  
    [:a { :href "/about" } 
      [:span "About"]]])

(defn footer
  []
  [:footer
    [:p "&copy;2011 "
     [:a { :href "http://twitter.com/baoist" :target "_new" } "@baoist"]]])

(defn layout
  [& content]
  (html
    (html5
      [:head
        [:meta { :http-equiv "Content-type"
                 :content "text/html; charset=utf-8"}]
        [:title "Blogger" ]]
        (include-css "/css/style.css")
        (include-js "/js/jquery.js" "/js/app.js")
      [:body
        (nav "main")
        [:div { :id "wrapper" }
          [:div {:id "container"}
            (head)
            [:section { :id "content" }
              content]
            (footer)]]])))
