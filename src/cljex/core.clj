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

(defn sort-ns
  "Sorts the keys from the map returned by ns-publics for a given nspace into alphabetical order."
  [nspace]
  (vals (ns-publics nspace)))

;; modified print doc to pop the current clj documentation for a form into the markdown file of the same name
(defn print-doc-out)

;; temporary reference for the above fn
(defn print-ns-docs-to-file
 "Prints the documentation for a given nspace to a file-name which is located on path."
 [nspace, path-to-file, file-name]
 (with-out-writer
   (file-str path-to-file file-name)
   (doseq [s (sort-ns nspace)]
     (print-doc s))))

;; routes
(defroutes main-routes
  (GET "/"
       api-listing)
  (GET "*"))