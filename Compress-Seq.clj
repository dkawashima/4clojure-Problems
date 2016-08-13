;; dkawashima's solution to Compress a Sequence #30
;; https://4clojure.com/problem/30

;; Write a function which removes consecutive duplicates from a sequence

(defn compress
  [seq]
  (loop [seq seq list '()]
    (if (not-empty seq)
      (if (not= (second seq) (first seq))
        (recur (rest seq) (conj list (first seq)))
        (recur (rest seq) list))
      (reverse list))) 
  )

;; Test Cases	

(= (apply str (compress "Leeeeeerrroyyy")) "Leroy")	

(= (compress [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))	

(= (compress [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))