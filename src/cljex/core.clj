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
  (html
   (interpose [:br] (map #(link-to % (strip-dot-html (str %)))
                         (rest (file-seq *doc-output-dir*))))))

(defn create-index []
  (spit (file-str *public-dir* "/" "index.html")
        (template (docs-index))))

(defroutes cljex
  (GET "/"
       (template (docs-index))))

(defn -main []
  (run-server {:port 5885}
              "/*" (servlet cljex)))
