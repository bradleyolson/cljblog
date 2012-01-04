(ns views.partial
  (:use [models.blog :as blog])
  (:use [cljblog.globals :as globals])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn pagi-prev
  [n-page]
  (if (and (pos? n-page) (> (count (blog/retrieve-with "order by" "id desc" "limit" globals/posts-per-page "offset" (blog/page-offset n-page))) 0)) 
    [:a { :href (str "/page/" n-page) } "&larr; previous"]))

(defn pagi-next
  [n-page]
  (if (and (pos? n-page) (> (count (blog/retrieve-with "order by" "id desc" "limit" globals/posts-per-page "offset" (blog/page-offset n-page))) 0))
    [:a { :href (str "/page/" n-page) } "next &rarr;"]))

(defn pagination
  [page]
  [:nav
   (pagi-prev (dec page))
   (pagi-next (inc page))])

(defn single-page-post
  [data]
  (let [post (first data)]
    [:article
      [:h1 (:title post)]
      (:body post)]))

(defn single-post
  [data]
  [:article
    [:h1 [:a { :href (str "/post/" (:slug data)) } (:title data)]]
    (:body data)])

(defn all-posts
  [data]
  (map (fn [post] (single-post post)) 
       data))

(defn head
  []
  [:header
   [:h1 [:a { :href "/" } "Baoblog" [:sup "A Place Where Bradley Olson Blogs"]]]])

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
     [:a { :href "http://twitter.com/baoist" :target "_new" } "@baoist"]]
    [:a { :href "https://github.com/bradleyolson/cljblog" :class "git" :target "_new" } "via github"]
    [:img { :src "/images/clojure.gif" :alt "clojure icon" :width "20px" }]])

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
