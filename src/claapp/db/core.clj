(ns claapp.db.core
  (:require
    [yesql.core :refer [defqueries]]))

(def db-spec
  {:subprotocol "postgresql"
   :subname "//localhost/claapp"
   :user "db_user_name_here"
   :password "db_user_password_here"})

(defqueries "sql/queries.sql" {:connection db-spec})
