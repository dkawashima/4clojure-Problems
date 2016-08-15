;; dkawashima's solution to Write Roman Numerals #104
;; https://4clojure.com/problem/104

;; This is the inverse of Problem 92, but much easier. 
;; Given an integer smaller than 4000, return the corresponding roman numeral in uppercase, adhering to the subtractive principle.

(defn writeRomanNumerals
  [num]
  (let [m (sorted-map-by > 1000 "M", 900 "CM", 500 "D", 400 "CD", 100 "C", 90 "XC", 50 "L", 40 "XL", 10 "X", 9 "IX", 5 "V", 4 "IV", 1 "I")]
    (loop [result [] currnum num topVal 1000 currm m]
        (if (>= currnum topVal)
          (recur (conj result (m topVal)) (- currnum topVal) topVal currm)
          (if (= currnum 0)
            (apply str result)
            (recur result currnum ((comp first keys rest) currm) (rest currm))
          )
        )
    )
  )
)

;; Test Cases:

(= "I" (writeRomanNumerals 1))

(= "XXX" (writeRomanNumerals 30))

(= "IV" (writeRomanNumerals 4))

(= "CXL" (writeRomanNumerals 140))

(= "DCCCXXVII" (writeRomanNumerals 827))

(= "MMMCMXCIX" (writeRomanNumerals 3999))

(= "XLVIII" (writeRomanNumerals 48))