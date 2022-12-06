(require '[clojure.string :as str])

(->> (slurp "input")
     (#(str/split % #"\n\n"))
     (map #(str/split-lines %))
     (map (fn [l] (map #(Integer. %) l)))
     (map #(reduce + %))
     (sort >)
     (take 3)
     (reduce +))
