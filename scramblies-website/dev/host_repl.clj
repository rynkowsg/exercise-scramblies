(ns host-repl
  (:require
   [shadow.cljs.devtools.api :as shadow]))

(println "ns host-repl loaded")

(defn cljs-dev []
  (println "opening cljs repl...")
  (shadow/repl :client))

(comment
 (shadow/compile :client)
 (shadow/repl :client)
 (cljs-dev))
