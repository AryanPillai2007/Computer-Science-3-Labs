import java.util.Stack;
public class RecursionProbs {
    public double sumReciprocals(int n) {
        if (n == 1) {
            return 1.0;
        } else {
            return 1.0 / n + sumReciprocals(n - 1);
        }
    }

    public int productOfEvens(int n) {
        if (n == 1) {
            return 2;
        } else {
            return 2 * n * productOfEvens(n - 1);
        }
    }

    public String conversion(int num, int base) {
        if (num == 0) {
            return "";
        }
        int remainder = num % base;
        return conversion(num / base, base) + remainder;
    }

    public int matchingDigits(int a, int b) {
        if (a == 0 && b == 0) {
            return 0;
        }
        int count = (a % 10 == b % 10) ? 1 : 0;
        return count + matchingDigits(a / 10, b / 10);
    }

    public void doubleUp(Stack<Integer> nums) {
        if (nums.isEmpty()) {
            return;
        }
        int num = nums.pop();
        doubleUp(nums);
        nums.push(num);
        nums.push(num);
    }

    public void printThis(int n) {
        printThisHelper(1, n);
    }

    private void printThisHelper(int i, int n) {
        if (i > n) {
            return;
        }
        StringBuilder line = new StringBuilder();
        appendChars(line, "<", i - 1);
        line.append(n % 2 == 1 ? "*" : "****");
        appendChars(line, ">", i - 1);
        System.out.println(line);
        printThisHelper(i + 1, n);
    }

    private void appendChars(StringBuilder sb, String ch, int count) {
        if (count == 0) {
            return;
        }
        sb.append(ch);
        appendChars(sb, ch, count - 1);
    }

    public void printNums2(int n) {
        printNums2Helper(1, n);
    }

    private void printNums2Helper(int i, int n) {
        if (i > n) {
            return;
        }
        StringBuilder line = new StringBuilder();
        if (i % 2 == 1) {
            appendNumsAscending(line, 1, i);
        } else {
            appendNumsDescending(line, i);
            appendNumsAscending(line, 2, i);
        }
        System.out.println(line.toString().trim());
        printNums2Helper(i + 1, n);
    }

    private void appendNumsAscending(StringBuilder sb, int start, int end) {
        if (start > end) {
            return;
        }
        sb.append(start).append(" ");
        appendNumsAscending(sb, start + 1, end);
    }

    private void appendNumsDescending(StringBuilder sb, int start) {
        if (start < 1) {
            return;
        }
        sb.append(start).append(" ");
        appendNumsDescending(sb, start - 1);
    }

public static void main(String[] args) {
    RecursionProbs rp = new RecursionProbs();

    int q1 = 5;
    System.out.println("Sum of reciprocals for " + q1 + ": " + rp.sumReciprocals(q1));

    int q2 = 4;
    System.out.println("Product of first " + q2 + " even numbers: " + rp.productOfEvens(q2));

    int number = 13, base = 2;
    System.out.println("Conversion of " + number + " to base " + base + ": " + rp.conversion(number, base));

    int a = 12345, b = 12335;
    System.out.println("Matching digits between " + a + " and " + b + ": " + rp.matchingDigits(a, b));

    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println("stack: " + stack);
    rp.doubleUp(stack);
    System.out.println("Double stack: " + stack);

    System.out.println("output for n = 4:");
    rp.printThis(4);

    System.out.println("Nums2 output for n = 5:");
    rp.printNums2(5);
}
}
