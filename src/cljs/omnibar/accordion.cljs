(ns omnibar.accordion)

(def major-3rd-note
  {:name "major-3rd-note" :text "Major 3rd note" :row 0 :notes [
            {:id "accord-bass-maj-3rd-d-flat"
             :midi [49]        ;; the midi notes that are played when the button is pressed.
             :text "Db"        ;; what should appear on the bass button
             :position [0 0]   ;; where it appears on the accordion bass board
             :class "r0 c0"}   ;; knowing the row and column can help css styling, so you can highligh the whole row and columns of a selected note.
            {:id "accord-bass-maj-3rd-a-flat" :midi [44] :text "Ab" :position [0 1] :class "r0 c1"}
            {:id "accord-bass-maj-3rd-e-flat" :midi [39] :text "Eb" :position [0 2] :class "r0 c2"}
            {:id "accord-bass-maj-3rd-b-flat" :midi [46] :text "Bb" :position [0 3] :class "r0 c3"}
            {:id "accord-bass-maj-3rd-f" :midi [41] :text "F" :position [0 4] :class "r0 c4"}
            {:id "accord-bass-maj-3rd-c" :midi [48] :text "C" :position [0 5] :class "r0 c5"}
            {:id "accord-bass-maj-3rd-g" :midi [43] :text "G" :position [0 6] :class "r0 c6"}
            {:id "accord-bass-maj-3rd-d" :midi [38] :text "D" :position [0 7] :class "r0 c7"}
            {:id "accord-bass-maj-3rd-a" :midi [45] :text "A" :position [0 8] :class "r0 c8"}
            {:id "accord-bass-maj-3rd-e" :midi [40] :text "E" :position [0 9] :class "r0 c9"} ;; same as accord-bass-root-f-flat
            {:id "accord-bass-maj-3rd-b" :midi [47] :text "B" :position [0 10] :class "r0 c10"}
            {:id "accord-bass-maj-3rd-f-sharp" :midi [42] :text "F#" :position [0 11] :class "r0 c11"}
            {:id "accord-bass-maj-3rd-c-sharp" :midi [49] :text "C#" :position [0 12] :class "r0 c12"}
            {:id "accord-bass-maj-3rd-g-sharp" :midi [44] :text "G#" :position [0 13] :class "r0 c13"}
            {:id "accord-bass-maj-3rd-d-sharp" :midi [39] :text "D#" :position [0 14] :class "r0 c14"}
            {:id "accord-bass-maj-3rd-a-sharp" :midi [46] :text "A#" :position [0 15] :class "r0 c15"}
            {:id "accord-bass-maj-3rd-e-sharp" :midi [41] :text "E#" :position [0 16] :class "r0 c16"}
            {:id "accord-bass-maj-3rd-b-sharp" :midi [48] :text "B#" :position [0 17] :class "r0 c17"}
            {:id "accord-bass-maj-3rd-double-sharp" :midi [43] :text "F##" :position [0 18] :class "r0 c18"}
            {:id "accord-bass-maj-3rd-double-sharp" :midi [38] :text "C##" :position [0 19] :class "r0 c19"}]})

(def root-note
  {:name "root-note" :text "Root note" :row 1 :notes [
            {:id "accord-bass-root-b-double-flat" :midi [45] :text "Bbb" :position [1 0] :class "r1 c0"}
            {:id "accord-bass-root-f-flat" :midi [40] :text "Fb" :position [1 1] :class "r1 c1"} ;; same as accord-bass-maj-3rd-e
            {:id "accord-bass-root-c-flat" :midi [35] :text "Cb" :position [1 2] :class "r1 c2"}
            {:id "accord-bass-root-g-flat" :midi [42] :text "Gb" :position [1 3] :class "r1 c3"}
            {:id "accord-bass-root-d-flat" :midi [37] :text "Db" :position [1 4] :class "r1 c4"}
            {:id "accord-bass-root-a-flat" :midi [44] :text "Ab" :position [1 5] :class "r1 c5"}
            {:id "accord-bass-root-e-flat" :midi [39] :text "Eb" :position [1 6] :class "r1 c6"}
            {:id "accord-bass-root-b-flat" :midi [34] :text "Bb" :position [1 7] :class "r1 c7"}
            {:id "accord-bass-root-f" :midi [41] :text "F" :position [1 8] :class "r1 c8"}
            {:id "accord-bass-root-c" :midi [36] :text "C" :position [1 9] :class "r1 c9"}
            {:id "accord-bass-root-g" :midi [43] :text "G" :position [1 10] :class "r1 c10"}
            {:id "accord-bass-root-d" :midi [38] :text "D" :position [1 11] :class "r1 c11"}
            {:id "accord-bass-root-a" :midi [45] :text "A" :position [1 12] :class "r1 c12"}
            {:id "accord-bass-root-e" :midi [40] :text "E" :position [1 13] :class "r1 c13"}
            {:id "accord-bass-root-b" :midi [35] :text "B" :position [1 14] :class "r1 c14"}
            {:id "accord-bass-root-f-sharp" :midi [42] :text "F#" :position [1 15] :class "r1 c15"}
            {:id "accord-bass-root-c-sharp" :midi [37] :text "C#" :position [1 16] :class "r1 c16"}
            {:id "accord-bass-root-g-sharp" :midi [44] :text "G#" :position [1 17] :class "r1 c17"}
            {:id "accord-bass-root-d-sharp" :midi [39] :text "D#" :position [1 18] :class "r1 c18"}
            {:id "accord-bass-root-a-sharp" :midi [34] :text "A#" :position [1 19] :class "r1 c19"}]})

