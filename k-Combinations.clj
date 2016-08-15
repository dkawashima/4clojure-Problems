;; dkawashima's solution to Generating k-Combinations #103
;; https://4clojure.com/problem/103

;; Given a sequence S consisting of n elements generate all k-combinations of S, 
;; i. e. generate all possible sets consisting of k distinct elements taken from S. 
;; The number of k-combinations for a sequence is equal to the binomial coefficient.

(defn powerset
  "Uses a bit vector to generate the power set of a given set."
  [s]
  (loop [bitv (dec (int (Math/pow 2 (count s)))) finalset #{} partset #{} i 0]
    (if (>= bitv 0)
      (if (< i (count s))
        (if (bit-test bitv i)
          (recur bitv finalset (conj partset (nth (vec s) i)) (inc i))
          (recur bitv finalset partset (inc i))
        )
        (recur (dec bitv) (conj finalset partset) #{} 0)
      )
      finalset
    )
  )
)

(defn kCombo
	"Utilizes powerset function get specific k-combinations of set."
  [numper elems]
  (set (filter #(= (count %) numper) (powerset elems)))
)

;; Test Cases

(= (kCombo 1 #{4 5 6}) #{#{4} #{5} #{6}})

(= (kCombo 10 #{4 5 6}) #{})

(= (kCombo 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}})

(= (kCombo 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                         #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})

(= (kCombo 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})

(= (kCombo 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                    #{:a "abc"} #{:a "efg"} #{"abc" "efg"}})