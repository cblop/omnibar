(ns omnibar.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]))


;; -------------------------
;; svg
(defn circle [[x y]]
  [:circle {:cx (if (even? y)
                  (+ 60 (* x 48))
                  (+ 35 (* x 48)))
            :cy (+ 30 (* y 48))
            :class "circle"
            :r "19"
;;            :stroke "#00f"
;;            :stroke-width "2"
;;            :fill "#00eb00"
            }])

(defn hexagon [[x y]]
  (let [hx (if (even? y)
            (+ 90 (* x 50))
            (+ 65 (* x 50)))            
        hy   (+ 40 (* y 45))
        size 0.05
        trans (str "translate(" hx " " hy ") scale(" size ")")]
[:g {:id "layer1-6" :transform trans}
 [:path {:id "path3344"
  :d "m-760 532.36-381.05-220v-440l381.05-220 381.05 220 0.00001 440z"
  :fill-rule "evenodd"
  :stroke "#00f"
  :stroke-width "50"
         :fill "#00eb00"
         }]]))

(defn piano-key-b-shape
  "As an example, this is the shape used by middle c"
  [[x y] flip]
 [:g {:id "key-b-shape" :transform (str "scale(" flip ",1)")}
  [:path {:id "key-b-shape-path"
          :style {
                  :fill-rule "evenodd"
                  :stroke "#000"
                  :stroke-width "1px"
                  :fill "#fff"}
          :d (str "m" x " " y "v51l29 17 29-17v-35.858l-29-17.142-0.004-52.844-28.797 0.0101z")}]])


(defn defs-piano-key-b-shape
  "define a svg piano key. svg use can access it. As an example, this is the shape used by middle c"
  []
  [:defs
   [:g {:id "keybshape"}
    [:path {:id "key-b-shape-path"
            :style {
                  :fill-rule "evenodd"
                  :stroke "#000"
                  :stroke-width "1px"
                  :fill "#ff6800"}
            :d (str "m" 0 " " 0 " "
                    "v51l29 17 29-17v-35.858l-29-17.142-0.004-52.844-28.797 0.0101z")}]]])

(defn grid [cols rows]
   (for [x (range cols)
         y (range rows)]
     [x y]))

(defn svg-inkscape-hexagons [h w]
  [:svg {:width w
         :height h
         :id "inkscape-hexagons"
   :style {:outline "2px solid black"
           :background-color "#eee"}}

   ;; may wan to revisit this using the use tag and defs
   ;; this may reduce the amount of code sent to the client
   (map #(hexagon %) (grid 9 5))])

(defn svg-circles [h w]
  [:svg {:width w
         :height h
         :id "circles"
   :style {:outline "2px solid black"
           :background-color "#eee"}}

   ;; may wan to revisit this using the use tag.
   ;; this may reduce the amount of code sent to the client
   (map #(circle %) (grid 9 5))])

(defn svg-piano-keyboard [h w]
  [:svg {:width w
         :height h
         :id "piano"
   :style {:outline "2px solid black"
           :background-color "#eee"}}
   (defs-piano-key-b-shape)
   [:use {:xlink/href "#keybshape" :x 100 :y 10}]


   (piano-key-b-shape [40 60] 1)
   (piano-key-b-shape [-200 60] -1)
   (piano-key-b-shape [200 60] 1)
   (piano-key-b-shape [-400 60] -1)
   
   ])



(defn svg-triangles []
  [:svg {:width "500px" :height "250px" :viewBox "0 0 200 200"
         :xmlns "http://www.w3.org/2000/svg"
         :xmlns/xlink="http://www.w3.org/1999/xlink"

         :version "1.1"
         :style {:outline "2px solid black"
                 :background-color "#eee"}}
   [:defs 
    [:g {:id "simple-circle"}
     [:circle {:cx 110 :cy 100 :class "circle" :r "19"}]]]
   [:use {:xlink:href "#simple-circle"} ]]
   )

;; All the points to make up a polygon

(def polygon-points "20.000,0.000 10.000,17.321 -10.000,17.321 -20.000,0.000 -10.000,-17.321 10.000,-17.321")

(defn polygon-tile [[x y] title]
  (let [hx (if (even? y)
            (+ 28 (* x 55))
            (+ 55.5 (* x 55)))            
        hy   (+ 35 (* y 48))]

    [:g {:class "tile" :transform (str "translate(" hx "," hy ")")}
     [:polygon {:transform "rotate(-30),scale(1.6,1.6)"
                :fill "hsl(20, 10%, 95%)"
                :stroke "hsl(0, 0%, 70%)"
                :stroke-width "0.5" 
                :points polygon-points}]
     [:text {:y "0.4em"
             :transform "scale(.95)"
             :text-anchor "middle"}
      title]])

  )

(defn svg-accordion [h w]
    [:svg {:width w
           :height h
           :id "accordion"
           :viewBox (str "0 0 100% " h)
   :style {:outline "2px solid black"
           :background-color "#eee"}}
     (map #(polygon-tile %1 %2) (grid 12 2)
          ["D♭"  "A♭" "E♭" "B♭" "F"  "C"  "G"  "D"  "A" "E" "B" "F♯"
           "B♭♭" "F♭" "C♭" "G♭" "Db" "A♭" "E♭" "B♭" "F" "C" "G" "D"
           ])
     ])



;;How do you apply use tag 


;; -------------------------
;; Views

(defn home-page []
  [:style
   (str ".keyboard { outline: 2px solid black; background-color:#333; }"
        ".white-key { fill: #fff; stroke: #000; stroke-width: 1px; }"
        ".white-key:hover { fill: #eee; stroke: #444; stroke-width: 3px; }"
        ".black-key { fill: #000;    stroke: #000;    stroke-width: 1px; }"
        ".black-key:hover { fill: #222;    stroke: #444; stroke-width: 3px; }"
        ".tile polygon { pointer-events: visiblePainted;  fill: hsl(60, 10%, 95%); stroke: hsl(0, 0%, 70%);  stroke-width: 0.5;     }"
        ".tile text { font-family: \"Droid Sans\";     }")]
  
   [:div [:h2 "Welcome to omnibar"]
   [:div [:a {:href "/about"} "go to about page"]]

   [:div
   [svg-inkscape-hexagons 250 500]
   [svg-circles 250 500]
   [svg-triangles] 
    [svg-piano-keyboard 150 500]]
    [svg-accordion 150 "100%"]
   ])

(defn about-page []
  [:div [:h2 "About omnibar"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn current-page []
  [:div [(session/get :current-page)]])


;; -------------------------
;; Routes

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/about" []
  (session/put! :current-page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!)
  (accountant/dispatch-current!)
  (mount-root))
