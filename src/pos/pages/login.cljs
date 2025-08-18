(ns pos.pages.login
  (:require ["@mui/joy/Button" :default Button]
            [reagent.core :as r]))

(defn page []
  [:<>
   [:div.login-page
    [:div
     [:div.login-logo
      [:img {:src "/img/logo500.png"
             :alt "POS logo"}]
      [:h1 "Pile of Sheets"]]
     [:> Button {:startDecorator (r/as-element [:img.google-logo {:src "/img/googleLogo.svg"
                                                                  :alt "Google icon"}])
                 :sx {:width "100%"}}
      "Sign in with Google"]]
    [:div.login-bottom
     [:h2 "Revolting against Big Sheets since 2019"]
     [:div.login-bottom-sub
      "Learn more" [:span.material-symbols-sharp "arrow_downward"]]]]
   [:div.story
    [:h1 "Pile of Sheets (POS) is a tool for keeping track of your working hours."]
    [:div.story-alt-bg
     [:h2 "But more importantly, it's " 
      [:b "free"] 
      " and " 
      [:b "open-source"] 
      ". And I refuse to change that."]]
    [:h2 "Just a run down of everything that's free:"]
    [:ul
     [:li "Multiple job support"]
     [:li "Unlimited sheet history"]
     [:li "Report generator" [:span.coming-soon "COMING SOON"]]
     [:li "API access" [:span.coming-soon "COMING SOON"]]]
    [:h2 "The story"]
    [:div "I've been an independent contractor for almost 10 years now and I've always needed timekeeping software. I used this great free program, but when it was acquired and bastardized into a paid product in 2018 by a money-grubbing whore, I decided enough was enough and created Pile of Sheets. After using it myself for a few years I thought it was high time I liberated the public from the whore's hands."]
    [:h3 "So what are you waiting for? Scroll up and sign in."]]])