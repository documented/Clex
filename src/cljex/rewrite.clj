(ns cljex.main
  (:use ('compojure)
        ('clojure.contrib.str-utils)
        ('clojure.contrib.duck-streams)
        ('clojure.contrib.shell-out)
        ('clojure.set)))

                                        ;==============================
                                        ; Configuration Options
                                        ;==============================
(def *markdown-command* "/usr/local/bin/markdown")
(def *project-root* "/Users/defn/git/cljex/")
(def *core-docs* (str *project-root* "src/docs/core-docs/"))
(def *examples-dir* (str *project-root* "src/docs/examples/"))

                                        ;==============================
                                        ; Namespace Functions
                                        ;==============================
(defn discover-namespace
  "Gets all of the vals in the map produced by (ns-publics '"
  [ns]
  (vals (ns-publics ns)))

(defmacro discover-namespace-m [ns] `(discover-namespace '~ns))

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
     (str "> *Macro*"))
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
(defn get-doc [doc]
  (concat
   (str (sh *markdown-command* doc "-x" "codehilite"))
   (str (sh *markdown-command* example "-x" "codehilite"))))

(create-docs 'clojure.core)
(create-empty-examples)