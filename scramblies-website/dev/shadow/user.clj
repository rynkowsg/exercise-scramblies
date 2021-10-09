(ns shadow.user
  (:require
   [shadow.cljs.devtools.api :as shadow-api]))

(println "ns shadow/user.cljs loaded")

(defn cljs-repl []
  (println "opening cljs repl...")
  (shadow-api/repl :client))
