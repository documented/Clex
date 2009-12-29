(ns cljex.core
  (:gen-class)
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out,
   clojure.contrib.str-utils,
   cljex.config] ;?
  [:import java.io.File])

;; Output final print-markdown-doc + examples
(defn docs-index []
  "Creates links to all of the docs located in public/docs/*"
  (html (map #(link-to %)
             (map #(str %)
                  (file-seq (java.io.File. *doc-output-dir*))))))


(defn -main [])
;; TODO ;;
                                        ; - Create a function to add the examples in docs/examples/
