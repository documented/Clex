(ns cljex.build
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out
   clojure.contrib.str-utils
   clojure.set
   cljex.config]
  [:import java.io.File])

(defn build-filename
  "build-filename takes a string like #'clojure.core/and, and turns it into _and.  The underscore protects us from creating '..' as a filename, which is illegal in over 40 countries."
  [input]
  (str "_" (second (re-find #"\/(.*)\Z" (str input)))))

(defn get-filename!
  [input]
  (second (re-find #".*\/(.*)\Z" (str input))))

(defn strip-dot-html
  [input]
  (second (re-find #".*\/_(.*)\.html\Z" (str input))))

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
          (file-str *core-docs* "/" filename)
          (print-markdown-doc s))))))

;; (defn apply-markdown
;;   "Turns all of the core-docs into html files"
;;   []
;;   (doseq [f (file-seq *core-docs*)]
;;     (sh *markdown-command* (str f)
;;         "-f" (str f ".html") "-x" "codehilite")))

(defn to-html
  "Takes the *out* from (print-markdown-doc #'doc) and applies pygments (with codehilite) to it.  Prints back to *out*."
  [doc]
  (sh *markdown-command* (str doc) "-x" "codehilite" (print-markdown-doc )))

(defn get-file-names-to-set [dir]
  (set (map #(.getName %) (file-seq dir))))

(defn compose-examples
  "Finds all of the files whose filenames match in the examples and core-docs directories.  Returns the list of items that match."
  []
  (let [examples (get-file-names-to-set *examples*)
        core-docs (get-file-names-to-set *core-docs*)]
    (intersection examples core-docs)))

(defn build-examples
  "Takes the composed-examples and returns a merged version (a str) of both the core-doc and example to compiled-docs."
  []
  (doseq [f (compose-examples)]
    (let [core-doc (read-lines (file-str *core-docs* "/" (str f)))
          example (read-lines (file-str *examples* "/" (str f)))
          compiled-doc (concat core-doc "\n" example)]
      (write-lines (file-str *compiled-docs* "/" (str f))
            compiled-doc))))

(defn template [body]
     (html
      [:html
       [:head (include-css "../css/pygments.css")]
       [:body body]]))

(defn convert-compiled
  []
  (doseq [f (rest (file-seq *compiled-docs*))]
    (let [filename (second (re-find #".*\/(.*)\Z" (str f)))]
      (spit (file-str *doc-output-dir* "/" (str filename) ".html")
            (template (str (sh *markdown-command* (str f) "-x" "codehilite")))))))

;; remember: (zipmap [:a :b :c] [1 2 3])