# DSATermProject

Implementation of Dijkstra's algorithm

CONTENTS

I.   GENERAL INFORMATION<br>
II.  DATA STRUCTURE DESIGN<br>
III. Algorithm for finding reachable vertices


I. GENERAL INFORMATION

1.  Programming Language used	: Java<br>
    Java Version		: "1.8.0_71"<br>
    Compiler Version		: major version: 52 - Java 8

2.  Executing the program
	
    Compilation: 	javac graph.java 
    Execution: 		java graph [input file]

3.  Command Line Arguments
	- Input file name
		
4.  Files
	- graph.java : 			main class: prints graph and finds reachable vertices at any given instance
	- Vertex.java :			holds Vertex(es)
	- Edge.java :			holds Edges
	- Dijkstra.java : 		computes shortest path from source to destination
	- PriorityQueue.java :  	functions as a priority queue in Dijkstra's algorithm
	- State.java : 			an enum - holds values UP and DOWN
	- GraphException.java : 	handles exceptions

Example Usage: java graph network.txt
	
<input file>
	(required) Specifies the input file to construct the initial graph	

II. DATA STRUCTURE DESIGN	


1. The list of vertices of the graph is being held in a HashMap, vertexMap. DistanceMap in Dijkstra.java contains the vertices and the minimum distance from the source. It is updated whenever a new minimum distance is found.

2.  Arraylist is used for implementing Min heap which acts as the priority queue for Dijkstra's algorithm.

Efficiency: Using HashMap is efficient since the time complexity for table lookup is O(1) in the average case and O(n) in the worst case.

III. Algorithm for finding reachable vertices


```
for all vertices in the graph				
	if vertex is DOWN
		do nothing
	else
		mark vertex as visited
		add vertex to queue
	end
	while queue is not empty
		get vertex with minimum weight from queue
		for each vertex in adjacency list of vertex 
			if adj vertex is DOWN or if the vertex has already been visited
				do nothing
			else
				add the adj vertex to the queue
				mark the vertex as visited
			end
		end
	end
end
print reachable vertices
```

Time complexity : O(V(V+E))

