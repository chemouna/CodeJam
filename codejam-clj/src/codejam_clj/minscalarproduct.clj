
(ns minscalarproduct)

(defn minprod
  "Compute the min scalar product of two vectors."
  [col1 col2]
  (let [s1 (sort col1)
        s2 (reverse (sort col2))]
    (reduce + (map * s1 s2))))

;; (minprod '(1 3 -5) '(-2 4 1))
;; (minprod '(1 2 3 4 5) '(1 0 1 0 1))

