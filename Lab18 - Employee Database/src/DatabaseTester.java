public class DatabaseTester {

	public static void main(String[] args) {
		test00add();
		test01contains();
 		test02remove();
		test03checkForStop();
	}

	public static void test00add() {
		/*
		 * make sure your default capacity is 11 before running this test!
		 */
		EmployeeDatabase set = new EmployeeDatabase();
		set.add(new Employee("Ron", 111111));
		System.out.println(set); //should show only Ron the array

		set.add(new Employee("Charlie", 222222));
		System.out.println(set); //should show Charlie and Ron in the array
		System.out.println("size: " + set.size());
		System.out.println(set.add(new Employee("Charlie", 222222))); //should print false

		for (char i = 97, j = 0; j < 10; i++, j++) { //should reject two entries
			set.add(new Employee("" + i, j));        //as the hash table is full
		}

		System.out.println(set); //should show a completely full array
		System.out.println(set.size()); //should print 11 (completely full)
	}

	public static void test01contains() {
		EmployeeDatabase set = new EmployeeDatabase();
		set.add(new Employee("Ron", 111111));
		System.out.println(set.contains(new Employee("Ron", 111111))); //should print true
		System.out.println(set.contains(new Employee("Ron", 222222))); //should print false
		System.out.println(set.contains(new Employee("R0n", 111111))); //should print false
	}

	public static void test02remove() {
		EmployeeDatabase set = new EmployeeDatabase();
		set.add(new Employee("Ron", 111111));
		System.out.println(set); //should show only Ron the array

		set.add(new Employee("Charlie", 222222));
		System.out.println(set); //should show Charlie and Ron in the array

		set.remove(new Employee("Ron", 111111));

		System.out.println(set); //should only show Charlie in the array

		System.out.println(set.remove(new Employee("Ron", 111111))); //should print false
		
		System.out.println(set.contains(new Employee("Charlie", 222222))); //should print true
	}
	
	public static void test03checkForStop() {
		/*
		 * make sure your default capacity is 11 before running this test!
		 */
		EmployeeDatabase set = new EmployeeDatabase();
		
		for (int i = 0; i <= 11; i++) { //intentionally try to over fill the array
			set.add(new Employee("" + (char) (97 + i), i));
		}
		
		System.out.println(set); //should be full
		
		System.out.println(set.add(new Employee("Marissa", 15)));      //should print false, can't add
		System.out.println(set.contains(new Employee("Marissa", 15))); //should print false
		System.out.println(set.remove(new Employee("Marissa", 15)));   //should print false
	}
}