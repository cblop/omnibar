(ns omnibar.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [hum.core :as hum]
              [omnibar.accordion :as accord]))

;; -------------------------
;; svg


(def ctx (hum/create-context))
(def vco (hum/create-osc ctx :sawtooth))
(def vcf (hum/create-biquad-filter ctx))
(def output (hum/create-gain ctx))


(defn midi-to-freq [note-num]
  (let [expt-numerator (- note-num 49)
        expt-denominator 12
        expt (/ expt-numerator expt-denominator)
        multiplier (.pow js/Math 2 expt)
        a 440]
    (* multiplier a)))
                                        ; connect the VCO to the VCF and on to the output gain node
(hum/connect vco vcf output)

(hum/start-osc vco)

(hum/connect-output output)


(defn inkscape-hexagon [[x y]]
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
   (map #(inkscape-hexagon %) (grid 9 5))])

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


;; All the points to make up a polygon
(def hexagon-points "20.000,0.000 10.000,17.321 -10.000,17.321 -20.000,0.000 -10.000,-17.321 10.000,-17.321")

(defn hexagon [freq]
  [:polygon {:on-mouse-down #(hum/note-on output vco freq)
             :on-mouse-up #(hum/note-off output)
             :transform "rotate(-30),scale(1.6,1.6)"
             :fill "hsl(20, 10%, 95%)"
             :stroke "hsl(0, 0%, 70%)"
             :stroke-width "0.5" 
             :points hexagon-points}] )

(defn circle [freq]
  [:circle {
            :on-mouse-down #(hum/note-on output vco freq)
            :on-mouse-up #(hum/note-off output)
            :r "27" :stroke "black" :stroke-width "1" :fill "red"}] )


(defn accordion-keyboard [shape note]
  (let [[x y] (:position note)
        title (:text note)
        freq (midi-to-freq (first (:midi note)))
        y-start-pos 33
        x-start-pos 33
        hx   (+ y-start-pos (* x 48))
        hy   (+ x-start-pos (* x 27) (* y 55))]

    [:g {:class "tile" :transform (str "translate(" hy "," hx ")")}
     (if (= shape "circle") (circle freq) (hexagon freq))
     [:text {:y "0.4em"
             :on-mouse-down #(hum/note-on output vco freq)
             :on-mouse-up #(hum/note-off output)
             :transform "scale(.95)"
             :text-anchor "middle"}
      title]]))

(defn accordion-group [shape note-group]
  (map #(accordion-keyboard shape %) (:notes note-group)) )

(defn svg-accordion [[h w] shape]
    [:svg {:width w
           :height h
           :id "accordion"
           :viewBox (str "0 0 100% " h)
   :style {:outline "2px solid black"
           :background-color "#eee"}}
     (map #(accordion-group shape %) accord/stradella-bass) ])

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
  
  [:div
   [:h2 "Welcome to omnibar"]
   [:div [:a {:href "/about"} "go to about page"]]
   [:div
    [svg-piano-keyboard 150 800]
    [svg-accordion [320 1250] "hexagon"]
    [svg-accordion [320 1250] "circle"]
    [svg-inkscape-hexagons 250 500]]    ])

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
