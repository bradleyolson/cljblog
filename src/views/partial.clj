(ns views.partial
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn head
  []
  [:header
   [:h1 "Simple Blog Test"]])

(defn nav
  [id]
  [:nav { :id id }
    [:a { :href "/about" } "About"]])

(defn footer
  []
  [:footer
    [:p "&copy;2011 @baoist"]])

(defn layout
  [& content]
  (html
    (html5
      [:head
        [:meta { :http-equiv "Content-type"
                 :content "text/html; charset=utf-8"}]
        [:title "Blogger" ]]
        (include-css "css/style.css")
        (include-js "js/jquery.js", "js/app.js")
      [:body
        (nav "main")
        [:div { :id "wrapper" }
          (head)
          [:section { :id "content" }
            content]
          (footer)]])))
