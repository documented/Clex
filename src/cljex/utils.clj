(ns cljex.utils
  (:use cljex.config
        cljex.build
        compojure
        clojure.contrib.str-utils
        clojure.contrib.duck-streams))

(defn get-file-names-to-set [dir]
  (set (map #(.getName %) (file-seq (java.io.File. dir)))))

(def examples-which-exist
     (let [core-docs (get-file-names-to-set *core-docs*)
           examples  (get-file-names-to-set *examples-dir*)]
       (intersection core-docs examples)))