(def major-chord
   {:name "major-chord" :text "Major chord" :row 2 :notes [
            {:id "accord-bass-maj-chord-d-flat" :midi [57 61 64] :text "Db" :position [2 0] :class "r2 c0"}
            {:id "accord-bass-maj-chord-a-flat" :midi [64 68 71] :text "Ab" :position [2 1] :class "r2 c1"}
            {:id "accord-bass-maj-chord-e-flat" :midi [59 63 66] :text "Eb" :position [2 2] :class "r2 c2"}
            {:id "accord-bass-maj-chord-b-flat" :midi [54 58 61] :text "Bb" :position [2 3] :class "r2 c3"}
            {:id "accord-bass-maj-chord-f" :midi [61 65 68] :text "F" :position [2 4] :class "r2 c4"}
            {:id "accord-bass-maj-chord-c" :midi [56 60 63] :text "C" :position [2 5] :class "r2 c5"}
            {:id "accord-bass-maj-chord-g" :midi [63 67 70] :text "G" :position [2 6] :class "r2 c6"}
            {:id "accord-bass-maj-chord-d" :midi [58 62 65] :text "D" :position [2 7] :class "r2 c7"}
            {:id "accord-bass-maj-chord-a" :midi [65 69 72] :text "A" :position [2 8] :class "r2 c8"}
            {:id "accord-bass-maj-chord-e" :midi [60 64 67] :text "E" :position [2 9] :class "r2 c9"}
            {:id "accord-bass-maj-chord-b" :midi [55 59 62] :text "B" :position [2 10] :class "r2 c10"}
            {:id "accord-bass-maj-chord-f-sharp" :midi [62 66 69] :text "F#" :position [2 11] :class "r2 c11"}
            {:id "accord-bass-maj-chord-c-sharp" :midi [57 61 64] :text "C#" :position [2 12] :class "r2 c12"}
            {:id "accord-bass-maj-chord-g-sharp" :midi [64 68 71] :text "G#" :position [2 13] :class "r2 c13"}
            {:id "accord-bass-maj-chord-d-sharp" :midi [59 63 66] :text "D#" :position [2 14] :class "r2 c14"}
            {:id "accord-bass-maj-chord-a-sharp" :midi [54 58 61] :text "A#" :position [2 15] :class "r2 c15"}
            {:id "accord-bass-maj-chord-e-sharp" :midi [61 65 68] :text "E#" :position [2 16] :class "r2 c16"}
            {:id "accord-bass-maj-chord-b-sharp" :midi [56 60 63] :text "B#" :position [2 17] :class "r2 c17"}
            {:id "accord-bass-maj-chord-f-double-sharp" :midi [63 67 70] :text "F##" :position [2 18] :class "r2 c18"}
            {:id "accord-bass-maj-chord-c-double-sharp" :midi [70 74 77] :text "C##" :position [2 19] :class "r2 c19"}]})

