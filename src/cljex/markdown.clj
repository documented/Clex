(ns cljex.markdown)

(defn markdown [doc]
  (let [md (new com.petebevin.markdown.MarkdownProcessor)]
    (str (.markdown md (read)))
    (.markdown md ())))