(ns cljex.build
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out])

(defn get-markdown-paths
  [docs-path]  ; must be the /full/path, no ~ allowed
  (let [path (java.io.File. docs-path)]
    (filter #(re-matches #"(.*markdown)" %)
            (map #(str %) (file-seq path)))))

(defn apply-markdown
  [path-to-md write-to-path]
  (sh "/usr/local/bin/markdown" path-to-md "-f" write-to-path "-x codehilite"))