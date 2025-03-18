import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class IntTreeTest 
{
	public static void main(String[] args) {
		 testSize();
		 testCountLeaves();
		 testSum();
		 testDepthSum();
		 testNumEmpty();
		 testHeight();
		 testPrintLevel();
		 testTighten();
	}
	
	public static void testSize() {
		/*
		 *  The following tree has a size of 8:

			               +---+
			               | 6 |
			           ___ +---+ ___
			         /               \
			     +---+               +---+
			     | 3 |               | 2 |
			     +---+               +---+
			    /     \             /     \
			+---+     +---+     +---+     +---+
			| 1 |     | 4 |     | 5 |     | 7 |
			+---+     +---+     +---+     +---+
			                                   \
			                                   +---+
			                                   | 0 |
			                                   +---+
		 */
		IntTree tree = new IntTree(new TreeNode(6));
		tree.overallRoot.left = new TreeNode(3);
		tree.overallRoot.left.left = new TreeNode(1);
		tree.overallRoot.left.right = new TreeNode(4);
		tree.overallRoot.right = new TreeNode(2);
		tree.overallRoot.right.left = new TreeNode(5);
		tree.overallRoot.right.right = new TreeNode(7);
		tree.overallRoot.right.right.right = new TreeNode(0);
		
		if (tree.size() == 8) {
			System.out.println("Pass: Your size method seems to be working!");
		}
		else {
			System.out.println("Fail: Your size method returned: " + tree.size());
		}
	}
	
	public static void testCountLeaves() {
		/*
		 *  the following tree has four leaves (nodes 1, 4, 8, and 9):

		               +---+
		               | 5 |
		               +---+
		              /     \
		          +---+     +---+
		          | 3 |     | 6 |
		          +---+     +---+
		         /     \         \
		     +---+     +---+     +---+
		     | 2 |     | 4 |     | 7 |
		     +---+     +---+     +---+
		    /                   /     \
		+---+               +---+     +---+
		| 1 |               | 8 |     | 9 |
		+---+               +---+     +---+
		*/
		IntTree tree = new IntTree(new TreeNode(5));
		tree.overallRoot.left = new TreeNode(3);
		tree.overallRoot.left.left = new TreeNode(2);
		tree.overallRoot.left.left.left = new TreeNode(1);
		tree.overallRoot.left.right = new TreeNode(4);
		tree.overallRoot.right = new TreeNode(6);
		tree.overallRoot.right.right = new TreeNode(7);
		tree.overallRoot.right.right.left = new TreeNode(8);
		tree.overallRoot.right.right.right = new TreeNode(9);
		
		if (tree.countLeaves() == 4) {
			System.out.println("Pass: Your countLeaves method seems to be working!");
		}
		else {
			System.out.println("Fail: Your countLeaves method returned: " + tree.countLeaves());
		}
	}
	
	public static void testSum() {
		/*
		 *  the following tree has a sum of 33:

			            +----+
			            |  3 |
			            +----+
			           /      \
			          /        \
			      +----+      +----+
			      | -9 |      | 15 |
			      +----+      +----+
			     /      \           \
			    /        \           \
			+----+      +----+      +----+
			| 12 |      | 24 |      |  0 |
			+----+      +----+      +----+
			           /      \
			          /        \
			      +----+      +----+
			      |  3 |      | -9 |
			      +----+      +----+
			     /                  \
			    /                    \
			+----+                  +----+
			|  0 |                  | -6 |
			+----+                  +----+
		*/
		IntTree tree = new IntTree(new TreeNode(3));
		tree.overallRoot.left = new TreeNode(-9);
		tree.overallRoot.left.left = new TreeNode(12);
		tree.overallRoot.left.right = new TreeNode(24);
		tree.overallRoot.left.right.left = new TreeNode(3);
		tree.overallRoot.left.right.left.left = new TreeNode(0);
		tree.overallRoot.left.right.right = new TreeNode(-9);
		tree.overallRoot.left.right.right.right = new TreeNode(-6);
		tree.overallRoot.right = new TreeNode(15);
		tree.overallRoot.right.right = new TreeNode(0);
		
		if (tree.sum() == 33) {
			System.out.println("Pass: Your sum method seems to be working!");
		}
		else {
			System.out.println("Fail: Your sum method returned: " + tree.sum());
		}
	}
	
	public static void testDepthSum() {
		/*
		 *  in the tree below:

			          +---+
			          | 9 |
			          +---+
			         /     \
			     +---+     +---+
			     | 7 |     | 6 |
			     +---+     +---+
			    /     \         \
			+---+     +---+     +---+
			| 3 |     | 2 |     | 4 |
			+---+     +---+     +---+
			         /               \
			     +---+               +---+
			     | 5 |               | 2 |
			     +---+               +---+
		     
			The sum would be computed as:
			
			1 * 9 + 2 * (7 + 6) + 3 * (3 + 2 + 4) + 4 * (5 + 2) = 90
		
		*/
		IntTree tree = new IntTree(new TreeNode(9));
		tree.overallRoot.left = new TreeNode(7);
		tree.overallRoot.left.left = new TreeNode(3);
		tree.overallRoot.left.right = new TreeNode(2);
		tree.overallRoot.left.right.left = new TreeNode(5);
		tree.overallRoot.right = new TreeNode(6);
		tree.overallRoot.right.right = new TreeNode(4);
		tree.overallRoot.right.right.right = new TreeNode(2);
		
		if (tree.depthSum() == 90) {
			System.out.println("Pass: Your depthSum method seems to be working!");
		}
		else {
			System.out.println("Fail: Your depthSum method returned: " + tree.depthSum());
		}
	}
	
	public static void testNumEmpty() {
		/*
		 *  the tree below has 15 empty branches (indicated by circles):

			                    +---+
			                    | 0 |
			                    +---+
			                   /     \
			               +---+     +---+
			               | 4 |     | 6 |
			               +---o     +---+
			              /         /     \
			          +---+     +---+     +---+
			          | 3 |     | 0 |     | 1 |
			          +---o     +---+     o---+
			         /         /     \         \
			     +---+     +---+     +---+     +---+
			     | 4 |     | 3 |     | 8 |     | 5 |
			     +---o     o---+     o---o     +---+
			    /               \             /     \
			+---+               +---+     +---+     +---+
			| 1 |               | 9 |     | 2 |     | 7 |
			o---o               o---o     o---o     o---o
		*/
		IntTree tree = new IntTree(new TreeNode(0));
		tree.overallRoot.left = new TreeNode(4);
		tree.overallRoot.left.left = new TreeNode(3);
		tree.overallRoot.left.left.left = new TreeNode(4);
		tree.overallRoot.left.left.left.left = new TreeNode(1);
		tree.overallRoot.right = new TreeNode(6);
		tree.overallRoot.right.left = new TreeNode(0);
		tree.overallRoot.right.left.left = new TreeNode(3);
		tree.overallRoot.right.left.left.right = new TreeNode(9);
		tree.overallRoot.right.left.right = new TreeNode(8);
		tree.overallRoot.right.right = new TreeNode(1);
		tree.overallRoot.right.right.right = new TreeNode(5);
		tree.overallRoot.right.right.right.left = new TreeNode(2);
		tree.overallRoot.right.right.right.right = new TreeNode(7);
		
		if (tree.numEmpty() == 15) {
			System.out.println("Pass: Your numEmpty method seems to be working!");
		}
		else {
			System.out.println("Fail: Your numEmpty method returned: " + tree.numEmpty());
		}
	}
	
	public static void testHeight() {
		/*
		 *  the following tree has a height of 4:

			               +---+
			               | 7 |
			               +---+
			              /     \
			          +---+     +---+
			          | 4 |     | 8 |
			          +---+     +---+
			         /     \
			     +---+     +---+
			     | 2 |     | 5 |
			     +---+     +---+
			    /     \         \
			+---+     +---+     +---+
			| 1 |     | 3 |     | 6 |
			+---+     +---+     +---+
		*/
		IntTree tree = new IntTree(new TreeNode(7));
		tree.overallRoot.left = new TreeNode(4);
		tree.overallRoot.left.left = new TreeNode(2);
		tree.overallRoot.left.left.left = new TreeNode(1);
		tree.overallRoot.left.left.right = new TreeNode(3);
		tree.overallRoot.left.right = new TreeNode(5);
		tree.overallRoot.left.right.right = new TreeNode(6);
		
		if (tree.height() == 4) {
			System.out.println("Pass: Your height method seems to be working!");
		}
		else {
			System.out.println("Fail: Your height method returned: " + tree.height());
		}
	}
	
	public static void testPrintLevel() {
		/*
		 *  if a variable tree stores a reference to the following tree:

				            +----+
				            | 12 |
				            +----+
				           /      \
				          /        \
				      +----+      +----+
				      | 19 |      | 93 |
				      +----+      +----+
				     /      \           \
				    /        \           \
				+----+      +----+      +----+
				| 11 |      | 14 |      | 15 | //level 3
				+----+      +----+      +----+
				           /
				          /
				      +----+
				      | 10 |
				      +----+
				      
				Then the call tree.printLevel(3); would produce the following output:
				
				11
				14
				15
		*/
		IntTree tree = new IntTree(new TreeNode(12));
		tree.overallRoot.left = new TreeNode(19);
		tree.overallRoot.left.left = new TreeNode(11);
		tree.overallRoot.left.right = new TreeNode(14);
		tree.overallRoot.left.right.left = new TreeNode(10);
		tree.overallRoot.right = new TreeNode(93);
		tree.overallRoot.right.right = new TreeNode(15);
		
		//save standard out for replacing later
	    PrintStream stdout           = System.out;
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
    
        try {
			System.setOut(new PrintStream(output, true, "UTF-8")); //redirect normal print statement for capturing output
		} 
        catch (UnsupportedEncodingException e) { }
		
        tree.printLevel(3);
        
        String result = output.toString();                   //get the captured output 
        result = result.replaceAll("[\\n\\r]+", " ").trim(); //replace any line breaks with spaces, trim up any trailing spaces
        
        System.setOut(stdout); //reset output to standard out
        
		if (result.equals("11 14 15")) {
			System.out.println("Pass: Your printLevel method seems to be working!");
		}
		else {
			System.out.println("Fail: Your printLevel method output: " + result);
		}
	}
	
	public static void testTighten() {
		if (testTighten00() && testTighten01() && testTighten02() && testTighten03()) {
			System.out.println("Pass: Your tighten method seems to be working!  Nice!!");
		}
		else {
			System.out.print("Fail: Your tighten method failed test(s): ");
			if (!testTighten00()) System.out.print("00 ");
			if (!testTighten01()) System.out.print("01 ");
			if (!testTighten02()) System.out.print("02 ");
			if (!testTighten03()) System.out.print("03 ");
			System.out.println();
		}
	}
	
	private static boolean testTighten00() {
		/*
		 * overallRoot
		            [1]
		           /
		        [2]
		       /
		    [3]
		   /
		[4]
		
		overallRoot
		     [4]
		 */
		
		IntTree tree = new IntTree(new TreeNode(1));
		tree.overallRoot.left = new TreeNode(2);
		tree.overallRoot.left.left = new TreeNode(3);
		tree.overallRoot.left.left.left = new TreeNode(4);
		
		tree.tighten();
		
		return tree.overallRoot.data  == 4 && 
		       tree.overallRoot.left  == null && 
			   tree.overallRoot.right == null;
	}
	
	private static boolean testTighten01() {
		/*
		 * overallRoot
			[1]
			   \
			    [2]
			       \
			        [3]
			           \
			            [4]
			
			overallRoot
			     [4]
		 */
		
		IntTree tree = new IntTree(new TreeNode(1));
		tree.overallRoot.right = new TreeNode(2);
		tree.overallRoot.right.right = new TreeNode(3);
		tree.overallRoot.right.right.right = new TreeNode(4);
		
		tree.tighten();
		
		return tree.overallRoot.data  == 4 && 
		       tree.overallRoot.left  == null && 
			   tree.overallRoot.right == null;
	}
	
	private static boolean testTighten02() {
		/*
		 * overallRoot
			                [1]
			               /   \
			        ____[2]     [3]
			       /
			    [4]
			   /   \
			[5]     [6]
			
			        overallRoot
			        ____[1]
			       /       \
			    [4]         [3]
			   /   \
			[5]     [6]
		 */
		
		IntTree tree = new IntTree(new TreeNode(1));
		tree.overallRoot.left = new TreeNode(2);
		tree.overallRoot.left.left = new TreeNode(4);
		tree.overallRoot.left.left.left = new TreeNode(5);
		tree.overallRoot.left.left.right = new TreeNode(6);
		tree.overallRoot.right = new TreeNode(3);
		
		tree.tighten();
		
		return tree.overallRoot.data            == 1 && 
			   tree.overallRoot.left.data       == 4 &&
			   tree.overallRoot.left.left.data  == 5 &&
			   tree.overallRoot.left.right.data == 6 &&
			   tree.overallRoot.right.data      == 3;
	}
	
	private static boolean testTighten03() {
		/*
		 * overallRoot
			    [1]
			   /   \
			[2]     [3]____
			               \
			                [4]
			               /   \
			            [5]     [6]
			
			overallRoot
			    [1]____
			   /       \
			[2]         [4]
			           /   \
			        [5]     [6]
		 */
		
		IntTree tree = new IntTree(new TreeNode(1));
		tree.overallRoot.left = new TreeNode(2);
		tree.overallRoot.right = new TreeNode(3);
		tree.overallRoot.right.right = new TreeNode(4);
		tree.overallRoot.right.right.left = new TreeNode(5);
		tree.overallRoot.right.right.right = new TreeNode(6);
		
		tree.tighten();
		
		return tree.overallRoot.data             == 1 && 
			   tree.overallRoot.left.data        == 2 &&
			   tree.overallRoot.right.data       == 4 &&
			   tree.overallRoot.right.left.data  == 5 &&
			   tree.overallRoot.right.right.data == 6;
	}
}