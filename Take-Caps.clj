;; dkawashima's solution to Get the Caps #29
;; https://4clojure.com/problem/29

;; Write a function which takes a string and returns a new string containing only the capital letters.	

(defn takeCaps
  [x] 
  (apply str (re-seq #"[A-Z]+" x))
  )

(= (takeCaps "HeLlO, WoRlD!") "HLOWRD")	

(empty? (takeCaps "nothing"))	

(= (takeCaps "$#A(*&987Zf") "AZ")