(def minor-chord
   {:name "minor-chord" :text "Minor chord" :row 3 :notes [
            {:id "accord-bass-minor-chord-d-flat" :midi [57 60 64] :text "Db" :position [3 0] :class "r3 c0"}
            {:id "accord-bass-minor-chord-a-flat" :midi [64 67 71] :text "Ab" :position [3 1] :class "r3 c1"}
            {:id "accord-bass-minor-chord-e-flat" :midi [59 62 66] :text "Eb" :position [3 2] :class "r3 c2"}
            {:id "accord-bass-minor-chord-b-flat" :midi [54 57 61] :text "Bb" :position [3 3] :class "r3 c3"}
            {:id "accord-bass-minor-chord-f" :midi [61 64 68] :text "F" :position [3 4] :class "r3 c4"}
            {:id "accord-bass-minor-chord-c" :midi [56 59 63] :text "C" :position [3 5] :class "r3 c5"}
            {:id "accord-bass-minor-chord-g" :midi [63 66 70] :text "G" :position [3 6] :class "r3 c6"}
            {:id "accord-bass-minor-chord-d" :midi [58 61 65] :text "D" :position [3 7] :class "r3 c7"}
            {:id "accord-bass-minor-chord-a" :midi [65 68 72] :text "A" :position [3 8] :class "r3 c8"}
            {:id "accord-bass-minor-chord-e" :midi [60 63 67] :text "E" :position [3 9] :class "r3 c9"}
            {:id "accord-bass-minor-chord-b" :midi [55 58 62] :text "B" :position [3 10] :class "r3 c10"}
            {:id "accord-bass-minor-chord-f-sharp" :midi [62 65 69] :text "F#" :position [3 11] :class "r3 c11"}
            {:id "accord-bass-minor-chord-c-sharp" :midi [57 60 64] :text "C#" :position [3 12] :class "r3 c12"}
            {:id "accord-bass-minor-chord-g-sharp" :midi [64 67 71] :text "G#" :position [3 13] :class "r3 c13"}
            {:id "accord-bass-minor-chord-d-sharp" :midi [59 62 66] :text "D#" :position [3 14] :class "r3 c14"}
            {:id "accord-bass-minor-chord-a-sharp" :midi [54 57 61] :text "A#" :position [3 15] :class "r3 c15"}
            {:id "accord-bass-minor-chord-e-sharp" :midi [61 64 68] :text "E#" :position [3 16] :class "r3 c16"}
            {:id "accord-bass-minor-chord-b-sharp" :midi [56 59 63] :text "B#" :position [3 17] :class "r3 c17"}
            {:id "accord-bass-minor-chord-f-double-sharp" :midi [63 66 70] :text "F##" :position [3 18] :class "r3 c18"}
            {:id "accord-bass-minor-chord-c-double-sharp" :midi [70 73 77] :text "C##" :position [3 19] :class "r3 c19"}]})

(def dominant-7th-chord
   {:name "dominant-7th-chord" :text "Dominant 7th chord" :row 4 :notes [
            {:id "accord-bass-dominant-7th-d-flat" :midi [57 61 67] :text "Db" :position [4 0] :class "r4 c0"}
            {:id "accord-bass-dominant-7th-a-flat" :midi [64 68 74] :text "Ab" :position [4 1] :class "r4 c1"}
            {:id "accord-bass-dominant-7th-e-flat" :midi [59 63 69] :text "Eb" :position [4 2] :class "r4 c2"}
            {:id "accord-bass-dominant-7th-b-flat" :midi [54 58 64] :text "Bb" :position [4 3] :class "r4 c3"}
            {:id "accord-bass-dominant-7th-f" :midi [61 65 71] :text "F" :position [4 4] :class "r4 c4"}
            {:id "accord-bass-dominant-7th-c" :midi [56 60 66] :text "C" :position [4 5] :class "r4 c5"}
            {:id "accord-bass-dominant-7th-g" :midi [63 67 73] :text "G" :position [4 6] :class "r4 c6"}
            {:id "accord-bass-dominant-7th-d" :midi [58 62 68] :text "D" :position [4 7] :class "r4 c7"}
            {:id "accord-bass-dominant-7th-a" :midi [65 69 75] :text "A" :position [4 8] :class "r4 c8"}
            {:id "accord-bass-dominant-7th-e" :midi [60 64 70] :text "E" :position [4 9] :class "r4 c9"}
            {:id "accord-bass-dominant-7th-b" :midi [55 59 65] :text "B" :position [4 10] :class "r4 c10"}
            {:id "accord-bass-dominant-7th-f-sharp" :midi [62 66 72] :text "F#" :position [4 11] :class "r4 c11"}
            {:id "accord-bass-dominant-7th-c-sharp" :midi [57 61 67] :text "C#" :position [4 12] :class "r4 c12"}
            {:id "accord-bass-dominant-7th-g-sharp" :midi [64 68 74] :text "G#" :position [4 13] :class "r4 c13"}
            {:id "accord-bass-dominant-7th-d-sharp" :midi [59 63 69] :text "D#" :position [4 14] :class "r4 c14"}
            {:id "accord-bass-dominant-7th-a-sharp" :midi [54 58 64] :text "A#" :position [4 15] :class "r4 c15"}
            {:id "accord-bass-dominant-7th-e-sharp" :midi [61 65 71] :text "E#" :position [4 16] :class "r4 c16"}
            {:id "accord-bass-dominant-7th-b-sharp" :midi [56 60 66] :text "B#" :position [4 17] :class "r4 c17"}
            {:id "accord-bass-dominant-7th-f-double-sharp" :midi [63 67 73] :text "F##" :position [4 18] :class "r4 c18"}
            {:id "accord-bass-dominant-7th-c-double-sharp" :midi [70 74 80] :text "C##" :position [4 19] :class "r4 c19"}]})

