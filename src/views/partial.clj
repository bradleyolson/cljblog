(ns views.partial
  (:use [models.blog :as blog])
  (:use [models.tag :as tags])
  (:use [cljblog.globals :as globals])
  (:use hiccup.core) 
  (:use hiccup.page-helpers))

(defn pagi-link
  [n-page tag]
  (if (and (pos? n-page) (> (count (blog/retrieve-with "order by" "id desc" "limit" globals/posts-per-page "offset" (blog/page-offset n-page))) 0))
    [:a { :href (str "/page/" n-page) :class tag } tag]))

(defn pagination
  [page]
  [:nav
   (pagi-link (dec page) "previous")
   (pagi-link (inc page) "next")])

(defn tags-list
  [id]
  [:nav
      (map (fn [tag] [:a { :href (str "/tag/" (:slug tag)) } (:type tag)]) (tags/tags-by-post id))])

(defn single-page-post
  [data]
  (let [post (first data)]
    [:article
      [:h1 (:title post)]
      (:body post)
      (tags-list (:id post))]))

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

(defn build-tag
  [tag]
  [:a { :href (str "/tag/" (:slug tag)) } (:tag tag)])

(defn tag-list
  []
  [:nav { :id "taglist" }
   [:h1 "Topics"]
   (map (fn [tag] 
          (build-tag tag))
        (tags/unique-tags))])

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
        (tag-list)
        [:div { :id "wrapper" }
          [:div {:id "container"}
            (head)
            [:section { :id "content" }
              content]
            (footer)]]])))
