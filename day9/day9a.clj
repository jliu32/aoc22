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

(defn tail-moves
  [t h]
  (let [s (:s t)
        p (:p t)]
    (case (map - h p)
      [2 0] (let [np (map + p [1 0])] {:s (conj s np) :p np})
      [-2 0] (let [np (map + p [-1 0])] {:s (conj s np) :p np})
      [0 2] (let [np (map + p [0 1])] {:s (conj s np) :p np})
      [0 -2] (let [np (map + p [0 -1])] {:s (conj s np) :p np})
      [1 2] (let [np (map + p [1 1])] {:s (conj s np) :p np})
      [1 -2] (let [np (map + p [1 -1])] {:s (conj s np) :p np})
      [-1 2] (let [np (map + p [-1 1])] {:s (conj s np) :p np})
      [-1 -2] (let [np (map + p [-1 -1])] {:s (conj s np) :p np})
      [2 1] (let [np (map + p [1 1])] {:s (conj s np) :p np})
      [2 -1] (let [np (map + p [1 -1])] {:s (conj s np) :p np})
      [-2 1] (let [np (map + p [-1 1])] {:s (conj s np) :p np})
      [-2 -1] (let [np (map + p [-1 -1])] {:s (conj s np) :p np})
      t)))

(count (:s (reduce tail-moves {:s #{[0 0]} :p [0 0]} head-pos)))
