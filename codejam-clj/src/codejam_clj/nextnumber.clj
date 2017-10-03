
(ns codejam-clj.nextnumber)

(use 'clojure.math.combinatorics)

(defn permute-number [n]
  (map #(read-string (.replaceFirst (apply str %) "^0+" ""))
       (permutations (seq (str n)))))

(defn next-number [n m]
  (first (filter #(> % m) (sort < (permute-number n)))))

(defn find-next-number [n]
  (let [i (next-number n n)]
    (if i
      i
      (next-number (* n 10) n))))

(doseq [i (range 1 (+ (read) 1))]
  (println "Case" (str "#" i ":") (find-next-number (read))))
