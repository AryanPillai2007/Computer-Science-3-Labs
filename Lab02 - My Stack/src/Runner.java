import java.util.Stack;

public class Runner {

    public static Stack<Integer> makeStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            stack.push(num);
        }
        return stack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = makeStack(new int[]{1, 3, 5, 0, -1});
        System.out.println("Original Stack: " + stack);

        Stack<Integer> doubledStack = StackProbs.doubleUp(stack);
        System.out.println("Doubled Stack: " + doubledStack);
    }
}


