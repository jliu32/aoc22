(require '[clojure.string :as str])

(def input
  (->> (slurp "input")
       (#(str/split % #"\n"))))

(defn sop-marker
  [s]
  (loop [n 4
         l s]
    (let [[head tail] (split-at 4 l)]
      (if (or (empty? tail) (= 4 (count (set head))))
        n
        (recur (inc n) (rest l))))))

(map sop-marker input)
