(defproject cljex "1.0.0"
  :description "cljex provides examples for clojure's existing core api documentation"
  :repositories [["scala tools" "http://scala-tools.org/repo-releases"]]
  :dependencies [[org.clojure/clojure "1.1.0-master-SNAPSHOT"]
                 [org.clojure/clojure-contrib "1.0-SNAPSHOT"]
                 [org.clojars.liebke/compojure "0.3.1"]
                 [autodoc "0.3.0-SNAPSHOT"]
                 [org.markdownj/markdownj "0.3.0-1.0.2b4"]]
  :dev-dependencies [[swank-clojure "1.1.0-SNAPSHOT"]]
  :main "cljex.core")