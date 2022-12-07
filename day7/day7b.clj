(require '[clojure.string :as str])
(require '[clojure.walk :as w])

(def input
  (->> (slurp "input")
       (#(str/split % #"\n"))))

(def cd (re-pattern #"\$ cd ([^\/. ]+)"))
(def up (re-pattern #"\$ cd \.\."))
(def root (re-pattern #"\$ cd \/"))
(def ls (re-pattern #"\$ ls"))
(def directory (re-pattern #"dir (.*)"))
(def file (re-pattern #"(\d+) (.*)"))

(defn dir-tree
  [input]
  (loop [path [:/]
         tree {:/ {:size 0}}
         l input]
    (let [f (first l)
          pwd (get-in tree path)]
      (if (empty? l)
        tree
        (cond
          (re-matches cd f) (let [d (keyword (last (re-matches cd f)))]
                              (recur
                               (conj path d)
                               (if (d pwd) tree (assoc-in tree path (conj pwd {d {:size 0}})))
                               (rest l)))
          (re-matches up f) (recur
                             (pop path)
                             tree
                             (rest l))
          (re-matches root f) (recur
                               [:/]
                               tree
                               (rest l))
          (re-matches file f) (let [[size name] (rest (re-matches file f))]
                                (recur
                                 path
                                 (loop [p path
                                        t tree]
                                   (if (empty? p)
                                     t
                                     (recur (pop p)
                                            (update-in t (conj p :size) + (Integer. size)))))
                                 (rest l)))
          :else
          (recur path tree (rest l)))))))

(defn size-tree
  [t]
  (let [dirs (remove #{:size} (keys t))
        s (:size t)]
    (if (empty? dirs)
      s
      (conj [s] (vec (for [d dirs] (size-tree (d t))))))))
           
(def target
  (->> input
       dir-tree
       :/
       size-tree
       flatten
       (sort >)
       first
       (- 70000000)
       (- 30000000)))

(->> input
       dir-tree
       :/
       size-tree
       flatten
       sort
       (drop-while #(> target %))
       first)
