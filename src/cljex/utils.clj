<<<<<<< HEAD:src/cljex/utils.clj
(ns cljex.utils)
=======
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

>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/utils.clj
