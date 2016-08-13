;; dkawashima's solution to Last Element #19
;; https://4clojure.com/problem/19

;; Write a function which returns the last element in a sequence.	

;; Test Cases:
;; (= (__ [1 2 3 4 5]) 5)

;; (= (__ '(5 4 3)) 3)

;; (= (__ ["b" "c" "d"]) "d")

#(first (reverse %))