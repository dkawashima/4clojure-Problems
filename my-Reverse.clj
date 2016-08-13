;; dkawashima's solution to Reverse a Sequence #23
;; https://4clojure.com/problem/23

;; Write a function which reverses a sequence.	

(defn my-reverse
  [elems]
 (loop [new [] elems elems] 
   (if (not= nil (last elems)) 
     (recur (conj new (last elems)) (butlast elems))
     new
)    
   ))

;; Test Cases

(= (my-reverse [1 2 3 4 5]) [5 4 3 2 1])	

(= (my-reverse (sorted-set 5 7 2 7)) '(7 5 2))	

(= (my-reverse [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])
