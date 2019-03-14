(ns aws.cognito
  (:require [cognito-user-pool]
            [cognito-user-attribute]))

(def config-attr-map {:user-pool-id "UserPoolId"
                      :client-id "ClientId"})

(def user-attr-map {:email "email"
                    :phone-number "phone_number"})

(defn to-js-config
  [cognito-cfg]
  (->> cognito-cfg
    (map (fn  [[key value]] [(get config-attr-map key) value]))
    (into {})))

(defn user-pool [cognito-cfg])

(defn sign-up [pool usr pwd])
