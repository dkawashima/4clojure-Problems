;; dkawashima's solution to Triangle Minimal Path #79
;; https://4clojure.com/problem/79

;; Write a function which calculates the sum of the minimal path through a triangle. 
;; The triangle is represented as a collection of vectors. 
;; The path should start at the top of the triangle and move to an adjacent number on the next row until the bottom of the triangle is reached.

(defn advanceRow
  "Recursively moves across a row, replacing values of next row with values including the path length of previous row."
  [prev_row next_row] 
  (loop [prev prev_row new_row (vector (+ (first prev_row) (first next_row))) iter 1]
    (if (empty? (rest prev))
      (conj new_row (+ (last prev_row) (last next_row)))
      (recur (rest prev) (conj new_row (+ (min (first prev) (second prev)) (nth next_row iter))) (inc iter))

))
  )

(defn shortest-path
  [listofvec]
  (loop [l (rest listofvec) curr_row (first listofvec)]
    (if (empty? l)
      (apply min curr_row)
      (recur (rest l) (advanceRow curr_row (first l)))))
  )

;; Test Cases:

(= 7 (shortest-path '([1]
          [2 4]
         [5 1 4]
        [2 3 4 5]))) ; 1->2->1->3
test not run  

(= 20 (shortest-path '([3]
           [2 4]
          [1 9 3]
         [9 9 2 4]
        [4 6 6 7 8]
       [5 7 3 5 1 4]))) ; 3->4->3->2->7->1

