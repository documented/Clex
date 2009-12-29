(ns cljex.config
  [:use compojure,
   clojure.contrib.duck-streams
   clojure.contrib.str-utils]
  [:import java.io.File])

;;; Global config variables ;;;;

;;; PATH ;;;
(def *project-root* (java.io.File. "/home/defn/git/cljex/"))

(def *core-docs* (file-str *project-root* "/src/docs/core-docs/"))

(def *public-dir* (file-str *project-root* "/src/public/"))

(def *examples-dir* (file-str *project-root* "/src/docs/examples/"))

(def *doc-output-dir* (file-str *project-root* "/src/public/docs/"))

;;; OTHER ;;;
(def *markdown-command* "/usr/local/bin/markdown")

;;; SAVED FOR LATER -- NOT IN USE ;;;
;; (def *project-root*
;;      (-> "user.dir" System/getProperty java.io.File. .getParentFile .getParentFile))
;; (def *rel-project-root*
;;      (-> "user.dir" System/getProperty java.io.File. .getParentFile .getParentFile))