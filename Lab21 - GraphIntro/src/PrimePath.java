// PrimePath.java
import java.util.*;

public class PrimePath {
    static Set<Integer> primes = new HashSet<>();

    public static void main(String[] args) {
        generatePrimes(9999);
//        Scanner sc = new Scanner(System.in);
//        int start = sc.nextInt();
//        int end = sc.nextInt();
//        sc.close();

        int start = 1033;  // preload start
        int end = 8179;    // preload end

        System.out.println(bfs(start, end));
    }

    static void generatePrimes(int max) {
        Stack<Integer> primeStack = sieveoferatosthenes(max);
        while (!primeStack.isEmpty()) {
            int prime = primeStack.pop();
            if (prime >= 1000) { // Only consider 4-digit primes
                primes.add(prime);
            }
        }
    }

    public static Stack<Integer> sieveoferatosthenes(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> primes = new Stack<>();

        for (int i = 2; i <= n; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            int prime = queue.poll();
            primes.push(prime);

            Queue<Integer> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int candidate = queue.poll();
                if (candidate % prime != 0) {
                    tempQueue.add(candidate);
                }
            }
            queue = tempQueue;
        }

        return primes;
    }


    static int bfs(int start, int end) {
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.add(new int[]{start, 0});
        visited.add(start);

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int num = curr[0], steps = curr[1];

            if (num == end) return steps;

            for (int i = 0; i < 4; i++) {
                char[] digits = String.valueOf(num).toCharArray();
                for (char d = '0'; d <= '9'; d++) {
                    if (i == 0 && d == '0') continue;
                    if (digits[i] == d) continue;

                    char old = digits[i];
                    digits[i] = d;
                    int next = Integer.parseInt(new String(digits));

                    if (primes.contains(next) && !visited.contains(next)) {
                        visited.add(next);
                        q.add(new int[]{next, steps + 1});
                    }

                    digits[i] = old;
                }
            }
        } return -1;
    }
}