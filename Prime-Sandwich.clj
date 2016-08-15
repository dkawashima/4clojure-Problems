;; dkawashima's solution to Prime Sandwich #116
;; https://4clojure.com/problem/116


;; A balanced prime is a prime number which is also the mean of the primes directly before and after 
;; it in the sequence of valid primes. 

;; Create a function which takes an integer n, and returns true iff it is a balanced prime.

(defn primeSandwich?
  [n]
  (loop [lprime 0 uprime 0 primes [] num 2]
    (if (> uprime n)
      (= (/ (+ lprime uprime) 2) n)
      (if (some #(= 0 %) (map #(rem num %) primes))
        (recur lprime uprime primes (inc num))
        (cond 
         (and (> num lprime) (< num n))
           (recur num uprime (conj primes num) (inc num))
         (= num n)
           (recur lprime num (conj primes num) (inc num))
         (= n uprime)
           (recur lprime num (conj primes num) (inc num))
         :else
           false
         )))))

;; Test Cases:

(= false (primeSandwich? 4)) 

(= true (primeSandwich? 563)) 

(= 1103 (nth (filter primeSandwich? (range)) 15))
