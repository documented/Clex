(ns cljex.build
  (:use clojure.contrib.duck-streams
        clojure.contrib.str-utils
        cljex.config))

(defn discover-namespace
  "Gets all of the vals in the map produced by (ns-publics 'ns)"
  [ns]
  (vals (ns-publics ns)))

(defn print-markdown-doc
  "Prints documentation in markdown format."
  [v]
  [(str "###" (:name (meta v)) "###\n")
   (str "> *" (ns-name (:ns (meta v))) "/" (:name (meta v)) "*")
   (str "> ")
   (str ">     :::clojure")
   (str ">     " (:arglists (meta v)))
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
