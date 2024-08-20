package intro;

public class Edge<T> {
	
	private Vertex<T> from, to;
	private int weight;
	
	
	public Edge(Vertex<T> from, Vertex<T> to, int weight) { 
		this.from = from;
		this.to = to;
		this.weight= weight;
	}
	   	
	
	public int getWeight(){
		return weight;
	}

}
