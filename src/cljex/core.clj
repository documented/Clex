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

(defn docs-index []
  "Creates links to all of the docs located in public/docs/*"
  (html (map #(link-to %)
             (map #(str %) (file-seq
                            (java.io.File. "/Users/defn/git/cljex/src/public/docs"))))))

(defn remove-leading-whitespace 
  "Find out what the minimum leading whitespace is for a doc block and remove it.
We do this because lots of people indent their doc blocks to the indentation of the 
string, which looks nasty when you display it."
  [s]
  (when s
    (let [lines (.split s "\\n") 
          prefix-lens (map #(count (re-find #"^ *" %)) 
                           (filter #(not (= 0 (count %))) 
                                   (next lines)))
          min-prefix (when (seq prefix-lens) (apply min prefix-lens))
          regex (when min-prefix (apply str "^" (repeat min-prefix " ")))]
      (if regex
        (apply str (interpose "\n" (map #(.replaceAll % regex "") lines)))
        s))))

(defn var-type 
  "Determing the type (var, function, macro) of a var from the metadata and
return it as a string."
  [v]
  (cond (:macro ^v) "macro"
        (= (:tag ^v) clojure.lang.MultiFn) "multimethod"
        (:arglists ^v) "function"
        :else "var"))

(defn vars-for-ns [ns]
  (for [v (sort-by (comp :name meta) (vals (ns-interns ns)))
        :when (and (or (:wiki-doc ^v) (:doc ^v)) (not (:skip-wiki ^v)) (not (:private ^v)))] v))