;; dkawashima's solution to Transitive Closure #84
;; https://4clojure.com/problem/84

;; Write a function which generates the transitive closure of a binary relation.

;; Given [a b] and [b c], the transitive closure set would include [a b], [b c], and [a c]. 
;; The sequences only go one way (e.g. a -> b)

;; The relation will be represented as a set of 2 item vectors.


(defn transitiveClosure
  [setOfTuples]
  (loop [queue (vec setOfTuples) final #{}]
    (if (empty? queue) 
      final
      (let [dest (second (first queue))
            connects (map second (filter #(= (first %) dest) setOfTuples))]
        (if (.contains (map first setOfTuples) dest)
          (recur (concat (rest queue) (map vector (repeat (count connects) (first (first queue))) connects)) (conj final (first queue)))
          (recur (rest queue) (conj final (first queue)))
        )
      )
    )
  )
)

  

(let [divides #{[8 4] [9 3] [4 2] [27 9]}]
  (= (transitiveClosure divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))

(let [more-legs
      #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
  (= (transitiveClosure more-legs)
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))

(let [progeny
      #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
  (= (transitiveClosure progeny)
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]}))