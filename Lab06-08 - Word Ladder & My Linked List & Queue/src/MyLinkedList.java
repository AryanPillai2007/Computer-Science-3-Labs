import java.util.*;

public class MyLinkedList
{
	public static final int ID = 226152 ; //add your student ID here
		
	private ListNode front;

	public class ListNode //******** INNER CLASS ********
	{
		int      data;
		ListNode next;

		public ListNode(int data) { this.data = data; }

		public ListNode(int data, ListNode next) { this.data = data; this.next = next; }

		@Override
		public String toString() { return "" + this.data; }
	}

	public MyLinkedList() //not actually necessary but included for clarity
	{
		front = null;
	}

	public MyLinkedList(int val)
	{
		front = new ListNode(val);
	}

	/** for ease of testing, you can construct a MLL object with initial values:
	 *     MyLinkedList list = new MyLinkedList(1, 2, 3, 4);
	 */
	public MyLinkedList(int... vals)
	{
		if (front == null) front = new ListNode(vals[0]);

		ListNode current = front;

		for (int i = 1; i < vals.length; i++) {
			current.next = new ListNode(vals[i]);
			current = current.next;
		}
	}

	@Override
	public String toString()
	{
		String result = "[";

		ListNode current = front;

		while (current != null)
		{
			if (current.next == null) //reached last element in the list
				result += current.data;

			else
				result += current.data + ", ";

			current = current.next;
		}
		result += "]";

		return result;
	}

	public ListNode getFront() { return this.front; }

	/********************************************
	 ********** QUIZ METHODS BELOW HERE *********
	 ********************************************/

	public void replaceLast(int n)
	{
		ListNode lis = front;
	    while (lis.next != null) {
	    	lis = lis.next;
	    }
	    lis.data =  n;
	    if (front == null) {
	        return; 
	    }
	} 

	public int countDuplicates() {
	    int counter = 0;
	    ListNode lis3 = front;
	    while (lis3 != null && lis3.next!= null) {
	    	if (lis3.data == lis3.next.data) {
	    		while (lis3.next != null &&lis3.data == lis3.next.data) {
	    			counter++;
	                lis3 = lis3.next;
	                }
	    	} 
	    	lis3 = lis3.next;
	    } 
	    return counter;
	}
	
	public int lastIndexOf(int val)
	{
		int dex3 = 0;         
	    int lIndex3 = -1;     
	   
	    ListNode lis4 = front;
	    while (lis4 != null) {
	        if (lis4.data == val) {
	        	lIndex3 = dex3;
	        }
	        lis4 = lis4.next;
	        dex3++;
	    }

	    return lIndex3;
	}

	public void stutter() {
		ListNode lis5 = front; 
		while (lis5 != null) {
			ListNode stutterlis5 = new ListNode(lis5.data);
			stutterlis5.next = lis5.next;
			lis5.next = stutterlis5;
			
			lis5 = stutterlis5.next;

		}
	}

	public void removeAll(int n) {
		while (front != null &&front.data ==n) {
			front = front.next; 
		}
		ListNode lis6 = front;
		while (lis6 != null && lis6.next !=   null) 
		{
			if (lis6.next.data ==n) {
				lis6.next = lis6.next.next;
		} else {
			lis6=lis6.next;
			}
		}
	}

	public int deleteLast()
	{
		if (front.next == null) {
	        int lastData = front.data;
	        front= null;
	        return lastData;
	    }

		ListNode lis7 = front;
	    while (lis7.next.next  !=   null) {
	    	lis7 =lis7.next;
	    }
			
	    int data7 = lis7.next.data;
	    lis7.next = null;
	    return data7;
	}
	

	public static void main(String[] args)
	{
		System.out.println("WORKING"); //bring up console for quickly terminating infinite loops
		
		/*
		 * Uncomment the tests below as needed
		 * 
		 * NOTE that passing the one supplied test does not mean your solution works 100%
		 *   your solution will be tested with more inputs
		 *   feel free to add more tests as time permits!
		 */
		MyLinkedList list = new MyLinkedList(1, 2, 3, 4, 5, 6);
		list.replaceLast(42);
		System.out.println(list);
		
		list = new MyLinkedList(1, 1, 1, 3, 3, 6, 9, 15, 15, 23, 23, 23, 40, 40);
		System.out.println(list.countDuplicates());
		
		list = new MyLinkedList(1, 18, 2, 7, 18, 39, 18, 40);
		System.out.println(list.lastIndexOf(18));
		
		list = new MyLinkedList(1, 8, 19, 4, 17);
		list.stutter();
		System.out.println(list);
		
		list = new MyLinkedList(3, 9, 4, 2, 3, 8, 17, 4, 3, 18);
		list.removeAll(3);
		System.out.println(list);
		
		list = new MyLinkedList(1, 8, 19, 4, 17);
		int result = list.deleteLast();
		System.out.println(list + ", removed: " + result);
	}
}
