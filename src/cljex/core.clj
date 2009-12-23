(ns cljex.core
  (:gen-class)
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out,
   clojure.contrib.str-utils,
   cljex.config] ;?
  [:import java.io.File])

;; Configuration
(def *rel-project-root*
     (-> "user.dir" System/getProperty File. .getParentFile .getParentFile))
(def *public-dir* (file-str *project-root* "/src/public/"))

;; Create core documentation text files
(defn build-filename
  "build-filename takes a string like #'clojure.core/and, and turns it into _and.  The underscore protects us from creating '..' as a filename, which is illegal."
  [input]
  (str "_" (second (re-find #"\/(.*)\Z" (str input)))))

(defn print-markdown-doc
  "This is a modified version of print-doc which outputs the documentation in markdown format."
  [v]
  (println (str "###" (:name (meta v)) "###\n"))
  (println (str "> *" (ns-name (:ns (meta v))) "/" (:name (meta v)) "*"))
  (println "> ")
  (println ">     :::clojure")
  (println (str ">     " (:arglists (meta v))))
  (println "> ")
  (when (:macro (meta v))
    (println "> Macro"))
  (println ">  " (re-gsub #"\n" "\n> " (str (:doc (meta v))))))

(defn ns-vals
  "Gets the vals from the map returned by ns-publics for a given nspace."
  [nspace]
  (vals (ns-publics nspace)))

(defn create-core-docs
  "Creates the core documentation in separate files under 'src/core-docs/'"
  []
  (let [docs (ns-vals 'clojure.core)]
    (doseq [s docs]
      (let [filename (build-filename s)]
        (with-out-writer
          (file-str "/home/defn/git/cljex/src/docs/core-docs/" filename)
          (print-markdown-doc s))))))

(defn enumerate-files
  "Enumerates all of the files located under [path]"
  [path]
  (file-seq (java.io.File. path)))

(def *markdown-command* "/usr/local/bin/markdown")

(defn apply-markdown
  "Turns all of the core-docs into html files"
  [path]
  (doseq [f (enumerate-files path)]
    (sh *markdown-command* (str f)
        "-f" (str f ".html")
        "-x codehilite")))

;; Which 

;; Output final print-markdown-doc + examples
(defn docs-index [path-to-files]
  "Creates links to all of the docs located in public/docs/*"
  (html (map #(link-to %)
             (map #(str %)
                  (file-seq (java.io.File. path-to-files))))))

;; Application layout
(defn application-layout [body]
  (html
   [:html
    [:head
     (include-css "pygments.css")
     (include-css "global.css")]
    [:body ,,body,,]]))
