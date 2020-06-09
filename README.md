# Trejectory_Replanning

## A* Algorithm  
A* algorithm is a very popular path finding and graph traversal algorithm based on heuristic search. This algorithm finds the shortest path between source node and destination node, and avoids obstacles between those nodes. Many real time video games, robotics, and web based maps use this algorithm to find the shortest path between initial and final state very efficiently. A* is a modified version of Dijksta’s algorithm, which is optimized to find a path to a single final state, instead of multiple locations. This algorithm priortize path which appears to be leading closer to a final state.  

#### A* algorithm works based on following three main parameter:
* g-value [g(s)] (Smallest  cost-to-come): The length of the shortest path from the start state to s found by the A* search. It is the sum of the all cells that have been visited before state s.
* h-value [h(s)] (Heuristic value):  The estimated distance between the current state s and final state. 
* f-value [f(s)] : f(s) = g(s) + h(s), estimates the distance from the start state to the gole state via state s.  

The  A* algorithm finds a shortest path, by taking the f-value into account. The algorithm selects the smallest f-value node and moves to that node. The process continues until the algorithm reaches its destination node.  

## Description
The program is designed to test and visualize and improve A* algorithms with different parameters and modification. The program creates a 100 X 100 square grid with a start and goal node and also having several obstacles, scattered randomly. The program creates 50 grids and stores them in a .txt file to be used by later commands. The user can select the type of A* algorithms out of nine different options, to find the shortest path between start and goal node. Program outputs GUI tab with 100 X 100 maze and calculated path from source to destination node.  

## Usage
The program can be run by using following commands in terminal:
```
javac main.java
java main
```
These command will allows user to choose one of the options from following menu:  
<img src="/Images/menu.PNG" width="300">

### Sample Outputs    

<img src="/Images/Option_2_Grid_30.PNG" width="260"> <img src="/Images/Option_6_Grid_45.PNG" width="260" /> <img src="/Images/Option_10_Grid_19.PNG" width="260">

