(require '[clojure.string :as str])

(def input
  (->> (slurp "input")
       (#(str/split % #"\n\n"))))

(defn start
  [s]
  (->> (str/split s #"\n")
       drop-last
       (map #(partition-all 4 %))
       (map #(map second %))
       (apply map vector)
       (map #(remove #{\space} %))
       (zipmap (rest (range)))))

(defn procedure
  [s]
  (->> (str/split s #"\n")
       (map #(re-matches #"move (\d+) from (\d+) to (\d+)" %))
       (map rest)
       (map #(map (fn [s] (Integer. s)) %))))

(loop [p (procedure (second input))
       stacks (start (first input))]
  #_(prn stacks p)
  (if (empty? p)
    (apply str (for [x (range 1 (+ (count stacks) 1))] (first (stacks x))))
    (recur (rest p)
           (let [[move from to] (first p)
                 [m fs] (split-at move (stacks from))
                 ts (apply conj (stacks to) m)]
             (conj (conj stacks {from fs}) {to ts})))))

