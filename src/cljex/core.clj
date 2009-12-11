(ns cljex.core
  (:gen-class)
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out,
   clojure.contrib.str-utils]
  [:import java.io.File])

;; basic view templates
(def *header*
     (html-tree
      [:h1 "getclojure"]))

;; navigation
(def *nav*
     (html-tree
      [:ul
       [:li
        [:h3
         [:a {:href ""}]]]
       [:li
        [:h3
         [:a {:href ""}]]]]))

;; footer
(def *footer*
     (html-tree
      [:strong "footer"]))

;; base html layout
(defn html-doc
  [title & body]
  (html
   (doctype "xhtml/transitional")
   [:html
    [:head [:title title]
     (include-css "/src/public/css/global.css"
                  "/src/public/css/pygments.css")]
    [:body
     [:div {:id "header"} *header*]
     [:div {:id "nav"} *nav*]
     ,,body,,
     [:footer
      *footer*]]]))

;; api entry "partial"
(defn api-entry
  [doc-path]
  (html-tree
   [:h2 [:a {:href doc-path}]]))

(defn get-markdown-paths
  [docs-path]  ; must be the /full/path, no ~ allowed
  (let [path (java.io.File. docs-path)]
    (filter #(re-matches #"(.*markdown)" %)
            (map #(str %) (file-seq path)))))

(defn apply-markdown
  [path-to-md write-to-path]
  (sh "/usr/local/bin/markdown" path-to-md "-f" write-to-path "-x codehilite"))

;; (defn markdown-to-html
;;   [paths-coll]
;;   (let [filename (regex...)
;;         basename])
;;   (sh (format "markdown ")))

;; routes
(defroutes main-routes
  (GET "/"
       api-listing)
  (GET "*"))