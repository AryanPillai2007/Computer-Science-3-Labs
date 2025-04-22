import java.util.*;

public class Graph {
    // Number of vertices and edges
    private int V;
    private int E;

    // Adjacency list representation
    public Vertex[] vertices;

    public Graph(Scanner scanner) {
        this.V = scanner.nextInt();
        this.E = scanner.nextInt();
        vertices = new Vertex[V];

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
        } if (scanner.hasNextInt()) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            System.out.println("Route: " + source + " -> " + destination);
        }
    }

    public double distance(int from, int to) {
        return vertices[from].euclideandistance(vertices[to]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph with ").append(V).append(" vertices and ").append(E).append(" edges.\n");
        for (Vertex v : vertices) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }
}
