// PrimePath.java
import java.util.*;

public class PrimePath {
    static Set<Integer> primes = new HashSet<>();

    public static void main(String[] args) {
        generatePrimes();

        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();

        //Or you can pre-load the values & comment the scanner
        //int start = 1033;  // preload start
        //int end = 8179;    // preload end

        sc.close();
        System.out.println(bfs(start, end));
    }

    static void generatePrimes() {
        for (int i = 1000; i < 10000; i++) {
            if (isPrime(i)) primes.add(i);
        }
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i*i <= n; i++)
            if (n % i == 0) return false;
        return true;
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
                    if (i == 0 && d == '0') continue; // 4-digit only
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
        }

        return -1;
    }
}
