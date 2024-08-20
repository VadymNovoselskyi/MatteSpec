package intro;

import java.util.HashMap;
import java.util.Map;

public class Vertex<T> {
	private T element;
	public boolean visited = false;
	
	private Map<Vertex<T>, Edge<T>> adjacenyMap = new HashMap<Vertex<T>, Edge<T>>();
	
	public Vertex(T element) {
		this.element = element;
	}
	
	public T getElement() {
		return element;
	}
 
    public int getWeight(Vertex<T> vertex){ 
	   Edge<T> edge = adjacenyMap.get(vertex);
	   return edge.getWeight();
    }
    
    public Map<Vertex<T>, Edge<T>> getNeighbors(){ 
	   return adjacenyMap;
    }
	
	public void addNeighbor(Vertex<T> neighbor, int weight) {
		Edge<T> newEdge = new Edge<T>(this, neighbor, weight);
		adjacenyMap.put(neighbor, newEdge);
	}
	
    public Edge<T> removeNeighbor(Vertex<T> neighbor){ 
    	return adjacenyMap.remove(neighbor);
    }
	
}
