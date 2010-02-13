(ns cljex.build
<<<<<<< HEAD:src/cljex/build.clj
  (:use clojure.contrib.duck-streams
        clojure.contrib.str-utils
        (cljex config)))
=======
  (:use clojure.contrib.str-utils
        clojure.contrib.duck-streams
        cljex.config))
>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/build.clj

(defn discover-namespace
  "Gets all of the vals in the map produced by (ns-publics 'ns)"
  [ns]
  (vals (ns-publics ns)))

(defn print-markdown-doc
<<<<<<< HEAD:src/cljex/build.clj
  "Prints documentation in markdown format."
=======
>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/build.clj
  [v]
  [(str "###" (:name (meta v)) "###\n")
   (str "> *" (ns-name (:ns (meta v))) "/" (:name (meta v)) "*")
   (str "> ")
<<<<<<< HEAD:src/cljex/build.clj
   (str ">     :::clojure")
   (str ">     " (:arglists (meta v)) "")
=======
   (str ">     " (:arglists (meta v)))
>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/build.clj
   (str "> ")
   (when (:macro (meta v))
     (str "> *Macro*\n"))
   (str ">  " (re-gsub #"\n" "\n>" (str (:doc (meta v)))))])

(defn create-docs
  "Create the documentation for [nspace] in the *core-docs* directory."
<<<<<<< HEAD:src/cljex/build.clj
  [nspace]
  (doseq [f (discover-namespace nspace)]
    (let [filename (str "_" (:name (meta f)))]
      (write-lines (file-str *core-docs* filename)
                   (print-markdown-doc f)))))

=======
  [ns]
  (let [ns-str (str ns)]
    (doseq [f (discover-namespace ns)]
      (let [filename (str "_" (:name (meta f)))]
        (write-lines (file-str *core-docs* filename)
                     (print-markdown-doc f))))))
>>>>>>> ef15a290624b96eaec224dd3dbe69581c2bc2060:src/cljex/build.clj
