(ns cljex.view.application
  (:use (compojure.html gen page-helpers)
        (clojure.contrib pprint)
        (cljex.config)))

;; (defn html-doc
;;   [title index-frame body]
;;   (html
;;    (doctype "xhtml/transitional")
;;    [:html
;;     [:head [:title title]
;;      (include-css "../../src/public/css/global.css"
;;                   "../../src/public/css/pygmentize.css")]
;;     [:frameset {:cols "175px,*"}
;;      [:frame#name "index"
;;       [:html [:head [:title "index"]]
;;        [:body
;;         [:ul
;;          (map #(vector :li (link %)) (all-examples))]]]]
;;      [:frame {:name "examples"}
;;       [:html [:head [:title "examples"]]
;;        [:body body]]]]]))