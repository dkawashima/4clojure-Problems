;; dkawashima's solution to Identify Keys and Values #105
;; https://4clojure.com/problem/105


;; Given an input sequence of keywords and numbers, create a map such that each key in the map is a keyword, 
;; and the value is a sequence of all the numbers (if any) between it and the next keyword in the sequence.

(defn parseKeysandVals
  [vect]
  (loop [lis (partition-by keyword vect) finmap {}]
    (if (empty? lis)
      finmap
      (if (keyword? (first (second lis)))
        (recur (rest lis)   (assoc finmap (first (first lis)) []))
        (recur (drop 2 lis) (assoc finmap (first (first lis)) (vec (second lis)))) 
      )
    )
  )
)
	
;; Test cases:

(= {} (parseKeysandVals []))	

(= {:a [1]} (parseKeysandVals [:a 1]))	

(= {:a [1], :b [2]} (parseKeysandVals [:a 1, :b 2]))	

(= {:a [1 2 3], :b [], :c [4]} (parseKeysandVals [:a 1 2 3 :b :c 4]))