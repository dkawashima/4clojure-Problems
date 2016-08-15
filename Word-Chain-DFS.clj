;; dkawashima's solution to Word Chains #82
;; https://4clojure.com/problem/82

;; A word chain consists of a set of words ordered so that each word differs by only one letter from the words 
;; directly before and after it. The one letter difference can be either an insertion, a deletion, or a substitution. 

;; Here is an example word chain:

;; cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog
;; Write a function which takes a sequence of words, and returns true if 
;; they can be arranged into one continous word chain, and false if they cannot.

(defn isWordEdge
  "Given two words, determine if they have an 'edge' (one letter difference) between them."
  [w1 w2]
 (cond (= (count w1) (count w2))
         (<= ((frequencies (map = w1 w2)) false) 1)
       (> (count w1) (count w2))
          (let [splitw (map split-at (range (count w1)) (repeat (count w1) w1) )]
            (.contains (map concat (map first splitw) (map (comp rest second) splitw)) (vec w2))
          )
       :else
          (if (vector? w1)
        ;; This is only a vector if specifically inputted to run longestPath for every word
        ;; All words will end up in the adj vector in longestPath in this case
            true
            (let [splitw (map split-at (range (count w2)) (repeat (count w2) w2) )]
              (.contains (map concat (map first splitw) (map (comp rest second) splitw)) (vec w1))
            )
          )
  )
)

(defn longestPath
  "Given a root word, and all other possible words, and the length of a word chain leading up to that
  word, returns the length of the longest word chain that can be created. Recursively calls itself."
  [root otherwords countprev]
  (let [adj (filter #(isWordEdge root %) otherwords)]
    (loop [ladj adj currmax countprev]
      (if (empty? ladj)
        currmax
        (let [resul (longestPath (first ladj) (filter #(not= (first ladj) %) otherwords) (inc countprev))]
          (if (> resul currmax)
            (recur (rest ladj) resul)
            (recur (rest ladj) currmax)
          )
        )
      )
    )
  )
     
)

(defn wordChain
    "Given a set of words, is there a word chain that contains every word in the set?"
  [setofWords]
  (= (count setofWords) (longestPath [] (vec setofWords) 0)) 
)

;; Test Cases:

(= true (wordChain #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})) 

(= false (wordChain #{"cot" "hot" "bat" "fat"})) 

(= false (wordChain #{"to" "top" "stop" "tops" "toss"})) 

(= true (wordChain #{"spout" "do" "pot" "pout" "spot" "dot"})) 

(= true (wordChain #{"share" "hares" "shares" "hare" "are"})) 

(= false (wordChain #{"share" "hares" "hare" "are"}))
