(require '[clojure.string :as str])
(require '[clojure.set :as set])

(defn range-set [x y]
  (set (range x (inc y))))

(->> (slurp "input")
     (#(str/split % #"\n"))
     (map #(str/split % #",|-"))
     (map #(map (fn [s] (Integer. s)) %))
     (map #(->> (partition 2 %)
                (map (fn [r] (apply range-set r)))))
     (map #(if (= (apply set/intersection %) #{})
             0
             1))
     (reduce +))

