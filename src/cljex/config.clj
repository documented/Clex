(ns cljex.config
  [:use compojure,
   clojure.contrib.duck-streams
   clojure.contrib.str-utils]
  [:import java.io.File])

;;; Global config variables ;;;;

(def *project-root*
     (-> "user.dir" System/getProperty java.io.File. .getParentFile .getParentFile))

(def *public-dir* (file-str *project-root* "/src/public/"))

(def *rel-project-root*
     (-> "user.dir" System/getProperty java.io.File. .getParentFile .getParentFile))

(def *markdown-command* "/usr/local/bin/markdown")