(ns models.tag)

(defn tag-exists?
  [tagname]
  (= "foo" "Bar"))

(defn add-tag
  [tagname]
  tagname)

(defn add-tags
  [& tags]
  (let [tag (first tags)
        remaining (rest tags)]
    (if-not (tag-exists? tag)
      (add-tag tag))
    (if-not (empty? remaining)
      (recur remaining))))

(defn -main []
  (println "running")
  (add-tags "foo" "bar" "baz" "wibble")
  (println "end"))
