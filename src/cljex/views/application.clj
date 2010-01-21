(ns cljex.views.application
  (:use compojure
        cljex.config))

(defn basic-layout [& body]
  (html
   (doctype :xhtml)
   [:html
    [:head
     [:title *site-title*]
     (include-css "/css/global.css" "/css/github.css" "/css/pygments.css")]
    [:body ,,,body,,,]]))


(defn get-doc-index []
  (sort (rest (file-seq (java.io.File. *core-docs*)))))