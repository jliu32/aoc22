(require '[clojure.string :as str])

(def input
  (->> (slurp "input")
       (#(str/split % #"\n"))
       (map #(str/split % #" "))))

(defn direction
  [d]
  (let [s (Integer. (second d))]
    (case (first d)
      "L" (take s (repeat [-1 0]))
      "R" (take s (repeat [1 0]))
      "U" (take s (repeat [0 1]))
      "D" (take s (repeat [0 -1])))))

(def head-pos
  (->> input
       (map direction)
       (apply concat)
       (reductions #(map + %1 %2) [0 0])))

(def initial (take 9 (repeat [0 0])))

(defn next-moves
  [h p]
  (case (map - h p)
    [2 0] (map + p [1 0])
    [-2 0] (map + p [-1 0])
    [0 2] (map + p [0 1])
    [0 -2] (map + p [0 -1])
    [1 2] (map + p [1 1])
    [1 -2] (map + p [1 -1])
    [-1 2] (map + p [-1 1])
    [-1 -2] (map + p [-1 -1])
    [2 1] (map + p [1 1])
    [2 -1] (map + p [1 -1])
    [-2 1] (map + p [-1 1])
    [-2 -1] (map + p [-1 -1])
    [2 2] (map + p [1 1])
    [2 -2] (map + p [1 -1])
    [-2 2] (map + p [-1 1])
    [-2 -2] (map + p [-1 -1])
    p))

(defn step
  [rope h]
  (rest (reductions next-moves h rope)))

(defn move
  [s h]
  (let [rope (:r s)
        tail (:s s)
        nr (step rope h)]
    {:r nr :s (conj tail (last nr))}))


(count (:s (reduce move {:s #{[0 0]} :r initial} head-pos)))
