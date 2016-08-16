;; dkawashima's solution to Digits and Bases #137
;; https://4clojure.com/problem/137

;; Write a function which returns a sequence of digits of a non-negative number 
;; (first argument) in numerical system with an arbitrary base (second argument). 
;; Digits should be represented with their integer values, e.g. 15 would be [1 5] in base 10, 
;; [1 1 1 1] in base 2 and [15] in base 16. 

(defn powersUnderNum
    "Returns lazy-sequence of all n^(power * k), where k continues to be incremented until
    n^(power * k) > num."
  [n power num]
  (when (<= n num)
      (cons n (lazy-seq (powersUnderNum (* n power) power num)))
  )
)

(defn convertToBase
  "Converts number to vector in base provided."
  [num base]
  (loop [powers (powersUnderNum base base num) currnum num  finvec []]
    (if (empty? powers)
      (conj finvec (rem num base))
      (let [quo (unchecked-divide-int currnum (last powers))]
          (recur (butlast powers) (- currnum (* (last powers) quo)) (conj finvec quo))
      )
    )  
  )
)

;; Test Cases:

(= [1 2 3 4 5 0 1] (convertToBase 1234501 10))

(= [0] (convertToBase 0 11))

(= [1 0 0 1] (convertToBase 9 2))

(= [1 0] (let [n (rand-int 100000)](convertToBase n n)))

(= [16 18 5 24 15 1] (convertToBase Integer/MAX_VALUE 42))