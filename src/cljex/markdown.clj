(ns cljex.markdown
  [:use compojure,
   clojure.contrib.duck-streams,
   clojure.contrib.str-utils,
   clojure.contrib.shell-out])

;; pygmentize -S manni -f html > pygments.css