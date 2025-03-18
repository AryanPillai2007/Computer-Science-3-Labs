import java.util.Stack;

public class StackProbs {

//1
        public static Stack<Integer> makeStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            stack.push(num);
        }
        return stack;
    }

//2
        public static Stack<Integer> doubleUp(Stack<Integer> nums) {
        Stack<Integer> tempStack = new Stack<>();
        while (!nums.isEmpty()) {
            int val = nums.pop();
            tempStack.push(val);
            tempStack.push(val);
        }
        Stack<Integer> resultStack = new Stack<>();
        while (!tempStack.isEmpty()) {
            resultStack.push(tempStack.pop());
        }
        return resultStack;
    }
//3
        public static String reverseVowels(String str) {
        Stack<Character> vowels = new Stack<>();
        String vowelsSet = "aeiouyAEIOUY";
        for (char c : str.toCharArray()) {
            if (vowelsSet.indexOf(c) != -1) {
                vowels.push(c);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (vowelsSet.indexOf(c) != -1) {
                result.append(vowels.pop());
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
//4
        public static Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
        Stack<Integer> tempStack = new Stack<>();
        Stack<Integer> buffer = new Stack<>();

        while (!nums.isEmpty()) {
            buffer.push(nums.pop());
        }
        while (!buffer.isEmpty()) {
            tempStack.push(buffer.pop());
        }
        for (int i = 0; i < n; i++) {
            buffer.push(tempStack.pop());
        }
        while (!tempStack.isEmpty()) {
            nums.push(tempStack.pop());
        }
        while (!buffer.isEmpty()) {
            nums.push(buffer.pop());
        }

        return nums;
    }

//    ----------------------------------------------------------------------------------------------------------------------------------
//RIDDLE - 2 Legs on the human body (HB)
//    ----------------------------------------------------------------------------------------------------------------------------------

//6
        public static boolean bracketBalance(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[' || c=='(') {
                stack.push(c);
            } else if (c == ']' || c == ')') {
                if (stack.isEmpty()) {
                    return false; }
                char top = stack.pop();
                if ((c == ']' && top != '[') || (c == ')' && top != '(')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

// reverse polish notations goes here

        public static void main(String[] args) {
        Stack<Integer> stack = makeStack(new int[]{1, 2, 3, 4});
        System.out.println("makeStack:" + stack);

        Stack<Integer> doubledStack = doubleUp(makeStack(new int[]{1, 3, 5, 0, -1}));
        System.out.println("doubleUp:" + doubledStack);

        String reversedVowels = reverseVowels("hello how are you");
        System.out.println("reverseVowels:" + reversedVowels);

        Stack<Integer> shiftedStack = shiftByN(makeStack(new int[]{7, 23, -7, 0, 22, -8, 4, 51, 3}), 3);
        System.out.println("shiftByN:" + shiftedStack);

        boolean balanced = bracketBalance("((([0])))");
        System.out.println("bracketBalance:" + balanced);

        //RPN goes here
    }
}
