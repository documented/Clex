(ns cljex.db
  (:use (clojure.contrib def str-utils sql)
        (cljex util markdown)))

(let [db-host "localhost"
      db-port 5885
      db-name cljex]
  (def db (:classname "com.mysql.jdbc.Driver" ; must be in classpath
           :subprotocol "mysql"
           :subname (str "//" db-host ":" db-port "/" db-name))))