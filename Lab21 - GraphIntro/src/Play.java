// Play.java
import java.io.*;
import java.util.*;

public class Play {
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("/Users/aryanpillai2701/Library/On Disk/Files/CS3/Computer-Science-3-Labs/Lab21 - GraphIntro/src/play.dat"));
        int testCases = sc.nextInt();

        while (testCases-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int l = sc.nextInt();

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
            visited = new boolean[n + 1];

            for (int i = 0; i < m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                graph.get(x).add(y); // Directed graph
            }

            for (int i = 0; i < l; i++) {
                int start = sc.nextInt();
                dfs(start);
            }

            int count = 0;
            for (boolean b : visited) if (b) count++;
            System.out.println(count);
        }

        sc.close();
    }

    static void dfs(int current) {
        if (visited[current]) return;
        visited[current] = true;
        for (int next : graph.get(current)) dfs(next);
    }
}
