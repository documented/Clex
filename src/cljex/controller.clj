(ns cljex.controller
  [:use compojure])

(defn sort-ns
  "Sorts the keys from the map returned by ns-publics for a given nspace into alphabetical order."
  [nspace]
  (vals (ns-publics nspace)))

(defn print-ns-docs-to-file
 "Prints the documentation for a given nspace to a file-name which is located on path."
 [nspace, path-to-file, file-name]
 (with-out-writer
   (file-str path-to-file file-name)
   (doseq [s (sort-ns nspace)]
     (print-doc s))))

(defn print-ns-doc-to-file
  "Prints the documentation for a single form to a file of the same name located at file-path."
  [form]
  (let []
    (with-out-writer
      ())))

(defn ns-doc-to-file
  [ns-doc]
  )

(map #(ns-doc-to-file %) )

(doseq [funcs (sort-by (comp :name meta)
                       (vals (ns-interns 'clojure.core)))]
  (prn (:doc (meta funcs))))
;; was 'clojure.main
(doseq [funcs (sort-by (comp :name meta)
                       (vals (ns-interns 'clojure.core)))]
  (prn (:name (meta funcs))))