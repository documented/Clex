(ns cljex.core
  (:gen-class)
  [:use compojure
   clojure.contrib.str-utils
   clojure.contrib.duck-streams
   clojure.contrib.shell-out
   clojure.set
   autodoc.collect-info
   cljex.config]
  [:require
   clojure.xml
   clojure.walk
   clojure.template
   clojure.test
   clojure.stacktrace
   clojure.zip
   clojure.inspector])

;; TODO
;; ~ Make dynamic frames with some JS
;; ~ Move away from Python highlighting and use JS to do markdown + highlighting

                                        ;==============================
                                        ; Namespace Functions
                                        ;==============================
(defn discover-namespace
  "Gets all of the vals in the map produced by (ns-publics 'ns)"
  [ns]
  (vals (ns-publics ns)))

(defmacro discover-namespace* [ns] `(discover-namespace '~ns))

                                        ;==============================
                                        ; Format/Output Documentation
                                        ;==============================
(defn print-markdown-doc
  "Prints documentation in markdown format."
  [v]
  [(str "###" (:name (meta v)) "###\n")
   (str "> *" (ns-name (:ns (meta v))) "/" (:name (meta v)) "*")
   (str "> ")
   (str ">     :::clojure")
   (str ">     " (:arglists (meta v)) "")
   (str "> ")
   (when (:macro (meta v))
     (str "> *Macro*\n"))
   (str ">  " (re-gsub #"\n" "\n>" (str (:doc (meta v)))))])

(defn create-docs
  "Create the documentation for [nspace] in the *core-docs* directory."
  [nspace]
  (doseq [f (discover-namespace nspace)]
    (let [filename (str "_" (:name (meta f)))]
      (write-lines (file-str *core-docs* filename)
                   (print-markdown-doc f)))))

                                        ;==============================
                                        ; Example Builder
                                        ;==============================
(defn get-file-names-to-set [dir]
  (set (map #(.getName %) (file-seq (java.io.File. dir)))))

(def examples-which-exist
     (let [core-docs (get-file-names-to-set *core-docs*)
           examples  (get-file-names-to-set *examples-dir*)]
       (intersection core-docs examples)))

                                        ;==============================
                                        ; Communicate with the server
                                        ;==============================

(defn basic-layout [& body]
  (html
   (doctype :xhtml)
   [:html
    [:head
     [:title *site-title*]
     (include-css "/css/global.css" "/css/github.css" "/css/pygments.css")]
    [:body ,,,body,,,]]))

(defn get-doc [doc]
  (str (sh *markdown-command* (str *core-docs* doc) "-x" "codehilite")
       (if (examples-which-exist doc)
         (sh *markdown-command* (str *examples-dir* doc) "-x" "codehilite"))))

(defn get-doc-markdown [doc]
  (str (sh *markdown-command* (str *core-docs* doc))
       (if (examples-which-exist doc)
         (sh *markdown-command* (str *examples-dir* doc)))))

                                        ;==============================
                                        ; Layout
                                        ;==============================
(defn get-doc-index []
  (sort (rest (file-seq (java.io.File. *core-docs*)))))

(defn link-to-frame
  "Wraps some content in a HTML hyperlink with the supplied URL and target frame."
  [url frame & content]
  [:a {:href url :target frame} content])

(defn gen-doc-index []
  (interpose [:br]
             (map #(link-to (str "/docs/" (.getName %))
                            (rest (seq (.getName %))))
                  (get-doc-index))))

(defn gen-doc-index-frame []
  (basic-layout
   (interpose [:br]
              (map #(link-to-frame
                     (str "/docs/" (.getName %))
                     "main"
                     (rest (seq (.getName %))))
                   (get-doc-index)))))

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
  (GET "/doc-index" (gen-doc-index-inline))
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
                                        ; Start Your REPL Engine
                                        ;==============================
;; (run-server
;;  {:port 8080}
;;  "/*" (servlet all-routes))

                                        ;==============================
                                        ; Main
                                        ;==============================
(def namespacen
     ['clojure.core
      'clojure.set
      'clojure.stacktrace
      'clojure.template
      'clojure.test
      'clojure.walk
      'clojure.xml
      'clojure.zip
      'clojure.inspector])

(defn -main [& args]
  (if (= (first args) "--server")
    (run-server
     {:port 8080}
     "/*" (servlet all-routes))
    (doseq [ns namespacen]
      (create-docs ns))))

