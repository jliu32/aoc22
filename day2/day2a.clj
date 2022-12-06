(require '[clojure.string :as str])

(def rule {"AX" (+ 1 3),
           "AY" (+ 2 6),
           "AZ" (+ 3 0),
           "BX" (+ 1 0),
           "BY" (+ 2 3),
           "BZ" (+ 3 6),
           "CX" (+ 1 6),
           "CY" (+ 2 0),
           "CZ" (+ 3 3)})

(->> (slurp "input")
     (#(str/split % #"\n"))
     (map #(str/split % #"\s"))
     (map #(apply str %))
     (map rule)
     (reduce +))
