(ns cljex.config)

                                        ;==============================
                                        ; Configuration Options
                                        ;==============================
(def *markdown-command* "/usr/local/bin/markdown")
(def *project-root* (System/getProperty "user.dir"))
(def *public-dir* (str *project-root* "/src/cljex/public/"))
(def *core-docs* (str *project-root* "/src/docs/core-docs/"))
(def *examples-dir* (str *project-root* "/src/docs/examples/"))

(def *site-url* "http://localhost:8080")
(def *site-title* "cljex")