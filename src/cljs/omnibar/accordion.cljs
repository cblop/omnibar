(ns omnibar.accordion)

;;"Typical 120-button Stradella bass system. This is the left-hand manual system found on most unisonoric accordions today" -- wikipedia
(def stradella-bass
  [{:name "major-3rd-note" :text "Major 3rd note" :notes [
            {:id "major-3rd-d-flat"
             :midi []
             :text "Db"
             :class "r0 c0"} ;; knowing the row and column can help css styling, so you can highligh the whole row and columns of a selected note.
            {:id "major-3rd-a-flat"       :midi [] :text "Ab"  :class "r0 c1"}
            {:id "major-3rd-e-flat"       :midi [] :text "Eb"  :class "r0 c2"}
            {:id "major-3rd-b-flat"       :midi [] :text "Bb"  :class "r0 c3"}
            {:id "major-3rd-f"            :midi [] :text "F"   :class "r0 c4"}
            {:id "major-3rd-c"            :midi [] :text "C"   :class "r0 c5"}
            {:id "major-3rd-g"            :midi [] :text "G"   :class "r0 c6"}
            {:id "major-3rd-d"            :midi [] :text "D"   :class "r0 c7"}
            {:id "major-3rd-a"            :midi [] :text "A"   :class "r0 c8"}
            {:id "major-3rd-e"            :midi [] :text "E"   :class "r0 c9"}
            {:id "major-3rd-b"            :midi [] :text "B"   :class "r0 c10"}
            {:id "major-3rd-f-sharp"      :midi [] :text "F#"  :class "r0 c11"}
            {:id "major-3rd-c-sharp"      :midi [] :text "C#"  :class "r0 c12"}
            {:id "major-3rd-g-sharp"      :midi [] :text "G#"  :class "r0 c13"}
            {:id "major-3rd-d-sharp"      :midi [] :text "D#"  :class "r0 c14"}
            {:id "major-3rd-a-sharp"      :midi [] :text "A#"  :class "r0 c15"}
            {:id "major-3rd-e-sharp"      :midi [] :text "E#"  :class "r0 c16"}
            {:id "major-3rd-b-sharp"      :midi [] :text "B#"  :class "r0 c17"}
            {:id "major-3rd-double-sharp" :midi [] :text "F##" :class "r0 c18"}
            {:id "major-3rd-double-sharp" :midi [] :text "C##" :class "r0 c19"}]}
   {:name "root-note" :text "Root note" :notes [
            {:id "root-b-double-flat" :midi [] :text "Bbb" :class "r1 c0"}
            {:id "root-f-flat" :midi [] :text "Fb" :class "r1 c1"}
            {:id "root-c-flat" :midi [] :text "Cb" :class "r1 c2"}
            {:id "root-g-flat" :midi [] :text "Gb" :class "r1 c3"}
            {:id "root-d-flat" :midi [] :text "Db" :class "r1 c4"}
            {:id "root-a-flat" :midi [] :text "Ab" :class "r1 c5"}
            {:id "root-e-flat" :midi [] :text "Eb" :class "r1 c6"}
            {:id "root-b-flat" :midi [] :text "Bb" :class "r1 c7"}
            {:id "root-f" :midi [] :text "F" :class "r1 c8"}
            {:id "root-c" :midi [] :text "C" :class "r1 c9"}
            {:id "root-g" :midi [] :text "G" :class "r1 c10"}
            {:id "root-d" :midi [] :text "D" :class "r1 c11"}
            {:id "root-a" :midi [] :text "A" :class "r1 c12"}
            {:id "root-e" :midi [] :text "E" :class "r1 c13"}
            {:id "root-b" :midi [] :text "B" :class "r1 c14"}
            {:id "root-f-sharp" :midi [] :text "F#" :class "r1 c15"}
            {:id "root-c-sharp" :midi [] :text "C#" :class "r1 c16"}
            {:id "root-g-sharp" :midi [] :text "G#" :class "r1 c17"}
            {:id "root-d-sharp" :midi [] :text "D#" :class "r1 c18"}
            {:id "root-a-sharp" :midi [] :text "A#" :class "r1 c19"}]}
   {:name "major-chord" :text "Major cho rd" :notes [
            {:id "major-chord-" :midi [] :text "Db"  :class "r2 c0"}
            {:id "major-chord-" :midi [] :text "Ab"  :class "r2 c1"}
            {:id "major-chord-" :midi [] :text "Eb"  :class "r2 c2"}
            {:id "major-chord-" :midi [] :text "Bb"  :class "r2 c3"}
            {:id "major-chord-" :midi [] :text "F"   :class "r2 c4"}
            {:id "major-chord-" :midi [] :text "C"   :class "r2 c5"}
            {:id "major-chord-" :midi [] :text "G"   :class "r2 c6"}
            {:id "major-chord-" :midi [] :text "D"   :class "r2 c7"}
            {:id "major-chord-" :midi [] :text "A"   :class "r2 c8"}
            {:id "major-chord-" :midi [] :text "E"   :class "r2 c9"}
            {:id "major-chord-" :midi [] :text "B"   :class "r2 c10"}
            {:id "major-chord-" :midi [] :text "F#"  :class "r2 c11"}
            {:id "major-chord-" :midi [] :text "C#"  :class "r2 c12"}
            {:id "major-chord-" :midi [] :text "G#"  :class "r2 c13"}
            {:id "major-chord-" :midi [] :text "D#"  :class "r2 c14"}
            {:id "major-chord-" :midi [] :text "A#"  :class "r2 c15"}
            {:id "major-chord-" :midi [] :text "E#"  :class "r2 c16"}
            {:id "major-chord-" :midi [] :text "B#"  :class "r2 c17"}
            {:id "major-chord-" :midi [] :text "F##" :class "r2 c18"}
            {:id "major-chord-" :midi [] :text "C##" :class "r2 c19"}]}
   {:name "minor-chord" :text "Minor chord" :notes [
            {:id "minor-chord-" :midi [] :text "Db"  :class ""}
            {:id "minor-chord-" :midi [] :text "Ab"  :class ""}
            {:id "minor-chord-" :midi [] :text "Eb"  :class ""}
            {:id "minor-chord-" :midi [] :text "Bb"  :class ""}
            {:id "minor-chord-" :midi [] :text "F"   :class ""}
            {:id "minor-chord-" :midi [] :text "C"   :class ""}
            {:id "minor-chord-" :midi [] :text "G"   :class ""}
            {:id "minor-chord-" :midi [] :text "D"   :class ""}
            {:id "minor-chord-" :midi [] :text "A"   :class ""}
            {:id "minor-chord-" :midi [] :text "E"   :class ""}
            {:id "minor-chord-" :midi [] :text "B"   :class ""}
            {:id "minor-chord-" :midi [] :text "F#"  :class ""}
            {:id "minor-chord-" :midi [] :text "C#"  :class ""}
            {:id "minor-chord-" :midi [] :text "G#"  :class ""}
            {:id "minor-chord-" :midi [] :text "D#"  :class ""}
            {:id "minor-chord-" :midi [] :text "A#"  :class ""}
            {:id "minor-chord-" :midi [] :text "E#"  :class ""}
            {:id "minor-chord-" :midi [] :text "B#"  :class ""}
            {:id "minor-chord-" :midi [] :text "F##" :class ""}
            {:id "minor-chord-" :midi [] :text "C##" :class ""}]}
   {:name "dominant-7th-chord" :text "Dominant 7th chord" :notes [
            {:id "dominant-7th-" :midi [] :text "Db"  :class ""}
            {:id "dominant-7th-" :midi [] :text "Ab"  :class ""}
            {:id "dominant-7th-" :midi [] :text "Eb"  :class ""}
            {:id "dominant-7th-" :midi [] :text "Bb"  :class ""}
            {:id "dominant-7th-" :midi [] :text "F"   :class ""}
            {:id "dominant-7th-" :midi [] :text "C"   :class ""}
            {:id "dominant-7th-" :midi [] :text "G"   :class ""}
            {:id "dominant-7th-" :midi [] :text "D"   :class ""}
            {:id "dominant-7th-" :midi [] :text "A"   :class ""}
            {:id "dominant-7th-" :midi [] :text "E"   :class ""}
            {:id "dominant-7th-" :midi [] :text "B"   :class ""}
            {:id "dominant-7th-" :midi [] :text "F#"  :class ""}
            {:id "dominant-7th-" :midi [] :text "C#"  :class ""}
            {:id "dominant-7th-" :midi [] :text "G#"  :class ""}
            {:id "dominant-7th-" :midi [] :text "D#"  :class ""}
            {:id "dominant-7th-" :midi [] :text "A#"  :class ""}
            {:id "dominant-7th-" :midi [] :text "E#"  :class ""}
            {:id "dominant-7th-" :midi [] :text "B#"  :class ""}
            {:id "dominant-7th-" :midi [] :text "F##" :class ""}
            {:id "dominant-7th-" :midi [] :text "C##" :class ""}]}
   {:name "diminished-7th-chord" :text "Diminished 7th chord" :notes [
            {:id "diminished-7th-" :midi [] :text "Db"  :class ""}
            {:id "diminished-7th-" :midi [] :text "Ab"  :class ""}
            {:id "diminished-7th-" :midi [] :text "Eb"  :class ""}
            {:id "diminished-7th-" :midi [] :text "Bb"  :class ""}
            {:id "diminished-7th-" :midi [] :text "F"   :class ""}
            {:id "diminished-7th-" :midi [] :text "C"   :class ""}
            {:id "diminished-7th-" :midi [] :text "G"   :class ""}
            {:id "diminished-7th-" :midi [] :text "D"   :class ""}
            {:id "diminished-7th-" :midi [] :text "A"   :class ""}
            {:id "diminished-7th-" :midi [] :text "E"   :class ""}
            {:id "diminished-7th-" :midi [] :text "B"   :class ""}
            {:id "diminished-7th-" :midi [] :text "F#"  :class ""}
            {:id "diminished-7th-" :midi [] :text "C#"  :class ""}
            {:id "diminished-7th-" :midi [] :text "G#"  :class ""}
            {:id "diminished-7th-" :midi [] :text "D#"  :class ""}
            {:id "diminished-7th-" :midi [] :text "A#"  :class ""}
            {:id "diminished-7th-" :midi [] :text "E#"  :class ""}
            {:id "diminished-7th-" :midi [] :text "B#"  :class ""}
            {:id "diminished-7th-" :midi [] :text "F##" :class ""}
            {:id "diminished-7th-" :midi [] :text "C##" :class ""}]}])

