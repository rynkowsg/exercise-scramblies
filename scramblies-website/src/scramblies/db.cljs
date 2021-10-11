(ns scramblies.db
  (:require
   [malli.core :as m]))

(def Db
  (m/schema [:map
             [:text string?]
             [:word string?]
             [:result [:or boolean? [:= :empty]]]]))
