(ns pos.router
  (:require [pos.pages.error-404 :as error-404]
            [pos.pages.home :as home]
            [pos.pages.login :as login] 
            [re-frame.core :refer [reg-sub reg-fx reg-event-fx reg-event-db dispatch-sync subscribe]]
            [reitit.coercion.spec :as rss]
            [reitit.frontend :as rf]
            [reitit.frontend.controllers :as rfc]
            [reitit.frontend.easy :as rfe]))

(def routes
  ["/"
   [""
    {:name ::home
     :view home/page}]
   ["login"
    {:name ::login
     :view login/page}]])

(defn init-routes! [router]
  (rfe/start! router 
              #(dispatch-sync [::navigated %]) 
              {:use-fragment true}))

(defn create []
  (let [router (rf/router routes {:data {:coercion rss/coercion}})]
    (init-routes! router)
    (dispatch-sync [::router-loaded])
    router))

(defn current-view []
  (let [current-route @(subscribe [::current-route])]
    (when (not= current-route :loading)
      [(-> current-route
           :data
           :view
           (or error-404/page))])))

(reg-sub
 ::current-route
 (fn [db]
   (get-in db [:ui :current-route])))

(reg-event-db
 ::router-loaded
 (fn [db]
   (when (= :loading (get-in db [:ui :current-route]))
     (update db :ui dissoc :current-route))))

(reg-event-fx
 ::navigated
 (fn [{:keys [db]} [_ new-match]]
   (let [history (get-in db [:ui :history] #{})
         old-match (get-in db [:ui :current-route])
         controllers (rfc/apply-controllers (:controllers old-match) new-match)]
     {:db (-> db
              (assoc-in [:ui :current-route] (assoc new-match :controllers controllers))
              (assoc-in [:ui :history] (conj history (-> new-match :data :name))))
      :fx [(when (not= (:path old-match) (:path new-match))
             [::scroll-to-top])]})))

(reg-fx
 ::scroll-to-top
 (fn []
   (.scrollTo js/window #js {:top 0 :left 0 :behavior "instant"})))

(reg-event-fx
 :router/goto-page
 (fn [_ [_ & route]]
   {::push-state route}))

(reg-fx
 ::push-state
 (fn [route]
   (apply rfe/push-state route)))