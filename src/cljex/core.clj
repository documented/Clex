(ns cljex.core
  (:gen-class)
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out,
   clojure.contrib.str-utils,
   cljex.config
   cljex.build]
  [:import java.io.File])

;; Output final print-markdown-doc + examples
(defn docs-index []
  "Creates links to all of the docs located in public/docs/*"
  (html (map #(link-to % (strip-dot-html %))
             (map #(str %)
                  (rest (file-seq *doc-output-dir*))))))

(defn create-index []
  (spit (file-str *public-dir* "/" "index.html")
        (template (docs-index))))


(defn -main [])
;; TODO ;;
                                        ; - Create a function to add the examples in docs/examples/
