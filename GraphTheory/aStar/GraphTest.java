package aStar;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphTest {
   	private Graph<String> graph;
   	
   	// Vertices
   	private String A = "A", B = "B", C = "C", D = "D", E = "E", F = "F", G = "G", H = "H", I = "I", J = "J", K = "K", L = "L";
 
   	// Edges x 2
   	private int a = 5, b = 2, c = 4, d = 1, e = 6, f = 2, g = 3, h = 6, i = 5, j = 4, k = 5, l = 4, m = 3, n = 2, o = 1, p = 3;
 
   	public GraphTest(){
         	createGraph();
         	String vertexToCheck = K;
         	System.out.println("Shortest path to " +vertexToCheck);
         	int shortestPath = graph.shortestPath(A, vertexToCheck);
         	System.out.println(shortestPath);

   	}
 
   	public void createGraph(){
         	graph = new Graph<String>();
 
         	// Add vertices
         	graph.addElement(A, 5, 10);
         	graph.addElement(B, 3, 11);
         	graph.addElement(C, 1, 9);
         	graph.addElement(D, 8, 11);
         	graph.addElement(E, 5, 5);
         	graph.addElement(F, 10, 1);
         	graph.addElement(G, 3, 4);
         	graph.addElement(H, 9, 4);
         	graph.addElement(I, 3, 7);
         	graph.addElement(J, 9, 8);
         	graph.addElement(K, 6, 3);
         	graph.addElement(L, 7, 6);
 
         	// Add edges
         	graph.addEdge(A, B, c); // Edge A-B with weight c (4)
         	graph.addEdge(B, A, c); // Edge B-A with weight c (4)

         	graph.addEdge(A, D, d); // Edge A-D with weight d (1)
         	graph.addEdge(D, A, d); // Edge D-A with weight d (1)

         	graph.addEdge(A, J, h); // Edge A-L with weight h (2)
         	graph.addEdge(J, A, h); // Edge L-A with weight h (2)

         	graph.addEdge(B, C, f); // Edge B-C with weight f (2)
         	graph.addEdge(C, B, f); // Edge C-B with weight f (2)

         	graph.addEdge(B, D, e); // Edge B-D with weight e (6)
         	graph.addEdge(D, B, e); // Edge D-B with weight e (6)

         	graph.addEdge(B, L, b); // Edge B-I with weight b (3)
         	graph.addEdge(L, B, b); // Edge I-B with weight b (3)

         	graph.addEdge(C, I, g); // Edge C-G with weight g (3)
         	graph.addEdge(I, C, g); // Edge G-C with weight g (3)

         	graph.addEdge(K, G, l); // Edge E-G with weight l (4)
         	graph.addEdge(G, K, l); // Edge G-E with weight l (4)

         	graph.addEdge(E, I, n); // Edge E-K with weight n (2)
         	graph.addEdge(I, E, n); // Edge K-E with weight n (2)

         	graph.addEdge(E, L, o); // Edge E-L with weight o (1)
         	graph.addEdge(L, E, o); // Edge L-E with weight o (1)

         	graph.addEdge(F, H, j); // Edge F-H with weight j (4)
         	graph.addEdge(H, F, j); // Edge H-F with weight j (4)

         	graph.addEdge(F, K, k); // Edge F-K with weight k (5)
         	graph.addEdge(K, F, k); // Edge K-F with weight k (5)

         	graph.addEdge(G, I, m); // Edge G-I with weight m (3)
         	graph.addEdge(I, G, m); // Edge I-G with weight m (3)

         	graph.addEdge(H, J, i); // Edge H-J with weight i (5)
         	graph.addEdge(J, H, i); // Edge J-H with weight i (5)

         	graph.addEdge(J, L, p); // Edge J-L with weight p (3)
         	graph.addEdge(L, J, p); // Edge L-J with weight p (3)
         	
         	graph.addEdge(K, H, a); // Edge K-H with weight a (5)
         	graph.addEdge(H, K, a); // Edge H-K with weight a (5)
   	}
 
   	public void depthFirstSearch(String start) {
         	System.out.print(start +"\n");
 
         	Map<String, Integer> adjacencyMap = graph.getNeighbors(start);
 
         	graph.setVisited(start);
 
         	for(String key : adjacencyMap.keySet()) {
            	if(!graph.isVisited(key)){
                  	//System.out.println(key +" - " +graph.getWeight(start, key));
                  	depthFirstSearch(key);
            	}
         	}
   	}
 
   	public void breadthFirstSearch(String start) {
         	graph.setVisited(start);
         	Queue<String> queue = new LinkedList<String>();
         	queue.add(start);
 
         	System.out.print("Start -> ");
 
         	while(!queue.isEmpty()) {
                	String element = queue.poll();
                	System.out.print(element);
 
                	Map<String, Integer> neighborHood = graph.getNeighbors(element);
 
                	for(String neighbor : neighborHood.keySet()){
                      	if(!graph.isVisited(neighbor)){
                             	queue.add(neighbor);
                             	graph.setVisited(neighbor);
                      	}
                	}
                	if(!queue.isEmpty())
                      	System.out.print(" --> ");
         	}
   	}
 
   	public static void main(String[] args) {
         	new GraphTest();
   	}
 
}

