public class MyBST_Runner 
{
	public static void main(String[] args) 
	{
		// Uncomment the test calls below as needed

		 test00emptyTree();

		 test01basicInsert();

		 test02contains();

		 test03getMax();

		 test04getMin();

		 test05inOrder();

		 test06print();

		 test07deleteLeaf();

		 test08deleteWithOneChild();

		 test09deleteWithTwoChildren();

		 test10deleteNonexistantNode();
	}

	public static void test00emptyTree() {
		MyBST tree = new MyBST();

		if (tree.size() == 0)
			System.out.println("Test00 passed");
		else
			System.out.println("Test00 failed - size of an empty tree should be 0");
	}

	public static void test01basicInsert() {
		MyBST tree = new MyBST();
		tree.insert(42);
		tree.insert(9);
		tree.insert(60);

		if (tree.size() == 3)
			System.out.println("Test01 passed");
		else
			System.out.println("Test01 failed - size of tree should be 3 after three inserts");
	}

	public static void test02contains() {
		MyBST a = new MyBST();

		a.insert(3); a.insert(8); a.insert(1);  a.insert(4);  a.insert(0);  
		a.insert(2); a.insert(9); a.insert(11); a.insert(5);
		
		// System.out.println("Size of tree: " + a.size()); //9
		if (a.size() == 9 &&
			a.contains(3) &&
		    a.contains(8) &&
			a.contains(5) &&
			!a.contains(42)) 
				System.out.println("Test02 passed");
		else
			System.out.println("Test02 failed - tree should contain 9 inserted elements");
	}

	public static void test03getMax() {
		MyBST tree = new MyBST();

		tree.insert(5);
		tree.insert(10);
		tree.insert(2);

		if (tree.getMax() == 10) 
			System.out.println("Test03 passed");
		else
			System.out.println("Test03 failed - max should be 10");
	}

	public static void test04getMin() {
		MyBST tree = new MyBST();

		tree.insert(5);
		tree.insert(10);
		tree.insert(2);

		if (tree.getMin() == 2) 
			System.out.println("Test04 passed");
		else
			System.out.println("Test04 failed - min should be 2");
	}

	public static void test05inOrder() {
		MyBST tree = new MyBST();

		int[] vals = {5, 2, 6, 1, 3, 9}; //example from lab

		for (int val : vals)
			tree.insert(val);

		System.out.println("Test05 - Should be  : 1 2 3 5 6 9");
		System.out.print(  "Test05 - Your output: ");
		tree.inOrder(); System.out.println();
	}

	public static void test06print() {
		MyBST tree = new MyBST();

		int[] vals = {5, 2, 6, 1, 3, 9}; //example from lab

		for (int val : vals)
			tree.insert(val);

		System.out.println("Test06 - Your tree should look like this: \n        9\n    6\n5\n        3\n    2\n        1\n");
		System.out.println("Test06 - Your output:");
		tree.print();
	}

	public static void test07deleteLeaf() {
		MyBST a = new MyBST();

		int[] vals = {5, 2, 6, 1, 3, 9}; //example from lab

		for (int val : vals)
			a.insert(val);

		boolean hadThree = a.contains(3);

		a.delete(3); //leaf node (no children)

		if (hadThree &&
		    !a.contains(3) &&
			a.size() == 5)
			System.out.println("Test07 passed");
		else
			System.out.println("Test07 failed - 3 should have been in tree, then removed from tree");
	}

	public static void test08deleteWithOneChild() {
		MyBST a = new MyBST();

		int[] vals = {5, 2, 6, 1, 3, 9}; //example from lab

		for (int val : vals)
			a.insert(val);

		boolean hadSix = a.contains(6);

		a.delete(6); //has one child (9)
		
		if (hadSix &&
		    !a.contains(6) &&
			a.size() == 5 &&
			a.contains(9))
			System.out.println("Test08 passed");
		else
			System.out.println("Test08 failed - 6 should have been in tree then removed, its child (9) should still be in the tree");
	}

	public static void test09deleteWithTwoChildren() {
		MyBST a = new MyBST();

		int[] vals = {5, 2, 6, 1, 3, 9}; //example from lab

		for (int val : vals)
			a.insert(val);

		boolean hadTwo = a.contains(2);

		a.delete(2); //has two children, 1 and 3

		if (hadTwo &&
			!a.contains(2) &&
			a.size() == 5 &&
			a.contains(1) && //left child
			a.contains(3))   //right child
			System.out.println("Test09 passed (nice!)");
		else
			System.out.println("Test09 failed - 2 should be removed from tree but its children 1 and 3 should still exist");
			System.out.println("Test09 - Your tree now looks like this:");
			a.print();
	}

	public static void test10deleteNonexistantNode() {
		MyBST a = new MyBST();

		a.insert(3); a.insert(8); a.insert(1);  a.insert(4);  a.insert(0);  
		a.insert(2); a.insert(9); a.insert(11); a.insert(5);

		a.delete(10); //not a value in the tree

		if (a.size() == 9 &&
			a.contains(5) &&
			a.contains(3) && 
			a.contains(9))
			System.out.println("Test10 passed");
		else
			System.out.println("Test10 failed - deleting a node that doesn't exist shouldn't change the tree");
	}
}
