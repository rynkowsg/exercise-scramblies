(ns scramblies.effect
  (:require
   [cljs.core.async :refer [go <!]]
   [cljs-http.client :as http]
   [re-frame.core :as rf]
   [scramblies.event :as-alias event]))

(rf/reg-fx
 ::scramblies-check
 (fn [{:keys [text word]}]
   ;; there is no validation for input, anything is allowed, including empty strings
   (go
    (let [res (<! (http/get "http://localhost:3000/api/scramble/check" {:query-params {:word word :text text}}))]
      (rf/dispatch [::event/result-change (some-> res :body :result)])))))
