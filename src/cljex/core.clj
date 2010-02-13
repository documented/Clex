(ns cljex.core
  (:gen-class)
<<<<<<< HEAD:src/cljex/core.clj
  (:use compojure
        clojure.contrib.str-utils
        clojure.contrib.duck-streams
        clojure.contrib.shell-out
        clojure.set
        autodoc.collect-info
        (cljex utils build config)
        (cljex.controllers application)
        (cljex.views application))
  (:require clojure.xml
            clojure.walk
            clojure.template
            clojure.test
            clojure.zip)
  (:import (com.petebevin.markdown
            MarkdownProcessor)))

;; TODO
;; ~ Move away from Python highlighting and use JS to do markdown + highlighting instead.
;; ~ Organize this ugly core.clj file into a few places
;; ~ make everything that needs to be displayed happen
;;   in the views.* files

(defn get-file-names-to-set
  "Gets all of the file-names in all of the subdirectories
(including the subdirectory file objects) and creates a set
from that list."
  [dir]
=======
  [:use compojure
   clojure.contrib.str-utils
   clojure.contrib.duck-streams
   clojure.contrib.shell-out
   clojure.contrib.java-utils
   clojure.set
   autodoc.collect-info
   cljex.config
   cljex.build
   cljex.views.application
   cljex.controllers.application]
  [:require
   clojure.xml
   clojure.walk
   clojure.template
   clojure.test
   clojure.zip]
  [:import (com.petebevin.markdown MarkdownProcessor)])

;; TODO
;; ~ Move away from Python highlighting and use JS to do markdown + highlighting instead.

(defn get-file-names-to-set [dir]
>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/core.clj
  (set (map #(.getName %) (file-seq (java.io.File. dir)))))

(def examples-which-exist
     (let [core-docs (get-file-names-to-set *core-docs*)
           examples  (get-file-names-to-set *examples-dir*)]
       (intersection core-docs examples)))

<<<<<<< HEAD:src/cljex/core.clj
(defn basic-layout [& body]
  (html
   (doctype :xhtml)
   [:html
    [:head
     [:title *site-title*]
     (include-css "/css/global.css"
                  "/css/github.css"
                  "/css/pygments.css")]
    [:body ,,,body,,,]]))

(defn get-doc [doc]
  (str (sh *markdown-command*
           (str *core-docs* doc)
           "-x" "codehilite")
       (if (examples-which-exist doc)
         (sh *markdown-command*
             (str *examples-dir* doc)
             "-x" "codehilite"))))

(defn get-doc [doc]
  (str ))

(defn get-doc-markdown [doc]
  (str (sh *markdown-command* (str *core-docs* doc))
       (if (examples-which-exist doc)
         (sh *markdown-command* (str *examples-dir* doc)))))

(defn markdown [doc]
  (let [md (new MarkdownProcessor)]
    (str (.markdown md (slurp (str *core-docs* doc)))
     (if (examples-which-exist doc)
       (.markdown md (slurp (str *examples-dir* doc)))))))

=======
>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/core.clj
                                        ;==============================
                                        ; Layout
                                        ;==============================
(defn get-doc-index []
  (sort (rest (file-seq (java.io.File. *core-docs*)))))

(defn link-to-frame
  "Wraps some content in a HTML hyperlink
with the supplied URL and target frame."
  [url frame & content]
  [:a {:href url :target frame} content])

(defn gen-doc-index []
  (interpose [:br]
             (map #(link-to (str "/docs/" (.getName %))
                            (rest (seq (.getName %))))
                  (get-doc-index))))

(defn gen-doc-index-frame []
  (basic-layout
   (html
    (cons [:h1 "cljex"]
          (interpose [:br]
                     (map #(link-to-frame
                            (str "/docs/" (.getName %))
                            "main"
                            (rest (seq (.getName %))))
                          (get-doc-index)))))))

(defn app-layout [& body]
  (html
   (doctype :html4)
   [:html
    [:head
     [:title *site-title*]
     (include-css "/css/pygments.css" "/css/global.css")
     (javascript-tag "/js/highlight.pack.js")]
    [:body
     [:div#header [:h1 *site-title*]]
     [:div#nav (unordered-list [(link-to "/" "[index]")
                                (link-to "http://github.com/defn/cljex" "[contribute]")])]
     [:div#left (ordered-list (gen-doc-index))]
     [:div#right ,,,,,body,,,,,]]]))

(defn app-layout-frames [& body]
  (html
   (doctype :xhtml)
   [:html
    [:head
     [:title *site-title*]
     (include-css "/css/pygments.css" "/css/global.css")
     [:base {:href "http://localhost:8080/docs" :target "main"}]]
    [:frameset {:cols "250,*" :frameborder 0 :marginwidth 0 :marginheight 0 :scrolling "auto"}
     [:frame {:src "http://localhost:8080/doc-index" :name "left"}]
     [:frame {:src "http://localhost:8080/home" :name "main"}]]]))

(defn doc-page [doc]
  (basic-layout (get-doc-markdown doc)))

(def home-page
     (html
      [:h2 "Gentlemen, we have a whole lot of work to do..."]
      [:p "Please consider "
       (link-to "http://github.com/defn/cljex" "contributing")
       " to this project.  It's a great way to learn more about Clojure and contribute to the community while you do it.  If you look to your right, you'll notice a whole lot of forms which need examples.  Many of them yare one-liners and wont take more than a minute."]
      [:p "If you don't have the time, no worries, but do consider "
       (link-to "http://clojure.org/" "donating")
       " to Clojure if you're able."]))

                                        ;==============================
                                        ; Define Routes
                                        ;==============================
(defroutes doc-routes
  (GET "/" (app-layout-frames))
  (GET "/docs/:name" (doc-page (:name params)))
  (GET "/doc-index" (gen-doc-index-frame))
  (GET "/home" (basic-layout home-page)))

(defroutes static-routes
  (GET "/*" (serve-file *public-dir* (params :*))))

(defroutes error-routes
  (ANY "/*" [404 "404 Page Not Found"]))

(defroutes all-routes
  doc-routes
  static-routes
  error-routes)

                                        ;==============================
                                        ; Start Your REPL Engines
                                        ;==============================
;; (run-server
;;  {:port 8081}
;;  "/*" (servlet all-routes))

                                        ;==============================
                                        ; Main
                                        ;==============================
(def namespaces-to-document
     ['clojure.core
      'clojure.set
      'clojure.template
      'clojure.test
      'clojure.walk
      'clojure.xml
      'clojure.zip])
<<<<<<< HEAD:src/cljex/core.clj
=======

>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/core.clj

(defn -main [& args]
  (if (= (first args) "--server")
    (run-server
     {:port 8080}
     "/*" (servlet all-routes))
    (doseq [ns namespaces-to-document]
      (create-docs ns))))
