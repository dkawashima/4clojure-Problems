;; dkawashima's solution to Fibonacci Sequence #26
;; https://4clojure.com/problem/26

;; Write a function which returns the first X fibonacci numbers.	

(defn fib
	"Returns a list of the first n Fibonacci numbers."
  [num]
  (loop [i 1 b 0 c 1 list '(1)]
    (if (< i num)
      (recur (inc i) c (+ b c) (conj list (+ b c)))
      (reverse list)
      )
    ))
;; Test Cases:

(= (fib 3) '(1 1 2))	

(= (fib 6) '(1 1 2 3 5 8))	

(= (fib 8) '(1 1 2 3 5 8 13 21))