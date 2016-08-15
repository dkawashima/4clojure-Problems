;; dkawashima's solution to Power Set #85
;; https://4clojure.com/problem/85

;; Write a function which generates the power set of a given set. 
;; The power set of a set x is the set of all subsets of x, including the empty set and x itself.


(defn powerset
  "Uses a bit vector to generate the power set of a given set."
  [s]
  (loop [bitv (dec (int (Math/pow 2 (count s)))) finalset #{} partset #{} i 0]
    (if (>= bitv 0)
      (if (< i (count s))
        (if (bit-test bitv i)
          (recur bitv finalset (conj partset (nth (vec s) i)) (inc i))
          (recur bitv finalset partset (inc i))
        )
        (recur (dec bitv) (conj finalset partset) #{} 0)
      )
      finalset
    )
  )
)

;; Test Cases

(= (powerset #{1 :a}) #{#{1 :a} #{:a} #{} #{1}}) 

(= (powerset #{}) #{#{}}) 

(= (powerset #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}}) 

(= (count (powerset (into #{} (range 10)))) 1024)