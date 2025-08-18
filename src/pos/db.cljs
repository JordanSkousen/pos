(ns pos.db
  (:require [re-frame.core :refer [reg-event-fx]]))

(defn initial-db []
  {:ui {:current-route :loading}})

(reg-event-fx
 :initialize-db
 (fn [_ _]
   {:db (initial-db)}))