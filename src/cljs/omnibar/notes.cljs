(ns ^{:doc "functions for general music knowledge. Consider keeping Java/JavaScript neutral in the future."}
    omnibar.notes)

;; Overtone has a more robust set of functions. We don't need it's level of functionality yet.
;; https://github.com/overtone/overtone/blob/master/src/overtone/music/pitch.clj

(def lowest-piano-midi 21)
(def highest-piano-midi 108)

(def piano-notes [                                        :A0 :A#0 :B0
                  :C1 :C#1 :D1 :D#1 :E1 :F1 :F#1 :G1 :G#1 :A1 :A#1 :B1
                  :C2 :C#2 :D2 :D#2 :E2 :F2 :F#2 :G2 :G#2 :A2 :A#2 :B2
                  :C3 :C#3 :D3 :D#3 :E3 :F3 :F#3 :G3 :G#3 :A3 :A#3 :B3
                  :C4 :C#4 :D4 :D#4 :E4 :F4 :F#4 :G4 :G#4 :A4 :A#4 :B4
                  :C5 :C#5 :D5 :D#5 :E5 :F5 :F#5 :G5 :G#5 :A5 :A#5 :B5
                  :C6 :C#6 :D6 :D#6 :E6 :F6 :F#6 :G6 :G#6 :A6 :A#6 :B6
                  :C7 :C#7 :D7 :D#7 :E7 :F7 :F#7 :G7 :G#7 :A7 :A#7 :B7
                  :C8])

(def note-midi-map (zipmap piano-notes
                           (range lowest-piano-midi (inc highest-piano-midi))))

(defn note->midi [notename]
  (get note-midi-map (keyword notename)))

(defn midi->note [midi-value]
   (nth piano-notes (- midi-value lowest-piano-midi)))

(defn midi->freq [note-num]
  (let [expt-numerator (- note-num 49)
        expt-denominator 12
        expt (/ expt-numerator expt-denominator)
        multiplier (.pow js/Math 2 expt)
        a 440]
    (* multiplier a)))

(defn note->freq [notename]
  (-> notename
      note->midi
      midi->freq))

;; -------------------------
;; Scales


(defn scale [intervals]
  "Return a function to create a vector of a scale of midi notes"
  (fn [midi]
    (reductions + (into [midi] intervals))))

(def major (scale [2 2 1 2 2 2 1]))
(def minor (scale [2 1 2 2 1 2 2]))
(def blues (scale [3 2 1 2 3 2]))
(def pentatonic (scale [3 2 1 2 3 2]))
(def blues (scale [3 2 1 2 3 2]))
;; TODO need to define dominant-7th-chord diminished-7th-chord scales.
(def diminished  (scale [1 2 1 2 1 2 1 2]))
(def diminished2 (scale [2 1 2 1 2 1 2 1]))

(defn scale->notes [midi-value scale-type]
  (map #(midi->note %) (scale-type midi-value)))

(def c-major-scale (scale->notes 60 major))
(def d-major-scale (scale->notes 62 major))

;; -------------------------
;; TODO Arpeggios
;; An arpeggio is a sequence of notes in a chord, play one after the other, rather simultaneously.
;; Arpeggios allow monophonic instruments to play chords and harmony and help create rhythmic interest.

