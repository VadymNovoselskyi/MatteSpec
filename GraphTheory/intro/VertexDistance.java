package intro;

import java.util.Map;

public class VertexDistance<T> {
	private T vertex;
	private T prevVertex;
	private int distance;
	
	public VertexDistance(T vertex, int distance) {
		this.vertex = vertex;
		this.distance = distance;
	}
	
	public T getVertex() {
		return vertex;
	}
	
	public T getPrevious() {
		return prevVertex;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public String getPath(Map<T, VertexDistance<T>> path) {
		String stringPath = vertex.toString();
		T prevVertex = this.getPrevious();
		
		while(prevVertex != null) {
			stringPath += prevVertex.toString();
			prevVertex = path.get(prevVertex).getPrevious();
		}
		return formatPath(stringPath);
	}
	
	
	public void setPrevious(T prevVertex) {
		this.prevVertex = prevVertex;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	private String formatPath(String path) {
		String formatedPath = "";

        for (int i = path.length() - 1; i >= 0; i--) {
        	formatedPath += path.charAt(i);
        	if(i != 0) formatedPath += " --> ";
        }
        return formatedPath;
	}
}
