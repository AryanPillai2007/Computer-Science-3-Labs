import java.util.List;

public class BacktrackingProbs {

    public void printBinary(int digits) {
        printBinaryHelper(digits, "");
    }

    private void printBinaryHelper(int digits, String soFar) {
        if (digits == 0) {
            System.out.print(soFar + " ");
            return;
        }
        printBinaryHelper(digits - 1, soFar + "0");
        printBinaryHelper(digits - 1, soFar + "1");
    }

    public void climbStairs(int steps) {
        climbStairsHelper(steps, "");
    }

    private void climbStairsHelper(int steps, String soFar) {
        if (steps == 0) {
            System.out.println(soFar.trim());
            return;
        }
        if (steps >= 1) {
            climbStairsHelper(steps - 1, soFar + "1, ");
        }
        if (steps >= 2) {
            climbStairsHelper(steps - 2, soFar + "2, ");
        }
    }

    public void campsite(int x, int y) {
        campsiteHelper(x, y, "");
    }

    private void campsiteHelper(int x, int y, String path) {
        if (x == 0 && y == 0) {
            System.out.println(path.trim());
            return;
        }
        if (x > 0) {
            campsiteHelper(x - 1, y, path + "E ");
        }
        if (y > 0) {
            campsiteHelper(x, y - 1, path + "N ");
        }
        if (x > 0 && y > 0) {
            campsiteHelper(x - 1, y - 1, path + "NE ");
        }
    }

    public int getMax(List<Integer> nums, int limit) {
        return getMaxHelper(nums, limit, 0, 0);
    }

    private int getMaxHelper(List<Integer> nums, int limit, int index, int currentSum) {
        if (index == nums.size() || currentSum > limit) {
            return currentSum > limit ? 0 : currentSum;
        }
        int include = getMaxHelper(nums, limit, index + 1, currentSum + nums.get(index));
        int exclude = getMaxHelper(nums, limit, index + 1, currentSum);
        return Math.max(include, exclude);
    }

    public boolean canBalance(int target, List<Integer> nums) {
        return canBalanceHelper(nums, target, 0, 0);
    }

    private boolean canBalanceHelper(List<Integer> nums, int target, int index, int currentSum) {
        if (currentSum == target) {
            return true;
        }
        if (index == nums.size() || currentSum > target) {
            return false;
        }
        return canBalanceHelper(nums, target, index + 1, currentSum + nums.get(index)) ||
                canBalanceHelper(nums, target, index + 1, currentSum);
    }

    public String longestCommonSub(String a, String b) {
        return longestCommonSubHelper(a, b, a.length(), b.length());
    }

    private String longestCommonSubHelper(String a, String b, int m, int n) {
        if (m == 0 || n == 0) {
            return "";
        }
        if (a.charAt(m - 1) == b.charAt(n - 1)) {
            return longestCommonSubHelper(a, b, m - 1, n - 1) + a.charAt(m - 1);
        }
        String option1 = longestCommonSubHelper(a, b, m - 1, n);
        String option2 = longestCommonSubHelper(a, b, m, n - 1);
        return option1.length() > option2.length() ? option1 : option2;
    }

    public static void main(String[] args) {
        BacktrackingProbs helper = new BacktrackingProbs();

        System.out.println("Climb Stairs:");
        helper.climbStairs(4);

        System.out.println("\nCampsite:");
        helper.campsite(2, 1);

        System.out.println("\nGet Max:");
        System.out.println(helper.getMax(List.of(7, 30, 8, 22, 6, 1, 14), 19));

        System.out.println("\nCan Balance:");
        System.out.println(helper.canBalance(9, List.of(1, 2, 6))); // true
        System.out.println(helper.canBalance(12, List.of(1, 2, 6))); // false
        System.out.println(helper.canBalance(11, List.of(10, 34, 7, 5, 9, 0, 104))); // true

        System.out.println("\nLongest Common Subsequence:");
        System.out.println(helper.longestCommonSub("ABCDEFG", "BGCEHAF")); // BCEF
        System.out.println(helper.longestCommonSub("12345", "543212154321")); // 123
    }
}
