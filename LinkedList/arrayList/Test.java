 package arrayList;

public class Test {

	public static void main(String[] args) {
		LinkedList<Integer> test = new LinkedList<>();
		
		test.add(1);
		test.add(2);
		test.add(4);
		test.add(5);
		System.out.println(test.toString());
		test.insert(3, 2);
		test.add(10);
		test.removeLast();
		System.out.println();
		
		System.out.println(test.toString());
		System.out.println(test.toRevertString());
	}

}
