(ns scramblies-website.effects
  (:require
   [cljs.core.async :refer [go <!]]
   [cljs-http.client :as http]
   [re-frame.core :as rf]))

(rf/reg-fx
 :scramblies-check
 (fn [{:keys [text word]}]
   (if (or (empty? text) (empty? word))
     (js/alert "both text and word field should not be empty")
     (go
      (let [res (<! (http/get "http://localhost:3000/api/scramble/check" {:query-params {:word word :text text}}))]
        (prn res)
        (rf/dispatch [:result-change (some-> res :body :result)]))))))
