;; dkawashima's solution to Number Maze #106
;; https://4clojure.com/problem/106


;; Given a pair of numbers, the start and end point, find a path between the two 
;; using only three possible operations:

;; double
;; halve (odd numbers cannot be halved)
;; add 2

;; Find the shortest path through the "maze". 
;; Because there are multiple shortest paths, you must return the 
;; length of the shortest path, not the path itself.

(defn dijkstras
	"Implements Dijkstra's Algorithm to find length of shortest-path between two vertices.
	Takes in [startvertex pathlength] and endvertex as arguments."
  [curr end]
  (let [start (first (first curr)) scount (second (first curr))]
    (if (= start end)
      scount
      (if (odd? start)
        (recur (conj 
        	(vec (rest curr)) (vector (+ 2 start) (inc scount)) 
        	(vector (* 2 start) (inc scount))
        		) 
        		end
        )
        (recur (conj 
        	(vec (rest curr)) (vector (+ 2 start) (inc scount)) 
        	(vector (* 2 start) (inc scount)) (vector (/ start 2) (inc scount))
        		) 
        		end
        )
      )
    )
  )
)

(defn numberMaze
	"Finds quickest path between 2 numbers, where operations
	are add 2, double, and halve (if number is odd)"
  [start end]
  (dijkstras (vector (vector start 1)) end))

; Test Cases
(= 1 (numberMaze 1 1))  ; 1

(= 3 (numberMaze 3 12)) ; 3 6 12

(= 3 (numberMaze 12 3)) ; 12 6 3

(= 3 (numberMaze 5 9))  ; 5 7 9

(= 9 (numberMaze 9 2))  ; 9 18 20 10 12 6 8 4 2

(= 5 (numberMaze 9 12)) ; 9 11 22 24 12
 
