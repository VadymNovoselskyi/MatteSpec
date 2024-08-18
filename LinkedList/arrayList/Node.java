package arrayList;

public class Node<T> {
	 
	public Node<T> next;
	private T element;
	
	public Node(T element) {
    	    this.element = element;
    	    next = null;
	}
	
	public T getElement() {
	    return element;
	}
}
