(ns scramblies-website.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 :text
 (fn [db _]
   (:text db)))

(rf/reg-sub
 :word
 (fn [db _]
   (:word db)))

(rf/reg-sub
 :result
 (fn [db _]
   (:result db)))
