(ns katathursday.namogram)

(defn dictionary []
  (with-open [f (clojure.java.io/reader (clojure.java.io/resource "OpenTaal-210G-basis-gekeurd.txt"))]
    (doall (filter #(< 2 (count %)) (line-seq f)))))

(defn word-occurences [word]
  (frequencies (.toLowerCase word)))

(defn sentence-occurences [sentence]
  (reduce (partial merge-with +) (map word-occurences (clojure.string/split sentence #" |'"))))

(defn dictionary-per-occurence  [word-list]
  (group-by sentence-occurences word-list))

(defn subtract-occurences [x y]
  (into {} (filter #(pos? (second %)) (merge-with - x y))))

(defn character-combination
  ([occurences] (character-combination occurences (list {})))
  ([occurences acc]
   (if-let [[c n] (first occurences)]
       (let [remainders (into {} (rest occurences))
             perms (map #(vector c %) (range 1 (inc n)))
             results (for [a acc
                           r (map #(conj a %) perms)]
                       r)]
         (recur remainders (concat acc results)))
       acc)))

(defn sentence-anagram [sentence]
  (let [dict (dictionary-per-occurence (dictionary))]
    (letfn [(iter [occ]
              (if (empty? occ)
                (list [])
                (for [combination (character-combination occ)
                      word (get dict combination)
                      :let [diff (subtract-occurences occ (sentence-occurences word))]
                      sentence (iter diff)
                      :when ((complement empty?) combination)
                      :when ((complement empty?) word)]
                  (cons word sentence)
                  )))]
      (filter #(>= 2 (count %)) (iter (sentence-occurences sentence))))))
