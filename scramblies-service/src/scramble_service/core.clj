(ns scramble-service.core
  (:require
   [org.httpkit.server :as httpkit]
   [scramble-service.api :as api]
   [taoensso.timbre :as timbre]))

(defn -main [& _args]
  (let [config {:port 3000}]
    (timbre/log :info :starting-http-server config)
    (httpkit/run-server (api/app) config)))