(def diminished-7th-chord
   {:name "diminished-7th-chord" :text "Diminished 7th chord" :row 5 :notes [
            {:id "accord-bass-diminished-7th-d-flat" :midi [54 57 60] :text "Db" :position [5 0] :class "r5 c0"}
            {:id "accord-bass-diminished-7th-a-flat" :midi [61 64 67] :text "Ab" :position [5 1] :class "r5 c1"}
            {:id "accord-bass-diminished-7th-e-flat" :midi [56 59 62] :text "Eb" :position [5 2] :class "r5 c2"}
            {:id "accord-bass-diminished-7th-b-flat" :midi [51 54 57] :text "Bb" :position [5 3] :class "r5 c3"}
            {:id "accord-bass-diminished-7th-f" :midi [58 61 64] :text "F" :position [5 4] :class "r5 c4"}
            {:id "accord-bass-diminished-7th-c" :midi [53 56 59] :text "C" :position [5 5] :class "r5 c5"}
            {:id "accord-bass-diminished-7th-g" :midi [60 63 66] :text "G" :position [5 6] :class "r5 c6"}
            {:id "accord-bass-diminished-7th-d" :midi [55 58 61] :text "D" :position [5 7] :class "r5 c7"}
            {:id "accord-bass-diminished-7th-a" :midi [62 65 68] :text "A" :position [5 8] :class "r5 c8"}
            {:id "accord-bass-diminished-7th-e" :midi [57 60 63] :text "E" :position [5 9] :class "r5 c9"}
            {:id "accord-bass-diminished-7th-b" :midi [52 55 58] :text "B" :position [5 10] :class "r5 c10"}
            {:id "accord-bass-diminished-7th-f-sharp" :midi [59 62 65] :text "F#" :position [5 11] :class "r5 c11"}
            {:id "accord-bass-diminished-7th-c-sharp" :midi [54 57 60] :text "C#" :position [5 12] :class "r5 c12"}
            {:id "accord-bass-diminished-7th-g-sharp" :midi [61 64 67] :text "G#" :position [5 13] :class "r5 c13"}
            {:id "accord-bass-diminished-7th-d-sharp" :midi [56 59 62] :text "D#" :position [5 14] :class "r5 c14"}
            {:id "accord-bass-diminished-7th-a-sharp" :midi [51 54 57] :text "A#" :position [5 15] :class "r5 c15"}
            {:id "accord-bass-diminished-7th-e-sharp" :midi [58 61 64] :text "E#" :position [5 16] :class "r5 c16"}
            {:id "accord-bass-diminished-7th-b-sharp" :midi [53 56 59] :text "B#" :position [5 17] :class "r5 c17"}
            {:id "accord-bass-diminished-7th-f-double-sharp" :midi [60 63 66] :text "F##" :position [5 18] :class "r5 c18"}
            {:id "accord-bass-diminished-7th-c-double-sharp" :midi [67 70 73] :text "C##" :position [5 19] :class "r5 c19"}]})

;;"Typical 120-button Stradella bass system. This is the left-hand manual system found on most unisonoric accordions today" -- wikipedia
(def stradella-bass
  [major-3rd-note root-note major-chord minor-chord dominant-7th-chord diminished-7th-chord])
