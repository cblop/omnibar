(ns omnibar.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [hum.core :as hum]
              [cljs.core.async :refer [put! chan <!]]
              [omnibar.accordion :as accord]))

;; -------------------------
;; async

(def note-chan (chan))

;; -------------------------
;; audio

(def ctx (hum/create-context))

(defn play-note! [freq]
  (let [context ctx
        vco (hum/create-osc ctx :sawtooth)
        vcf (hum/create-biquad-filter context)
        output (hum/create-gain context)]
    (do (hum/connect vco vcf output)
        (hum/start-osc vco)
        (hum/connect-output output)
        (hum/note-on output vco freq)
        (go-loop [] (let [input (<! note-chan)]
              (if (= input :stop) (hum/note-off output) (recur))))
        )))

(defn play-chord! [chord]
  (doseq [f chord] (play-note! f)))

(defn stop-chord! [chord]
  (doseq [f chord] (put! note-chan :stop)))

(defn midi-to-freq [note-num]
  (let [expt-numerator (- note-num 49)
        expt-denominator 12
        expt (/ expt-numerator expt-denominator)
        multiplier (.pow js/Math 2 expt)
        a 440]
    (* multiplier a)))

;; -------------------------
;; svg

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

;;(defn b-key-points [[x y]] (str "m" x  "m1.183.883v106.15l28.954 16.89 28.955-17.2-.105-35.523-28.745-17.177L30.346.99z"))
(def b-key-points "M1.183.883v106.15l28.954 16.89 28.955-17.2-.105-35.523-28.745-17.177L30.346.99z")
(def black-key-points "M2.09 1.846l.018 53.005 28.967 16.985 29.002-16.987.004-53.005z")

;; try to replace this with hexagon-points defined later.
(def hex-key-points "M1.31 53.91l29 17 29.022-17.028-.088-35.853L30.348 1.044l-29.01 17.01c0 17.668-.028 18.187-.028 35.854z")

(def white-key-style
  {:fill-rule "evenodd"
   :stroke "#000"
   :stroke-width "1px"
   :fill "#fff"} )

(def black-key-style
  {:fill-rule "evenodd"
   :stroke "#fff"
   :stroke-width "1px"
   :fill "#000"} )


(defn piano-key [id style [x y] key-shape-points]
  [:g {:id id :transform (str "translate(" x "," y ")")}
   [:path {:id id :style style :d key-shape-points}]])

(defn b-key [id pos]
  (piano-key id white-key-style pos b-key-points))

(defn black-key [id pos]
  (piano-key id black-key-style pos black-key-points))

(defn d-key [id [x y]]
  [:g {:transform "scale(-1,1)"}
   (piano-key id white-key-style [(- x) y] b-key-points)])

(defn hex-key [id  pos]
  (piano-key id white-key-style pos hex-key-points))


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


(defn octave [[x y]]
  (let [octave-n (inc x)] 
    [:g {:id (str "octave-" octave-n) :transform (str "translate(" (* x 402) "," y ")")}
     ;; Could this be done in a more idomatic way? Maybe protocols
     (b-key     (str "C" octave-n)     [1 1])
     (black-key (str "C#" octave-n)   [29 0])
     (hex-key   (str "D" octave-n)   [58 54])
     (black-key (str "D#" octave-n)   [86 0])
     (d-key     (str "E" octave-n)   [176 1])
     (b-key     (str "F" octave-n)   [173 1])
     (black-key (str "F#" octave-n)  [200 0])
     (hex-key   (str "G" octave-n)  [230 54])
     (black-key (str "G#" octave-n)  [258 0])
     (hex-key   (str "A" octave-n)  [288 54])
     (black-key (str "A#" octave-n)  [316 0])
     (d-key     (str "B" octave-n)   [406 1])]))

(defn svg-piano-keyboard [h w]
  [:svg {:width w
         :height h
         :id "piano"
   :style {:outline "2px solid black"
           :background-color "#eee"}}
   ;; TODO add A0 A0# B0 and C8
   ;; Build a range of octaves
   (for [x (range 8)]
     (octave [x 0]))])

(defn svg-piano-keyboard2 [h w]
  [:svg {:width w
         :height h
         :id "piano"
   :style {:outline "2px solid black"
           :background-color "#eee"}}
   ;; TODO add A0 A0# B0 and C8
   
   ;; Build a range of octaves
   [:g {:id id :transform "translate(2000,150), rotate(180 0 0)"}
    (for [x (range 8)]
      (octave [x 0]))]])



;; All the points to make up a polygon
(def hexagon-points "20.000,0.000 10.000,17.321 -10.000,17.321 -20.000,0.000 -10.000,-17.321 10.000,-17.321")

(defn hexagon [chord]
  [:polygon {:on-mouse-down #(play-chord! chord)
             :on-mouse-up #(stop-chord! chord)
             :transform "rotate(-30),scale(1.6,1.6)"
             :fill "hsl(20, 10%, 95%)"
             :stroke "hsl(0, 0%, 70%)"
             :stroke-width "0.5" 
             :points hexagon-points}] )

(defn circle [chord]
  [:circle {
            :on-mouse-down #(play-chord! chord)
            :on-mouse-up #(stop-chord! chord)
            :r "27" :stroke "black" :stroke-width "1" :fill "red"}] )


(defn accordion-keyboard [shape note]
  (let [[x y] (:position note)
        title (:text note)
        freqs (map midi-to-freq (:midi note))
        y-start-pos 33
        x-start-pos 33
        hx   (+ y-start-pos (* x 48))
        hy   (+ x-start-pos (* x 27) (* y 55))]

    [:g {:class "tile" :transform (str "translate(" hy "," hx ")")}
     (if (= shape "circle") (circle freqs) (hexagon freqs))
     [:text {:y "0.4em"
             :on-mouse-down #(play-chord! freqs)
             :on-mouse-up #(stop-chord! freqs)
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
    [svg-piano-keyboard2 150 2000]

    [svg-accordion [320 1250] "hexagon"]
    [svg-accordion [320 1250] "circle"]
    [svg-piano-keyboard 150 2000]
    ;;[svg-inkscape-hexagons 250 500]
    ]])

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
