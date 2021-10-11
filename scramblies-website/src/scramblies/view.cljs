(ns scramblies.view
  (:require
   [reagent.core :as ra]
   [re-frame.core :as rf]
   [scramblies.sub :as sub]
   [scramblies.event :as event]))

(defn input-field []
  (let [val (ra/atom "")]
    [:input {:class      "edit"
             :type       "text"
             :auto-focus true
             :value      @val
             :on-change  #(reset! val (-> % .-target .-value))
             :on-key-down #(case (.-which %) nil)}]))

(defn scramblies-app
  []
  [:<>
   [:p "Scramblies check"]
   [:div [:input {:id "word"
                  :class "edit"
                  :type "text"
                  :on-change #(rf/dispatch [::event/text-change (-> % .-target .-value)])
                  :value @(rf/subscribe [::sub/text])}]]
   [:div [:input {:id "text"
                  :class "edit"
                  :type "text"
                  :on-change #(rf/dispatch [::event/word-change (-> % .-target .-value)])
                  :value @(rf/subscribe [::sub/word])}]]
   [:button {:type "submit"
             :on-click #(rf/dispatch [::event/button-clicked])} "CHECK"]
   [:p (str "Result: " @(rf/subscribe [::sub/result]))]])
