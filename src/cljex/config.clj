(ns cljex.config
  [:use compojure,
   clojure.contrib.duck-streams
   clojure.contrib.str-utils]
  [:import java.io.File])

;;; Global config variables ;;;;

;;; PATH ;;;
(def *path* (file-str (java.io.File. (System/getProperty "user.dir")) "/git" "cljex"))
(def *home* (java.io.File. (System/getProperty "user.dir")))

(def *project-root* (file-str *home* "/git/cljex/"))

(def *core-docs* (file-str *project-root* "/src/docs/core-docs/"))

(def *public-dir* (file-str *project-root* "/src/public/"))

(def *examples* (file-str *project-root* "/src/docs/examples/"))

(def *compiled-docs* (file-str *project-root* "/src/docs/compiled/"))

(def *doc-output-dir* (file-str *project-root* "/src/public/docs/"))

;;; OTHER ;;;
(def *markdown-command* "/usr/local/bin/markdown")

;;; SAVED FOR LATER -- NOT IN USE ;;;
;; (def *project-root*
;;      (-> "user.dir" System/getProperty java.io.File. .getParentFile .getParentFile))
;; (def *rel-project-root*
;;      (-> "user.dir" System/getProperty java.io.File. .getParentFile .getParentFile))