(require '[clojure.string :as str])

(def input
  (->> (slurp "input")
       (#(str/split % #"\n"))
       (map char-array)
       (map #(map (fn [c] (- (compare \0 c))) %))
       (map #(apply vector %))
       (#(apply vector %))))

(def input-t
  (apply mapv vector input))

(def visible
  (for [x (range 1 (- (count input) 1))
        y (range 1 (- (count input-t) 1))]
    (let [row (nth input x)
          col (nth input-t y)
          t (nth row y)
          l (take y row)
          r (drop (inc y) row)
          u (take x col)
          d (drop (inc x) col)]
      (some true? (map #(every? (partial > t) %) [l r u d])))))

(+ (* 2 (count input))
   (* 2 (count input-t))
   (count (filter identity visible))
   -4)
