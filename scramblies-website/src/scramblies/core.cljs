(ns scramblies.core
  (:require
   [reagent.dom]
   [re-frame.core :as rf]
   [scramblies.effect]
   [scramblies.event :as event]
   [scramblies.sub]
   [scramblies.view]))

;; -- Entry Point -------------------------------------------------------------

(defn render []
  (reagent.dom/render [scramblies.view/scramblies-app]
                      (.getElementById js/document "app")))

(defn ^:dev/after-load clear-cache-and-render! []
  (rf/clear-subscription-cache!)
  (render))

(defn ^:export main
  []
  (rf/dispatch-sync [::event/initialise-db])
  (render))
