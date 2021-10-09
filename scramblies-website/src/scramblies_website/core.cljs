(ns scramblies-website.core
  (:import
   [goog History]
   [goog.history EventType])
  (:require
   [goog.events :as events]
   [reagent.dom]
   [re-frame.core :as rf]
   [secretary.core :as secretary]
   [scramblies-website.effects]
   [scramblies-website.events]
   [scramblies-website.subs]
   [scramblies-website.views])
  (:require-macros
   [secretary.core :refer [defroute]]))

;; -- Routes and History ------------------------------------------------------

;; Although we use the secretary library below, that's mostly a historical
;; accident. You might also consider using:
;;   - https://github.com/DomKM/silk
;;   - https://github.com/juxt/bidi
;; We don't have a strong opinion.
;;
(defroute "/" [] (rf/dispatch [:set-showing :all]))
(defroute "/:filter" [filter] (rf/dispatch [:set-showing (keyword filter)]))

(defonce history
         (doto (History.)
           (events/listen EventType.NAVIGATE
                          (fn [^js/goog.History.Event event] (secretary/dispatch! (.-token event))))
           (.setEnabled true)))

;; -- Entry Point -------------------------------------------------------------

(defn render []
  (reagent.dom/render [scramblies-website.views/scramblies-app]
                      (.getElementById js/document "app")))

(defn ^:dev/after-load clear-cache-and-render! []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the Reframe subscription cache.
  (rf/clear-subscription-cache!)
  (render))

(defn ^:dev/before-load-async stop [done]
  (js/console.log "stop")
  (js/setTimeout
   (fn []
     (js/console.log "stop complete")
     (done)))

  (defn ^:dev/after-load-async start [done]
    (js/console.log "start")
    (js/setTimeout
     (fn [])
     (js/console.log "start complete")
     (done))))

(defn ^:export main
  []
  (rf/dispatch-sync [:initialise-db])
  (render))
