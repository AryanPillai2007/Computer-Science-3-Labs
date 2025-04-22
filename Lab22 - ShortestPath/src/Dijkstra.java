import java.util.*;

public class Dijkstra {
    private Graph graph;
    private int[] previous;
    // To reconstruct the shortest path

    // Constructor stores
    public Dijkstra(Graph graph) {
        this.graph = graph;
    }
    // Public method to short path distance
    public double distance(int source, int destination) {
        dijkstra(source, destination);
        return graph.vertices[destination].distance;
    }

    // Reconstructs the short path from source to dest.
    public List<Integer> getPath(int source, int destination) {
        dijkstra(source, destination);
        List<Integer> path = new ArrayList<>();
        for (int at = destination; at != -1; at = previous[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // Dijkstra’s algorithm
    private void dijkstra(int source, int destination) {
        PriorityQueue<Vertex> Dijkstra = new PriorityQueue<>();
        previous = new int[graph.vertices.length];

        for (Vertex v : graph.vertices) {
            v.distance = Double.POSITIVE_INFINITY;
            // To represent infinity handled by vertex class
            v.visited = false;
        }

        Vertex start = graph.vertices[source];
        start.distance = 0;
        Dijkstra.add(start);

        while (!Dijkstra.isEmpty()) {
            Vertex current = Dijkstra.poll();

            if (current.visited) continue;
            current.visited = true;

            if (current.ID == destination) break;

            for (int neighborId : current.edges) {
                Vertex neighbor = graph.vertices[neighborId];

                double newDist = current.distance + graph.distance(current.ID, neighborId);
                if (newDist < neighbor.distance) {
                    neighbor.distance = newDist;
                    previous[neighborId] = current.ID;
                    Dijkstra.add(neighbor);
                }
            }
        }
    }
}
