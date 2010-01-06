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
(defn discover-namespace [nspace]
  "Devin"
  (vals (ns-publics nspace)))

(defn print-markdown-doc
  "This is a modified version of print-doc which outputs the documentation in markdown format."
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

;; (defn compose-markdown-core-doc
;;   [v]
;;   (let [name    (str (:name (meta v)))
;;         nsname  (str (ns-name (:ns (meta v))) "/" (:name (meta v)))
;;         arglist (str (:arglists (meta v)))
;;         macro   (when (:macro (meta v))
;;                   (html [:b "Macro"]))
;;         docstring (str (:doc (meta v)))]
;;     (html [:h3 name]
;;           [:blockquote [:p [:em nsname]]]
;;           [:blockquote [:p ]])))


(defn create-core-docs
  [nspace]
  (doseq [f (discover-namespace nspace)]
    (let [filename (str "_" (:name (meta f)))]
      (write-lines (file-str *core-docs* filename)
                   (print-markdown-doc f)))))

                                        ;==============================
                                        ; Example Setup
                                        ;==============================

(defn get-file-names-to-set [dir]
  (set (map #(.getName %) (file-seq (java.io.File. dir)))))

(def examples-which-exist
     (let [core-docs (get-file-names-to-set *core-docs*)
           examples  (get-file-names-to-set *examples-dir*)]
       (intersection core-docs examples)))

;; (def examples-to-create
;;      (let [core-docs (get-file-names-to-set *core-docs*)
;;            examples (get-file-names-to-set *examples-dir*)]
;;        (difference core-docs examples)))

;; (defn create-empty-examples
;;   []
;;   (let [empty-examples (examples-to-create)]
;;     (doseq [example empty-examples]
;;       (write-lines (file-str *examples-dir* example)))))

                                        ;==============================
                                        ; Communicate with the server
                                        ;==============================

(defn get-doc [doc]
  (concat
   (str (sh *markdown-command* doc "-x" "codehilite"))
   (str (sh *markdown-command* example "-x" "codehilite"))))

(create-docs 'clojure.core)
(create-empty-examples)