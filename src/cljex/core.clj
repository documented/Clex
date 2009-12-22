(ns cljex.core
  (:gen-class)
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out,
   clojure.contrib.str-utils,]
  [:import java.io.File])

(defn sort-ns
  "Sorts the vals from the map returned by ns-publics for a given nspace into alphabetical order."
  [nspace]
  (vals (ns-publics nspace)))

(defn print-ns-docs-to-file
  "Prints the documentation for a given nspace to a file-name which is located on path-to-file."
  [nspace, path-to-file, file-name]
  (with-out-writer
    (file-str path-to-file file-name)
    (doseq [s (sort-ns nspace)]
      (print-doc s))))

(defn build-filename [input]
  (str "_" (second (re-find #"\/(.*)\Z" (str input)))))

(defn print-core-docs-to-files
  [path-to-files]
  (let [docs (sort-ns 'clojure.core)]
    (doseq [s docs]
      (let [filename (build-filename s)]
        (with-out-writer (file-str path-to-files filename)
          (print-doc s))))))

(defn docs-index []
  "Creates links to all of the docs located in public/docs/*"
  (html (map #(link-to %)
             (map #(str %) (file-seq
                            (java.io.File. "/Users/defn/git/cljex/src/public/docs"))))))