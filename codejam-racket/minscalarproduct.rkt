#lang racket

(define (mult lst1 lst2)
  (cond
      [(empty? lst1) 0]
      [else (+ (* (first lst1) (first lst2)) (mult (rest lst1)(rest lst2)))]
  ))

(define (minscalarprod lst1 lst2)
  (let ([s1 (sort lst1 <)]
        [s2 (sort lst2 >)])
        (mult s1 s2)
    ))