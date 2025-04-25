import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph {
    int V, E;
    Vertex[] vertices;

    public Graph(Scanner scanner) {
        this.V = scanner.nextInt();
        this.E = scanner.nextInt();
        this.vertices = new Vertex[V];

        for (int i = 0; i < V; i++) {
            int id = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            vertices[id] = new Vertex(id, x, y);
        }

        for (int i = 0; i < E; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            vertices[from].edges.add(to);
            vertices[to].edges.add(from); // Assuming undirected graph
        }

        // Skip source-destination pair (used in Dijkstra)
        // These values can be used in main() if desired
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append(v).append(" -> ").append(v.edges).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input6.txt"));
        Graph g = new Graph(scanner);
        System.out.println(g);
    }
}