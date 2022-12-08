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
          l (reverse (take y row))
          r (drop (inc y) row)
          u (reverse (take x col))
          d (drop (inc x) col)]
      (->> [l r u d]
           (map (fn [b]
                  (let [[short tall] (split-with #(< % t) b)
                        f (first tall)]
                    (if (nil? f)
                      short
                      (if (>= f t)
                        (conj short f)
                        short)))))
           (map count)))))

(reduce max (map #(apply * %) visible))
