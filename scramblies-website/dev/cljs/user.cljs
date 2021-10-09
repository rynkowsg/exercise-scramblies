(ns cljs.user)

(println "ns cljs/user.cljs loaded")

(defn dev []
  (enable-console-print!)
  (println "Console print enabled."))
