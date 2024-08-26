package arrayList;

public class LinkedList<T> {

	private Node<T> head; //den första node i listan
	private Node<T> tail; //den sista node i listan

	private int size;

	public LinkedList() {
		head = tail = null;
		size = 0;
	}


	public void add(T element) { //lägger till node på sista platsen i listan
		Node<T> newNode = new Node<T>(element); 

		if (size == 0) { //om noden är den första som skapas
			head = newNode;
		}
		else { //om nyskapade node är inte den första	
			tail.next = newNode; //tilldela newNode som värde på pointer till föregående node
			newNode.prev = tail;
		}

		tail = newNode; //newNode är nu den sista node i listan
		size++;
	}

	public T get(int position) { //hämtar värde på en node
		Node<T> searchedNode = getNode(position);
		return (T) searchedNode.getElement();
	}

	public int getSize() { //retunerar storleken på listan
		return size;
	}

	public boolean isEmpty() { //True - om listan är tom
		if (size == 0) return true;
		else return false;
	}

	public void clear() { //Tommar listan
		head = tail = null;
		size = 0;
	}

	public void removeLast() { //Raderar den sista värde i listan
		if(size == 1) { //om det finns bara ett värde
			clear();
		}
		else { //om det finns flera värde	
			Node<T> prev = getNode(size - 2); //hämtar den näst sista node
			prev.next = null; //tilldelar värde null till dess pointer
			size --;
			tail = prev;
			if(size == 1) head = prev;
		}
	}

	public void addFirst(T element) { //lägger till ett värde på första platsen
		if(size > 0) { //om det redan finns värde i listan		
			Node<T> firstNode = new Node<T>(element);
			firstNode.next = head;
			head.prev = firstNode;
			head = firstNode;
			size++;
		}
		else add(element); //om det finns inga element
	}

	public void removeFirst() { //raderar den första värden i listan
		if(size == 0) throw new IndexOutOfBoundsException("No elements in the list"); //om det finns inget värde i listan
		else if(size == 1) { //om det finns bara en node
			clear();
		}
		else {
			head = getNode(1); //hämtar den andra noden i listan, och gör det till den första
			head.prev = null;
			size --;			
		}
	}

	public void remove(int position) { //raderar en node på en viss index
		if(position >= size || position < 0) throw new IndexOutOfBoundsException("Index: " + position + ", Size: " + size);
		else if(position == 0) removeFirst();
		else if(position == size - 1) removeLast();

		else {
			Node<T> prevNode = getNode(position - 1);
			Node<T> currentNode = prevNode.next;
			Node<T> nextNode = prevNode.next;

			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			size--;
		}
	}

	public void insert(T element, int position) { //lägger till en node på en viss index
		if(position == 0) addFirst(element);
		else if(position == size) add(element);

		else {
			Node<T> newNode = new Node<T>(element);
			Node<T> prevNode = getNode(position - 1);
			Node<T> nextNode = prevNode.next;

			newNode.next = nextNode;
			prevNode.next = newNode;
			nextNode.prev = newNode;
			newNode.prev = prevNode;
			size++;
		}
	}


	private Node<T> getNode(int index) { //hämtar en node på en viss index
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

		Node<T> searchedNode = head;
		for(int i = 0; i < index; i++) {
			searchedNode = searchedNode.next;
		}
		return searchedNode;

	}

	public String toString() {
		String string = "";

		Node<T> searchedNode = head;
		string += searchedNode.toString() +", ";
		for(int i = 0; i < size - 1; i++) {
			searchedNode = searchedNode.next;
			string += searchedNode.toString() +", ";
		}
		return string.substring(0, string.length() - 2);
	}
	
	public String toRevertString() {
		String string = "";

		Node<T> searchedNode = tail;
		string += searchedNode.toString() +", ";
		for(int i = 0; i < size - 1; i++) {
			searchedNode = searchedNode.prev;
			string += searchedNode.toString() +", ";
		}
		return string.substring(0, string.length() - 2);
	}
}
