(require '[clojure.string :as str])

(def input
  (->> (slurp "input")
       (#(str/split % #"\n"))))

(defn som-marker
  [s]
  (loop [n 14
         l s]
    (let [[head tail] (split-at 14 l)]
      (if (or (empty? tail) (= 14 (count (set head))))
        n
        (recur (inc n) (rest l))))))

(map som-marker input)
