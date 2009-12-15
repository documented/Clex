(ns cljex.view
  (:use (compojure.html gen page-helpers)
        (clojure.contrib pprint)
        (config db util)))

(defn api-entry
  [doc-path]
  (html-tree
   [:h2 [:a {:href doc-path}]]))

;; (defn example-entry
;;   [example-entry])

(defmulti url type)

(defmethod url :tag [tag]
  (str "/tag/" (tag :id)))

(defmethod url :example [example]
  (str "/example/" (example :id)))

(defmethod url :default [x]
  (die "I can't make a url out of a " (type x)))

(defn link [x]
  (link-to {:class (str (type x) "-link")}
           (url x)
           (x :title)))

(defn html-doc
  [title index-frame body]
  (html
   (doctype "xhtml/transitional")
   [:html
    [:head [:title title]
     (include-css "../../src/public/css/global.css"
                  "../../src/public/css/pygmentize.css")]
    [:frameset {:cols "175px,*"}
     [:frame {:name "index"}
      [:html [:head [:title "index"]]
       [:body
        [:ul
         (map #(vector :li (link %)) (all-examples))]]]]
     [:frame {:name "examples"}
      [:html [:head [:title "examples"]]
       [:body body]]]]]))