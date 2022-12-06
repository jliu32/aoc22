(require '[clojure.string :as str])

(def rule {"AX" (+ 3 0),
           "AY" (+ 1 3),
           "AZ" (+ 2 6),
           "BX" (+ 1 0),
           "BY" (+ 2 3),
           "BZ" (+ 3 6),
           "CX" (+ 2 0),
           "CY" (+ 3 3),
           "CZ" (+ 1 6)})

(->> (slurp "input")
     (#(str/split % #"\n"))
     (map #(str/split % #"\s"))
     (map #(apply str %))
     (map rule)
     (reduce +))
