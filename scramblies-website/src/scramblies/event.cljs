(ns scramblies.event
   (:require
    [re-frame.core :as rf]
    [clojure.pprint :refer [pprint]]
    [malli.core :as m]
    [malli.error :as me]
    [scramblies.db :as db]
    [scramblies.effect :as effect]))

;; -- Interceptors --------------------------------------------------------------

(defn check-and-throw
   "Throws an exception if `db` doesn't match the `schema`."
   [schema db]
   (when-not (m/validate schema db)
       (let [explained (m/explain schema db)]
          (throw (ex-info "Failure of db schema validation"
                          (-> explained (clj->js))
                          (-> explained (me/humanize) (pprint) (with-out-str)))))))

(def check-spec-interceptor
   (rf/after (partial check-and-throw db/Db)))

(def interceptors [check-spec-interceptor])

;; -- Event Handlers ----------------------------------------------------------

(rf/reg-event-db
 ::initialise-db
 interceptors
 (fn [db _]
   (merge db {:text "" :word "" :result :empty})))

(rf/reg-event-db
 ::text-change
 interceptors
 (fn [db [_ new-value]]
   (assoc db :text new-value)))

(rf/reg-event-db
 ::word-change
 interceptors
 (fn [db [_ new-value]]
   (assoc db :word new-value)))

(rf/reg-event-fx
 ::button-clicked
 interceptors
 (fn [cofx _]
   {::effect/scramblies-check {:text (get-in cofx [:db :text])
                               :word (get-in cofx [:db :word])}}))

(rf/reg-event-db
 ::result-change
 interceptors
 (fn [db [_ new-value]]
   (assoc db :result new-value)))
