package arrayList;

public class Node<T> {
	 
	public Node<T> next;
	public Node<T> prev;
	private T element;
	
	public Node(T element) {
    	    this.element = element;
    	    prev = null;
    	    next = null;
	}
	
	public T getElement() {
	    return element;
	}
	
	public String toString() {
		return element.toString();
	}
}
