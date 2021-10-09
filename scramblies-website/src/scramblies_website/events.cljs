(ns scramblies-website.events
  (:require
   [re-frame.core :as rf]))

(rf/reg-event-db
 :initialise-db
 (fn [_ _]
   {:text "" :word "" :result ""}))

(rf/reg-event-db
 :text-change
 (fn [db [_ new-value]]
   (assoc db :text new-value)))

(rf/reg-event-db
 :word-change
 (fn [db [_ new-value]]
   (assoc db :word new-value)))

(rf/reg-event-fx
 :button-clicked
 (fn [cofx _event]
   {:scramblies-check {:text (get-in cofx [:db :text])
                       :word (get-in cofx [:db :word])}}))

(rf/reg-event-db
 :result-change
 (fn [db [_ new-value]]
   (assoc db :result new-value)))
