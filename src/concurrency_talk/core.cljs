(ns concurrency-talk.core
    (:require [goog.events :as events]
              [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce current-slide (atom 0))

(defn next-slide [] (swap! current-slide inc))
(defn previous-slide [] (swap! current-slide dec))

(def slide-deck
  {:introduction
   [:div
    [:h1 "Visualising Concurrency in Clojure"]
    [:h3 "replace example blue svg with clojure logos?"]
    [:img.diagram {:src "ConcurrencyBegin.svg"}]]
   :survey
   [:div
    [:h1 "Survey"]
    [:h3 "Get a feel for how many people in the room know core.async and are familiar with concurrency"]
    [:img.diagram {:src "ConcurrencyBegin.svg"}]]
   :what-is
   [:div
    [:h1 "What is Concurrency?"]
    [:h3 "Go over some brief definitions, talk about Tony Hoare's CSP paper if it's light enough"]
    [:h3 "Concurrency is not parallelism by Rob Pike"]
    [:img.diagram {:src "ConcurrencyBegin.svg"}]]
   :building-blocks
   [:div
    [:h1 "Building Blocks"]
    [:h3 "Then show the syntax / Lego bricks that clojurians can use to create something async"]
    [:img.diagram {:src "ConcurrencyBegin.svg"}]]
   :synchronous-toy
   [:div
    [:h1 "Synchronous Toy"]
    [:h3 "Take a synchronous toy example and amend it to be asynchronous"]]
   :visual-map
   [:div
    [:h1 "Visual Map"]
    [:h3 "Produce a visual map of how each part interacts (using react/quil or SVG)"]]
   :lispless
   [:div
    [:h1 "Asynchronous Go"]
    [:h3 "Also produce an equivalent async toy program in go and laugh at it's inferior lispless syntax"]]})

(defn interface [app]
  [:div
  [:div.content (nth (vals slide-deck) @current-slide)]
  [:div.controls
   [:a.control {:on-click previous-slide} "Previous Slide"]
   [:a.control {:on-click next-slide} "Next Slide"]]])

(defn- keydown [event]
  (let [keycode (.-keyCode event)]
    (comp
      (cond (= 37 keycode) (previous-slide) ;; left
            (= 39 keycode) (next-slide)) ;; right
      (println (str "pressed key " keycode)))))

(reagent/render-component
  [interface]
  (. js/document (getElementById "app")))

(defonce initiate (events/listen js/document "keydown" keydown))
