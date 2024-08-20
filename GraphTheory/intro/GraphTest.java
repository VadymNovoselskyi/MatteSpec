package intro;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphTest {
   	private Graph<String> graph;
   	
   	// Vertices
   	private String A = "A", B = "B", C = "C", D = "D", E = "E";
 
   	// Edges x 2
   	private int a = 5, b = 2, c = 4, d = 1, e = 6, f = 2, g = 3;
 
   	public GraphTest(){
         	createGraph();
 
         	/** DFS */
         	System.out.println("DFS");
         	System.out.print("Start --> ");
         	depthFirstSearch(E);
 
         	System.out.println("\n" +graph.allVisited());
         	graph.setAllUnVisited();
 
         	/** BFS */
         	System.out.println("\nBFS");
         	breadthFirstSearch(A);
         	graph.setAllUnVisited();
 
         	/** Djisktra */
         	String vertexToCheck = C;
         	System.out.println("\n\nShortest path to " +vertexToCheck);
         	Map<String, VertexDistance<String>> pathToA = graph.shortestPath(A);
 
         	System.out.println(pathToA.get(vertexToCheck).getDistance());
         	System.out.println(pathToA.get(vertexToCheck).getPath(pathToA)); 
   	}
 
   	public void createGraph(){
         	graph = new Graph<String>();
 
         	// Add vertices
         	graph.addElement(A);
         	graph.addElement(B);
         	graph.addElement(C);
         	graph.addElement(D);
         	graph.addElement(E);
 
         	// Add edges
         	graph.addEdge(A, B, a);
         	graph.addEdge(A, D, b);
 
         	graph.addEdge(B, A, a);
         	graph.addEdge(B, C, e);
         	graph.addEdge(B, D, c);
         	graph.addEdge(B, E, g);
 
         	graph.addEdge(C, B, e);
         	graph.addEdge(C, E, f);
 
         	graph.addEdge(D, A, b);
         	graph.addEdge(D, B, c);
         	graph.addEdge(D, E, d);
 
         	graph.addEdge(E, B, g);
         	graph.addEdge(E, C, f);
         	graph.addEdge(E, D, d);
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

