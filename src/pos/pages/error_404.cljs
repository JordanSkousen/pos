(ns pos.pages.error-404)

(defn page []
  [:div {:style {:display :flex
                 :flex-direction :column
                 :align-items :center
                 :justify-content :center
                 :width "100vw"
                 :height "100vh"}}
   [:div {:style {:font-size "30vw"}}
    "404"]
   [:a {:href "/"
        :style {:font-size "15vw"}}
    "Go Home"]])