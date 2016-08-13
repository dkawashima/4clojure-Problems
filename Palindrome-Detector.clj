;; Write a function which returns true if the given sequence is a palindrome.

;; Hint: "racecar" does not equal '(\r \a \c \e \c \a \r)

(defn palindrome?
  [elems]
  (if (string? elems)
    (= (clojure.string/reverse elems) elems)
    (= (reverse elems) elems))
  )

;; Test Cases:

(false? (palindrome? '(1 2 3 4 5)))

(true? (palindrome? "racecar"))

(true? (palindrome? [:foo :bar :foo]))

(true? (palindrome? '(1 1 3 3 1 1)))

(false? (palindrome? '(:a :b :c)))