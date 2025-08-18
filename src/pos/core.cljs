(ns pos.core
  (:require ["@mui/joy/styles" :refer [extendTheme CssVarsProvider]]
            [devtools.core :as devtools]
            [pos.db]
            [pos.router :as router]
            [re-frame.core :as rf]
            [reagent.dom :as rdom]))

(defn install-devtools [] ; this is used to invert cljs console colors so it's actually readable in dark mode
  (let [{:keys [cljs-land-style]} (devtools/get-prefs)]
    (devtools/set-pref! :cljs-land-style (str "filter:invert(1);" cljs-land-style)))
  (devtools/install!))

(def joy-theme (extendTheme (clj->js {:colorSchemes {:light {:palette {:primary {:solidBg "#e23b3b"
                                                                                 :solidHoverBg "#f15959"
                                                                                 :solidActiveBg "#c82929ff"
                                                                                 :outlinedColor "#e23b3b"
                                                                                 :outlinedBorder "#e23b3b"
                                                                                 :outlinedHoverBg "#f15959"
                                                                                 :outlinedActiveBg "#c82929ff"}
                                                                       :secondary {:main "#fff"}}}}
                                      :fontFamily {:body "'Azeret Mono', monospace"}})))

(defn ^:dev/after-load mount-root []
  (let [router (router/create)]
    (rdom/render [:> CssVarsProvider {:theme joy-theme}
                  [router/current-view]]
                 (.getElementById js/document "app"))))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (install-devtools)
  (mount-root))