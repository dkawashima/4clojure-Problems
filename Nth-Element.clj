;; dkawashima's solution to Nth Element #21
;; https://4clojure.com/problem/21

;; Write a function which returns the Nth element from a sequence.

;; Test cases:
;; (= (__ '(4 5 6 7) 2) 6)	

;; (= (__ [:a :b :c] 0) :a)

;; (= (__ [1 2 3 4] 1) 2)	

;; (= (__ '([1 2] [3 4] [5 6]) 2) [5 6])

;; %1 is first argument (sequence of values), %2 is second argument (n)
#(first (drop %2 %1))