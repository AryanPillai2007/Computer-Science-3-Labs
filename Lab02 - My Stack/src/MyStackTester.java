import java.util.EmptyStackException;


public class MyStackTester
{
	static MyStack m = new MyStack(); //the object the tests will use

	public static void main(String[] args)
	{
		test00emtpy();

		test01popEmptyStack();

		test02pushAndPeek();

		test03pop();

		test04popAll();
	}

	/** test that your stack starts out empty */
	public static void test00emtpy() {
		if (m.isEmpty())
			System.out.println("Pass: Your isEmpty/constructor methods seem to work");
		else
			System.out.println("Fail: There's a problem with your isEmpty/constructor method");
	}

	/** attempt to pop from an empty stack, should throw and exception */
	public static void test01popEmptyStack() {
		try {
			m.pop();
		}
		catch (EmptyStackException  e) {
			System.out.println("Pass: Attempting to pop an empty stack works!");
			return;
		}
		System.out.println("Fail: Attempt to pop an empty stack failed");
	}

	/** test the push and peek methods */
	public static void test02pushAndPeek() {
		m.push(2); m.push(3); m.push(7);

		if (!m.isEmpty() && m.peek() == 7)
			System.out.println("Pass: Your push/peek methods seem to work!");
		else
			System.out.println("Fail: There's a problem with your push/pop/peek methods");
	}

	/** run some basic tests on the pop method */
	public static void test03pop() {
		m.push(42);

		int a = m.pop(); //42

		m.push(2);
		m.push(3);

		int b = m.pop(); //3

		m.push(4);
		m.push(5);
		m.pop();
		m.push(6);
		m.pop();

		int c = m.pop(); //4

		if (a == 42 && b == 3 && c == 4)
			System.out.println("Pass: Your pop method seems to be working");
		else
			System.out.println("Fail: There's a problem with your pop (or push) method");
	}

	/** pop everything, push some elements, pop them all back out */
	public static void test04popAll() {
		while (!m.isEmpty()) //remove everything from the stack
			m.pop();

		m.push(1);
		m.push(2);
		m.push(3);
		m.push(4);

		String output = ""; //capture the elements in the stack

		while (!m.isEmpty())
			output += m.pop();

		if (m.isEmpty() && output.equals("4321"))
			System.out.println("Pass: Pushed and popped all successfully");
		else
			System.out.println("Fail: Problem pushing and popping all");
	}
}
