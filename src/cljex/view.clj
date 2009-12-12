(ns cljex.view
  [:use compojure])

(defn api-entry
  [doc-path]
  (html-tree
   [:h2 [:a {:href doc-path}]]))

(defn example-entry
  [example-entry])

(defn html-doc
  [title & body]
  (html
   (doctype "xhtml/transitional")
   [:html
    [:head [:title title] [:meta {:name }]
     (include-css "../../src/public/css/global.css"
                  "../../src/public/css/pygments.css")]
    [:frameset {:cols "175px,*"}
     [:frame {:name "index"}
      [:html [:head [:title "index"]]
       [:body index]]]
     [:frame {:name "examples"}
      [:html [:head [:title "examples"]]
       [:body body]]]]]))