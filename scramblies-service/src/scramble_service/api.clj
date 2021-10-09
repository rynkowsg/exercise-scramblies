(ns scramble-service.api
  (:require
   [muuntaja.core :as muuntaja]
   [reitit.coercion.malli :as rc-malli]
   [reitit.ring :as rr]
   [reitit.ring.coercion :as rrc]
   [reitit.ring.middleware.muuntaja :as rrm-muuntaja]
   [reitit.ring.middleware.parameters :as rrm-parameters]
   [scramble-service.domain :as domain]))

(def routes
  ["/api"
   ["/scramble" {:coercion rc-malli/coercion}
    ["/check" {:get {:name       ::scramble
                     :parameters {:query [:map [:text string?] [:word string?]]}
                     :responses  {200 {:body [:map [:result boolean?]]}}
                     :handler    (fn [{{{:keys [text word]} :query} :parameters}]
                                   {:status 200 :body {:result (domain/scramble? text word)}})}}]]])

(defn app []
  (rr/ring-handler
   (rr/router routes {:data {:muuntaja   muuntaja/instance
                             :middleware [rrm-parameters/parameters-middleware
                                          rrm-muuntaja/format-middleware
                                          rrc/coerce-exceptions-middleware
                                          rrc/coerce-request-middleware
                                          rrc/coerce-response-middleware]}})))

(comment
 (-> ((app) {:headers        {"content-type" "application/edn" "accept" "application/edn"}
             :request-method :get
             :uri            "/api/scramble/check"
             :query-params   {:text "abcd", :word "abc"}})
     :body
     (slurp)))
