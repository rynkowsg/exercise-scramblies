(ns scramblies.sub
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::text
 (fn [db _]
   (:text db)))

(rf/reg-sub
 ::word
 (fn [db _]
   (:word db)))

(rf/reg-sub
 ::result
 (fn [{:keys [result]} _]
   (if (not= :empty result)
     result
     "")))
