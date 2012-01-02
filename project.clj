(defproject cljblog "1.0.0-SNAPSHOT"
  :description "A Simple Blog in Clojure"
  :dependencies 
    [[org.clojure/clojure "1.3.0"]
     [org.clojure/clojure-contrib "1.2.0"]
     [org.clojure/java.jdbc "0.1.1"]
     [ring/ring-core "1.0.0"]
     [ring/ring-devel "1.0.0"]
     [ring/ring-jetty-adapter "1.0.0"]
     [compojure "0.6.4"]
     [hiccup "0.3.7"]
     [postgresql/postgresql "8.4-702.jdbc4"]]
  :dev-dependencies
    [[lein-run "1.0.0-SNAPSHOT"]
     [org.clojure/java.jdbc "0.1.1"]])
