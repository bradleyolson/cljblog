(defproject cljblog "0.1.0-SNAPSHOT" :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies
    [[org.clojure/clojure "1.4.0"]
     [org.clojure/clojure-contrib "1.2.0"]
     [org.clojure/java.jdbc "0.1.1"]
     [ring/ring-core "1.0.0"]
     [ring/ring-devel "1.1.0"]
     [ring/ring-jetty-adapter "1.0.0"]
     [compojure "1.1.1"]
     [hiccup "1.0.0"]
     [markdown-clj "0.8"]
     [postgresql/postgresql "8.4-702.jdbc4"]]
  :main cljblog.run)
