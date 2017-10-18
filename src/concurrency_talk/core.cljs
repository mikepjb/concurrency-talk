(ns concurrency-talk.core
    (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "This text is printed from src/concurrency-talk/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:current-slide 1}))

(defn introduction [app]
  [:div
  [:h1 "Visualising Concurrency in Clojure"]
  [:img.diagram {:src "ConcurrencyBegin.svg"}]
  ])

(defn interface [app]
  [:div
  [:div.content [introduction]]
  [:div.control [:a {:on-click #(println "next slide please!")} "Next Slide"]]])

(reagent/render-component
  [interface]
  (. js/document (getElementById "app")))

;; Writing a macro
;; defslide takes 3 args: name, target-div & body
;; name should be a.. name - not a symbol/keyword e.g defslide intro not defslide :intro
;; target-div is fine as a string
;; body will be hiccup of the html to fill the target-div with
