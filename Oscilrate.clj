;; dkawashima's solution to Oscilrate #144
;; https://4clojure.com/problem/144


;; Write an oscillating iterate: a function that takes an initial value and a variable number of functions. 
;; It should return a lazy sequence of the functions applied to the value in order, 
;; restarting from the first function after it hits the end.

(defn oscilrate-helper
  [val fns]
  (cons val (lazy-seq (oscilrate-helper ((first fns) val) (conj (vec (rest fns)) (first fns)))))
  )


(defn oscilrate
  [val & fns]
   (cons val (lazy-seq (oscilrate-helper ((first fns) val) (conj (vec (rest fns)) (first fns)))))
)

;; Test-Cases

(= (take 3 (oscilrate 3.14 int double)) [3.14 3 3.0]

(= (take 5 (oscilrate 3 #(- % 3) #(+ 5 %))) [3 0 5 2 7]

(= (take 12 (oscilrate 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3])
 
