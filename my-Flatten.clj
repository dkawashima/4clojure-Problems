;; dkawashima's solution to Flatten a Sequence #28
;; https://4clojure.com/problem/28

;; Write a function which flattens a sequence.

(defn my-flatten 
  [seq]
  (loop [seq seq list1 '()]
    (if (not-empty seq)
      (if (sequential? (first seq))
        (recur (rest seq) (concat list1 (my-flatten (first seq))
                           ))
        (recur (rest seq) (concat list1 (list (first seq))))
        )
      list1))
  
  )

;; Test Cases: 
(= (my-flatten '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))

(= (my-flatten ["a" ["b"] "c"]) '("a" "b" "c"))

(= (my-flatten '((((:a))))) '(:a))