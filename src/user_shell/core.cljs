(ns ^:figwheel-hooks user-shell.core
  (:require
   [goog.dom :as gdom]
   [cognito-user-pool]
   [cognito-user-attribute]
   [reagent.core :as reagent :refer [atom]]))

(println "This text is printed from src/user_shell/core.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))


;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "H!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn handle-signup-result [err result]
  (if err
    (prn "Error" err)
    (prn "Success" result)))

(defn config-auth []
  (let [cognito-cfg #js {"UserPoolId" "us-east-1_20VyIaRDx"
                         "ClientId" "9bue3q4ng0arnaf73d0vg4lcd"}
        pool (cognito-user-pool. cognito-cfg)
        attribute-list #js []
        data-email  (cognito-user-attribute. #js {"Name" "email" "Value" "bulavitchi@gmail.com"})
        data-phone  (cognito-user-attribute. #js {"Name" "phone_number" "Value" "+15555555555"})]
      (.signUp pool "zozo" "Zumba135$" #js [data-email data-phone] nil handle-signup-result)))



(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this in src/user_shell/core.cljs and watch it change!"]
   [:div [:button {:on-click #(config-auth)} "Sign up"]]])

(defn mount [el]
  (reagent/render-component [hello-world] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  ;(config-auth)
  (mount-app-element))
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
