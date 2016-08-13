;; dkawashima's solution to #89 Graph Tour
;; https://4clojure.com/problem/89

;; Graphs are vectors of tuples
;; 
;; Starting with a graph you must write a function that returns true if it is 
;; possible to make a tour of the graph in which every edge is visited exactly once.

;; The graph is represented by a vector of tuples, where each tuple represents a single edge.

;; The rules are:

;; - You can start at any node.
;; - You must visit each edge exactly once.
;; - All edges are undirected.

;; Graph Tour for Undirected = Eulerian Path


(defn isConnected-BFShelper
	"Given a root vertex, determines if all other vertices can be visited. Aside
	from a root vertex, takes in a graph, map of [vertices visited], and a queue
	to execute BFS."
  [root graph vmap queue]
  (let [adj (distinct (flatten (map first (filter #(= (second %) true) 
  		(map vector graph (map #(.contains % root) graph))))))]
    (let [newmap (merge vmap (zipmap adj (repeat (count adj) true)))
          newqueue (concat queue (filter #(= (vmap %) false) adj))]
      (if (.contains (vals newmap) false)
        (if (empty? newqueue)
          false
          (BFS (first newqueue) graph newmap (rest newqueue))
        )
        true
      )
	)
  )
)

(defn isConnected
	"Checks if undirected graph is fully connected."
  [graph]
  (let [vertices (distinct (flatten (vec graph)))]
    (if (= (count vs) 0)
      true
      (isConnected-BFShelper (first vs) (vec graph) 
      	(assoc (zipmap vs (repeat (count vs) false)) (first vs) true) [])
    )
  )
)

(defn graphTour
	"Checks if graph has Eulerian Path (is connected and has 0 or 2 vertices 
	with an odd degree."
  [graph]
  (let [oddDeg 
  		(count (filter #(odd? (second %)) ((comp frequencies flatten) graph)))]
    (and (isConnected graph) (or (= oddDeg 2) (= oddDeg 0)))
  )
)

;; Test cases:

(= true (graphTour [[:a :b]]))

(= false (graphTour [[:a :a] [:b :b]]))

(= false (graphTour [[:a :b] [:a :b] [:a :c] [:c :a]
               [:a :d] [:b :d] [:c :d]]))

(= true (graphTour [[1 2] [2 3] [3 4] [4 1]]))

(= true (graphTour [[:a :b] [:a :c] [:c :b] [:a :e]
              [:b :e] [:a :d] [:b :d] [:c :e]
              [:d :e] [:c :f] [:d :f]]))

(= false (graphTour [[1 2] [2 3] [2 4] [2 5]]))