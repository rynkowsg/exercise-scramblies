(ns scramblies-website.views
  (:require
   [reagent.core :as ra]
   [re-frame.core :as rf]))

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
                  :value @(rf/subscribe [:text])
                  :on-change #(rf/dispatch [:text-change (-> % .-target .-value)])}]]
   [:div [:input {:id "text"
                  :class "edit"
                  :type "text"
                  :value @(rf/subscribe [:word])
                  :on-change #(rf/dispatch [:word-change (-> % .-target .-value)])}]]
   [:button {:type "submit"
             :on-click #(rf/dispatch [:button-clicked])} "CHECK"]
   [:p (str "Result: " @(rf/subscribe [:result]))]])
