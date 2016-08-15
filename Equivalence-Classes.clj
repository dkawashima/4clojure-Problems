;; dkawashima's solution to Equivalence Classes #98
;; https://4clojure.com/problem/98

;; A function f defined on a domain D induces an equivalence relation on D, as follows: 
;; a is equivalent to b with respect to f if and only if (f a) is equal to (f b). 

;; Write a function with arguments f and D that computes the equivalence classes of D with respect to f.

(defn equivClasses
	"Takes in a function and a set of values (domain), in order to compute equivalence classes"
  [f domain]
  ;; This could be simpler if group-by function was used.
  (loop [fvals (distinct (map f domain)) result #{}]
    (if (empty? fvals)
       result
      (recur (rest fvals) (conj result (set (filter #(= (f %) (first fvals)) domain))))
    )
  )
)

;; Test Cases:

(= (equivClasses #(* % %) #{-2 -1 0 1 2})
   #{#{0} #{1 -1} #{2 -2}})	

(= (equivClasses #(rem % 3) #{0 1 2 3 4 5 })
   #{#{0 3} #{1 4} #{2 5}})	

(= (equivClasses identity #{0 1 2 3 4})
   #{#{0} #{1} #{2} #{3} #{4}})	

(= (equivClasses (constantly true) #{0 1 2 3 4})
   #{#{0 1 2 3 4}})
