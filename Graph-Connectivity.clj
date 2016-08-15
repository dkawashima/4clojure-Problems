;; dkawashima's solution to Graph Connectivity #91
;; https://4clojure.com/problem/91

;; Given a graph, determine whether the graph is connected. A connected graph is such that a path exists between any two given nodes.

;; -Your function must return true if the graph is connected and false otherwise.

;; -You will be given a set of tuples representing the edges of a graph. Each member of a tuple being a vertex/node in the graph.

;; -Each edge is undirected (can be traversed either direction). 

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

;; Test Cases:


(= true (isConnected #{[:a :a]}))

(= true (isConnected #{[:a :b]}))

(= false (isConnected #{[1 2] [2 3] [3 1]
               [4 5] [5 6] [6 4]}))

(= true (isConnected #{[1 2] [2 3] [3 1]
              [4 5] [5 6] [6 4] [3 4]}))

(= false (isConnected #{[:a :b] [:b :c] [:c :d]
               [:x :y] [:d :a] [:b :e]}))

(= true (isConnected #{[:a :b] [:b :c] [:c :d]
              [:x :y] [:d :a] [:b :e] [:x :a]}))
