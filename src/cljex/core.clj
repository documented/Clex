(ns cljex.core
  (:gen-class)
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.shell-out,
   clojure.contrib.str-utils,]
  [:import java.io.File])

;; (defn print-ns-docs-to-file
;;   "Prints the documentation for a given nspace to a file-name which is located on path-to-file."
;;   [nspace, path-to-file, file-name]
;;   (with-out-writer
;;     (file-str path-to-file file-name)
;;     (doseq [s (sort-ns nspace)]
;;       (print-doc s))))

(def *project-root* "/Users/defn/git/cljex")
(def *public-dir* (file-str *project-root* "/src/public/"))

(defn build-filename [input]
  (str "_" (second (re-find #"\/(.*)\Z" (str input)))))

(defn ns-vals
  "Sorts the vals from the map returned by ns-publics for a given nspace into alphabetical order."
  [nspace]
  (vals (ns-publics nspace)))

(defn create-core-docs []
  (let [docs (ns-vals 'clojure.core)]
    (doseq [s docs]
      (let [filename (build-filename s)]
        (with-out-writer
          (file-str *project-root* "/src/core-docs/" filename)
          (print-doc s))))))

(defn docs-index [path-to-files]
  "Creates links to all of the docs located in public/docs/*"
  (html (map #(link-to %)
             (map #(str %) (file-seq
                            (java.io.File. path-to-files))))))