# Trejectory_Replanning

## A* Algorithm  
A* algorithm is a very popular path finding and graph traversal algorithm based on heuristic search. This algorithm finds the shortest path between source node and destination node, and avoids obstacles between those nodes. Many real time video games, robotics, and web based maps use this algorithm to find the shortest path between initial and final state very efficiently. A* is a modified version of Dijksta’s algorithm, which is optimized to find a path to a single final state, instead of multiple locations. This algorithm priority path which appears to be leading closer to a final state.  

#### A* algorithm works based on following three main parameter:
* g-value [g(s)] (Smallest  cost-to-come): The length of the shortest path from the start state to s found by the A* search. It is the sum of the all cells that have been visited before state s.
* h-value [h(s)] (Heuristic value):  The estimated distance between the current state s and final state. 
* f-value [f(s)] : f(s) = g(s) + h(s), estimates the distance from the start state to the gole state via state s.
