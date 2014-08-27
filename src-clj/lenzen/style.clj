(ns lenzen.style
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer [px]]))

(defstyles main
  [:body
   {:font-family ["Avenir Next" "sans-serif"]
    :font-size (px 16)
    :line-height 1.5
    :margin 0
    }]
  [:.sets
   {:padding (px 6) 
    :overflow {:x :auto}
    :white-space :nowrap
    :border {:bottom {:width (px 1)
                      :color :gray
                      :style :solid}}}
   [:ol
    {:margin 0
     :padding 0
     :display :inline-block}
    [:.set
     {:border {:width (px 1)
               :style :solid
               :color :transparent}
      :color :gray
      :cursor :pointer
      :display :inline-block
      :padding [[(px 3) (px 6)]]}
     [:&.active
      {:color :red}]
     [:&.selected
      {:border {:color :red}
       :cursor :default}]]]])
