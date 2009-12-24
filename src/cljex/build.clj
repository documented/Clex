(ns cljex.build
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out
   clojure.contrib.str-utils
   cljex.config])

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

(defn apply-markdown-and-html
  []
  (doseq [f (enumerate-files "/home/defn/git/cljex/src/docs/core-docs/")]
    (spit (java.io.File. (str f ".html")) (html
           [:html
            [:head (include-css "pygments.css")]
            [:body (str (sh *markdown-command* (str f) "-x" "codehilite"))]]))))

(defn enumerate-files
  "Enumerates all of the files located under [path]"
  [path]
  (file-seq (java.io.File. path)))

(defn apply-markdown
  "Turns all of the core-docs into html files"
  []
  (doseq [f (enumerate-files "/home/defn/git/cljex/src/docs/core-docs/")]
    (sh *markdown-command* (str f)
        "-f" (str f ".html") "-x" "codehilite")))

(defn apply-markdown-and-html
  []
  (doseq [f (enumerate-files "/home/defn/git/cljex/src/docs/core-docs/")]
    (spit (java.io.File. (str f ".html")) (html
           [:html
            [:head (include-css "pygments.css")]
            [:body (str (sh *markdown-command* (str f) "-x" "codehilite"))]]))))

(defn compose-examples
  []
  (let [examples (enumerate-files *examples-dir*)
        core-docs (enumerate-files *core-docs*)]
    ))

;; Create two sets and do an intersection