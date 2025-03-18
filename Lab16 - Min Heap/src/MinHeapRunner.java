 public class MinHeapRunner
{
	public static void main(String[] args) 
	{
		// Comment / uncomment as needed

		test00isEmpty();

		test01basicPeekMin();
		
		test02basicInsert();

		test03fewInserts();

		test04multipleInserts();

		test05doubleCapacity();

		test06basicPop();

		test07multiplePops();
	}

	public static void test00isEmpty() {
		MinHeap heap = new MinHeap();

		if (heap.isEmpty() && heap.getSize() == 0)
			System.out.println("Test00 passed");
		else
			System.out.println("Test00 failed - heap should be empty and size should be 0");
	}

	public static void test01basicPeekMin() {
		MinHeap heap = new MinHeap();

		if (heap.peekMinimum() == null)
			System.out.println("Test01 passed");
		else
			System.out.println("Test01 failed - peekMin should return null for an empty heap");
	}

	public static void test02basicInsert() {
		MinHeap heap = new MinHeap();

		heap.insert(36);

		if (heap.getSize() == 1 &&
			heap.peekMinimum() == 36)
			System.out.println("Test02 passed");
		else
			System.out.println("Test02 failed - size should be 1 and min should be 36");
	}

	public static void test03fewInserts() {
		MinHeap heap = new MinHeap();

		int[] nums = {8, 42, 35};
		
		for (int i = 0; i < nums.length; i++) 
			heap.insert(nums[i]);

		if (heap.getSize() == nums.length &&
			heap.peekMinimum() == 8)
			System.out.println("Test03 passed");
		else
			System.out.println("Test03 failed - size should be 3 and 8 should be current min");

		System.out.println("Test03 - Your heap should look like this (ignore minor spacing differences):");
		String line = """
			..............................................................
			\t\t\t\t8
			\t\t42\t\t\t\t35
			..............................................................
				"""; //note: multi-line strings require Java 15+
		System.out.println(line);
		System.out.println("Test03 - Your output:");
		heap.display();
	}

	public static void test04multipleInserts() {
		MinHeap heap = new MinHeap();

		int[] nums = {8, 42, 35, 4, -1, 99, 76, 20};
		
		for (int i = 0; i < nums.length; i++) 
			heap.insert(nums[i]);

		if (heap.getSize() == nums.length &&
			heap.peekMinimum() == -1)
			System.out.println("Test04 passed");
		else
			System.out.println("Test04 failed - size should be 3 and 8 should be current min");

		System.out.println("Test04 - Your heap should look like this (ignore minor spacing differences):");
		String line = """
			..............................................................
			\t\t\t\t-1
			\t\t4\t\t\t\t35
			\t20\t\t8\t\t99\t\t76
			42
			..............................................................
				"""; //note: multi-line strings require Java 15+
		System.out.println(line);
		System.out.println("Test04 - Your output:");
		heap.display();
	}

	public static void test05doubleCapacity() {
		MinHeap heap = new MinHeap();

		for (int i = 0; i < 100; i++)
			heap.insert((int) (Math.random() * 100)); //insert random number < 100

		if (heap.getSize() == 100 &&
			heap.peekMinimum() >= 0 && heap.peekMinimum() < 100) //within range?
			System.out.println("Test05 passed");
		else
			System.out.println("Test05 failed - Heap should contain 100 elements");
	}

	public static void test06basicPop() {
		MinHeap heap = new MinHeap();

		int[] nums = {8, 42, 35, 4, -1, 99, 76, 20};
		
		for (int i = 0; i < nums.length; i++) 
			heap.insert(nums[i]);

		int a = heap.popMinimum(); //should be -1
		int b = heap.popMinimum(); //should be 4

		if (heap.getSize() == 6 &&
			a == -1 &&
			b == 4)
			System.out.println("Test06 passed");
		else
			System.out.println("Test06 failed - Size should be 6 and mins should be -1, 4");
	}

	public static void test07multiplePops() {
		MinHeap heap = new MinHeap();

		int[] nums = {8, 42, 35, 4, -1, 99, 76, 20};
		
		for (int i = 0; i < nums.length; i++) 
			heap.insert(nums[i]);

		int a = heap.popMinimum(); //should be -1
		int b = heap.popMinimum(); //should be 4

		heap.insert(12);

		int c = heap.peekMinimum(); //should be 8

		heap.popMinimum(); //discard the 8

		int d = heap.popMinimum(); //should be 12

		if (heap.getSize() == 5 &&
			a == -1 &&
			b == 4  &&
			c == 8  &&
			d == 12)
			System.out.println("Test07 passed");
		else
			System.out.println("Test07 failed - Size should be 5 and mins should be -1, 4, 8");

		System.out.println("Test07 - Your heap should look like this:");
		String line = """
			..............................................................
			\t\t\t\t20
			\t\t42\t\t\t\t35
			\t76\t\t99              
			..............................................................
				""";
		System.out.println(line);
		System.out.println("Test07 - Your output:");
		heap.display();
	}
}
