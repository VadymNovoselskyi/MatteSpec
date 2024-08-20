package intro;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class Graph<T> {
	 
   	private Map<T, Vertex<T>> vertexMap = new HashMap<T,Vertex<T>>();
 
   	public Graph() { }
 
   	public void addElement(T element) {
   		Vertex<T> newVertex = new Vertex<T>(element);
   		vertexMap.put(element, newVertex);
   	}
   	
   	public void addElement(T element, int x, int y) {
   		Vertex<T> newVertex = new Vertex<T>(element, x, y);
   		vertexMap.put(element, newVertex);
   	}
 
   	public void addEdge(T from, T to, int weight) {
   		Vertex<T> fromVertex = vertexMap.get(from);
   		Vertex<T> toVertex = vertexMap.get(to);
   		
   		fromVertex.addNeighbor(toVertex, weight);
   	}
 
   	public void removeVertex(T element) { 
   		Vertex<T> vertexToRemove = vertexMap.get(element);
   		Map<Vertex<T>, Edge<T>> neighborsMap = vertexToRemove.getNeighbors();
   		
   		for (Vertex<T> neighbor : neighborsMap.keySet()) {
   			vertexToRemove.removeNeighbor(neighbor);
   			neighbor.removeNeighbor(neighbor);
   		}
   		
   	}
 
   	public void removerEdge(T from, T to) {
   		Vertex<T> fromVertex = vertexMap.get(from);
   		Vertex<T> toVertex = vertexMap.get(to);
   		
   		fromVertex.removeNeighbor(toVertex);
   	}
 
    public boolean allVisited() { 
   		for (Vertex<T> vertex : vertexMap.values()) {
   			if(!vertex.visited) return false;
   		}
   		return true;
    }
 
    public void setAllUnVisited() {
   		for (Vertex<T> vertex : vertexMap.values()) {
   			vertex.visited = false;
   		}
    }
       
    public void setVisited(T element) { 
   		Vertex<T> vertex = vertexMap.get(element);
   		vertex.visited = true;
    }
 
    public boolean isVisited(T element) { 
   		Vertex<T> vertex = vertexMap.get(element);
   		boolean isVisited = vertex.visited;
   		
   		return isVisited;
    }

   	public int getWeight(T from, T to) {
   		Vertex<T> fromVertex = vertexMap.get(from);
   		Vertex<T> toVertex = vertexMap.get(to);
   		return fromVertex.getWeight(toVertex);
   	}
 
   	public boolean adjacent(T from, T to){
   		Vertex<T> fromVertex = vertexMap.get(from);
   		Vertex<T> toVertex = vertexMap.get(to);
   		
   		Map<Vertex<T>, Edge<T>> neighborsMap = fromVertex.getNeighbors();
   		
   		for (Vertex<T> neighbor : neighborsMap.keySet()) {
   			if(neighbor == toVertex) return true;
   		}
   		return false;
   	}
 
   	public Map<T,Integer> getNeighbors(T element) {
   		Vertex<T> vertex = vertexMap.get(element);
   		Map<Vertex<T>, Edge<T>> neighborsMap = vertex.getNeighbors();
   		
   		Map<T, Integer> neighborsWeights = new HashMap<T, Integer>();
   		for (Vertex<T> neighbor : neighborsMap.keySet()) {
   			neighborsWeights.put(neighbor.getElement(), vertex.getWeight(neighbor));
   		}
   		return neighborsWeights;
   	}
   	
   	
   	public int shortestPathA(T startVertex, T endVertex) {
   		Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.getApproxDistance(), e2.getApproxDistance()));
   		Map<T, VertexDistance<T>> distanceMap = new HashMap<>();
   		
   		for (T vertex : vertexMap.keySet()) { //sätta avstånd 99 till alla vertex
   			VertexDistance<T> distance = new VertexDistance<T>(vertex, Integer.MAX_VALUE);
   			distanceMap.put(vertex, distance);
   		}
   		distanceMap.get(startVertex).setDistance(0); //utgångspunkten befinner sig 0 ifrån sigsjälv
   		
   		priorityQueue.add(distanceMap.get(startVertex));
   		
   		while(!priorityQueue.isEmpty()) {
   			VertexDistance<T> fromVertex = priorityQueue.poll();
   			T from = fromVertex.getVertex();
 
   			if(from == endVertex) {
   				System.out.println(distanceMap.get(endVertex).getPath(distanceMap));
   				return distanceMap.get(endVertex).getDistance();
   			}
   			
   			Map<T,Integer> neighborsMap = getNeighbors(from);
   			
   			for(T to : neighborsMap.keySet()) {
   				if(!isVisited(to)) {
   					int edgeWeight = getWeight(from, to);
   					int newDistance = fromVertex.getDistance() + edgeWeight;
   					int approxDistance = fromVertex.getDistance() + edgeWeight + heuristic(from, to);
   					
   					VertexDistance<T> toVertex = distanceMap.get(to);
   					
   					if(toVertex.getApproxDistance() > approxDistance) {
   						toVertex.setDistance(newDistance);
   						toVertex.setApproxDistance(approxDistance);
   						toVertex.setPrevious(from);
   						
   						priorityQueue.add(toVertex);   						
   					}
   				}
   			}
   			setVisited(from);
   		}
   		System.out.println("No path found");
   		return Integer.MAX_VALUE;
   	}
   	
   	public Map<T, VertexDistance<T>> shortestPath(T start) {
   		Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.getDistance(), e2.getDistance()));
   		Map<T, VertexDistance<T>> distanceMap = new HashMap<>();
   		
   		for (T vertex : vertexMap.keySet()) { //sätta avstånd 99 till alla vertex
   			VertexDistance<T> distance = new VertexDistance<T>(vertex, Integer.MAX_VALUE);
   			distanceMap.put(vertex, distance);
   		}
   		distanceMap.get(start).setDistance(0); //utgångspunkten befinner sig 0 ifrån sigsjälv
   		
   		priorityQueue.add(distanceMap.get(start));
   		
   		while(!priorityQueue.isEmpty()) {
   			VertexDistance<T> fromVertex = priorityQueue.poll();
   			T from = fromVertex.getVertex();
   			Map<T,Integer> neighborsMap = getNeighbors(from);
   			
   			for(T to : neighborsMap.keySet()) {
   				if(!isVisited(to)) {
   					int edgeWeight = getWeight(from, to);
   					int newDistance = fromVertex.getDistance() + edgeWeight;
   					
   					VertexDistance<T> toVertex = distanceMap.get(to);
   					if(toVertex.getDistance() > newDistance) {
   						toVertex.setDistance(newDistance);
   						toVertex.setPrevious(from);
   						
   						priorityQueue.add(toVertex);   						
   					}
   				}
   			}
   			setVisited(from);
   		}
   		return distanceMap;
   	}
   	
   	private int heuristic(T from, T to) {
   		Vertex<T> fromVertex = vertexMap.get(from);
   		Vertex<T> toVertex = vertexMap.get(to);
   		int distance = (int) Math.sqrt(Math.pow(fromVertex.getCoord()[0] - toVertex.getCoord()[0], 2) + Math.pow(fromVertex.getCoord()[1] - toVertex.getCoord()[1], 2));
   		return distance;
   	}
   	
   	
   	
}


