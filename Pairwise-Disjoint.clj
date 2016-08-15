;; dkawashima's solution to Pairwise Disjoint Sets #153
;; https://4clojure.com/problem/153

;; Given a set of sets, create a function which returns true if no two of those sets have any elements in common 
;; and false otherwise. Some of the test cases are a bit tricky, so pay a little more attention to them.

;; Such sets are usually called pairwise disjoint or mutually disjoint.

(defn pairwiseDisjoint?
  [set]
  (= (distinct (apply concat set)) (apply concat set)))

;; Test Cases:

(= (pairwiseDisjoint? #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
   true)	

(= (pairwiseDisjoint? #{#{:a :b :c :d :e}
         #{:a :b :c :d}
         #{:a :b :c}
         #{:a :b}
         #{:a}})
   false)	

(= (pairwiseDisjoint? #{#{[1 2 3] [4 5]}
         #{[1 2] [3 4 5]}
         #{[1] [2] 3 4 5}
         #{1 2 [3 4] [5]}})
   true)	

(= (pairwiseDisjoint? #{#{'a 'b}
         #{'c 'd 'e}
         #{'f 'g 'h 'i}
         #{''a ''c ''f}})
   true)	

(= (pairwiseDisjoint? #{#{'(:x :y :z) '(:x :y) '(:z) '()}
         #{#{:x :y :z} #{:x :y} #{:z} #{}}
         #{'[:x :y :z] [:x :y] [:z] [] {}}})
   false)	

(= (pairwiseDisjoint? #{#{(= "true") false}
         #{:yes :no}
         #{(class 1) 0}
         #{(symbol "true") 'false}
         #{(keyword "yes") ::no}
         #{(class '1) (int \0)}})
   false)	

(= (pairwiseDisjoint? #{#{distinct?}
         #{#(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}})
   true)	

(= (pairwiseDisjoint? #{#{(#(-> *)) + (quote mapcat) #_ nil}
         #{'+ '* mapcat (comment mapcat)}
         #{(do) set contains? nil?}
         #{, , , #_, , empty?}})
   false)