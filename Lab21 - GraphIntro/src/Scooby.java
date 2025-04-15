// Scooby.java
import java.io.*;
import java.util.*;

public class Scooby {
    static Map<Character, List<Character>> graph = new HashMap<>();
    static Set<Character> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("/Users/aryanpillai2701/Library/On Disk/Files/CS3/Computer-Science-3-Labs/Lab21 - GraphIntro/src/scooby.dat"));
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < t; i++) {
            graph.clear();
            visited.clear();

            String[] edges = sc.nextLine().split("");
            String[] startEnd = sc.nextLine().split("");

            // Build undirected graph
            for (String edge : edges) {
                char from = edge.charAt(0);
                char to = edge.charAt(1);
                graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
                graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from); // because it's undirected
            }

            char start = startEnd[0].charAt(0);
            char end = startEnd[1].charAt(0);

            System.out.println(dfs(start, end) ? "yes" : "no");
        }
        sc.close();
    }

    static boolean dfs(char current, char target) {
        if (current == target) return true;
        visited.add(current);

        for (char neighbor : graph.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, target)) return true;
            }
        }

        return false;
    }
}
