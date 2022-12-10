(require '[clojure.string :as str])

(def input
  (->> (slurp "input")
       (#(str/split % #"\n"))
       (map #(str/split % #" "))))

(defn instruction
  [i]
  (case (first i)
      "noop" [[1 0]]
      "addx" [[1 0] [1 (Integer. (second i))]]))

(->> input
     (map instruction)
     (apply concat)
     (reductions #(map + %1 %2) [1 1])
     (#(for [i (range 20 221 40)] (nth % (dec i))))
     (map #(apply * %))
     (reduce +))
