;; dkawashima's solution to Read Roman Numerals #92
;; https://4clojure.com/problem/92

;; Roman numerals are easy to recognize, but not everyone knows all the rules necessary to work with them. 
;; Write a function to parse a Roman-numeral string and return the number it represents. 

;; You can assume that the input will be well-formed, in upper-case, and follow the subtractive principle. 
;; You don't need to handle any numbers greater than MMMCMXCIX (3999), the largest number representable with ordinary letters

(defn parseRomanNumerals
  [str]
  (let [m (hash-map \M 1000, \D 500, \C 100, \L 50, \X 10, \V 5, \I 1, [\I \V] 4, [\I \X] 9, [\C \M] 900, [\C \D] 400, [\X \L] 40, [\X \C] 90)]
    (loop [arr (vec str) sum 0]
      (if (empty? arr)
        sum
        (if (.contains (keys m) (take 2 arr))
          (recur (drop 2 arr) (+ sum (m (take 2 arr))))
          (recur (rest arr) (+ sum (m (first arr))) )
        )
      )
    )
  )
)

;; Test Cases:

(= 14 (parseRomanNumerals "XIV"))  

(= 827 (parseRomanNumerals "DCCCXXVII"))  

(= 3999 (parseRomanNumerals "MMMCMXCIX"))  

(= 48 (parseRomanNumerals "XLVIII"))
 
