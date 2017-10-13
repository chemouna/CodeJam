#lang racket

(define-syntax while
  (syntax-rules ()
    ((_ pred? stmt ...)
     (do () ((not pred?))
      stmt ...))))

(define (intersperse seperator xs)
    (cond
       [(null? xs) '()]
       [(null? (cdr xs)) xs]
       [else (cons (car xs)
                  (cons seperator
                      (intersperse seperator (cdr xs))))]))

;(define (main args)
;  (let loop 
;    ((n (read-skip-line)) (i 1))
;    (solve i)
;    (if (< i n) (loop n (+ i 1)))
;    (else (error "error"))))

(define (read-skip-line n)
  (begin0 (read) (read-line)))

(define (read-n-line n)
  (if (= n 0)
      '()
      (cons (read-line) (read-n-line (- n 1)))))

(define infitinity 1000000)

(define (solve i)
  (let* ((N read-skip-line)
         (M read-skip-line)
         (customers (map (lambda (x)
                           (let
                               ((malted  #f)
                                (unmalted '())
                                (input (map string->number (cdr (string-split x " ")))))
                             (for-each (filter pair? input)
                                    (if (= (cadr input) 1)
                                        (set! malted (car input))
                                        (set! unmalted (cons (car input) unmalted)))
                                    (set! input (cddr input)))
                             (cons malted unmalted))) ;; why ?
                         (read-n-line M)))
         (sol (make-vector N #f))
         (flag #t)
         (impossible #f))
    (define (test-customer cust)
      (define (exist unmalted)
        (if (pair? unmalted)
            (if (not (vector-ref sol (- (car unmalted) 1)))
                #t
                (exist (cdr unmalted)))
            #f))
      (let ((unmalted (cdr cust))
            (malted (car cust)))
        (if (and malted (vector-ref sol (- malted 1))) 
          #t
          (if (exist unmalted)
            #t
            (begin 
              (if malted 
                (begin (set! flag #t) (vector-set! sol (- malted 1) #t))
                (set! impossible #t) 
                ))))))
    (while flag
      (set! flag #f)
      (for-each 
        test-customer customers))
    (if impossible 
      (format #t "Case #~a: IMPOSSIBLE\n" i)
      (format #t "Case #~a: ~a\n" i (apply string-append (intersperse " " (map (lambda (x) (if x "1" "0")) sol)))))))
    
                             
                                