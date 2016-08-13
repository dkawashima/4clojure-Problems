;; dkawashima's solution to Count a Sequence #22
;; https://4clojure.com/problem/22

;; Write a function which returns the total number of elements in a sequence.
	
(defn my-count
  [elems]
  (loop [elems elems iterator 0]
    (if (not= (first elems) nil)
      (do
        (recur (rest elems) (inc iterator)))
      iterator
    )))

 (= (my-count '(1 2 3 3 1)) 5)
  
 (= (my-count "Hello World") 11)
  
 (= (my-count [[1 2] [3 4] [5 6]]) 3)

 (= (my-count '(13)) 1)
  
 (= (my-count '(:a :b :c)) 3)