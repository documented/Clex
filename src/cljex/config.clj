(ns cljex.config)

(def *project-root*
     (-> "user.dir" System/getProperty java.io.File. .getParentFile .getParentFile))