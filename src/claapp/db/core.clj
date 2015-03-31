(ns claapp.db.core
  (:require
    [yesql.core :refer [defqueries]]))

(defn spec []
  (let [db-uri (java.net.URI.
                 (or (System/getenv "DATABASE_URL")
                     "postgresql://localhost:5432/claapp"))
        host (.getHost db-uri)
        port (.getPort db-uri)
        path (.getPath db-uri)
        user-and-pass (if (nil? (.getUserInfo db-uri))
                        nil (clojure.string/split (.getUserInfo db-uri) #":"))]
    {:subprotocol "postgresql"
     :user        (get user-and-pass 0)
     :password    (get user-and-pass 1)
     :subname     (if (= -1 port)
                    (format "//%s%s" host path)
                    (format "//%s:%s%s" host port path))}))

(def db-spec (spec))

(defqueries "sql/queries.sql" {:connection db-spec})
