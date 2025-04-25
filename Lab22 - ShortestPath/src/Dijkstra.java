import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {

    private final Graph graph;
    // To reconstruct the shortest path
    private int[] previous;

    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.previous = new int[graph.V];
    }

    public double distance(int source, int destination) {
        dijkstra(source);
        return graph.vertices[destination].distance;
    }

    private void dijkstra(int source) {
        for (Vertex v : graph.vertices) {
            v.distance = Double.POSITIVE_INFINITY;
            v.visited = false;
        }
        previous = new int[graph.V];
        for (int i = 0; i < graph.V; i++) {
            previous[i] = -1;
        }

        Vertex start = graph.vertices[source];
        start.distance = 0.0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (current.visited) continue;
            current.visited = true;

//            ADD THE OUTPUT

            System.out.print("process node " + current.ID + " (dist");
            System.out.printf(" ", current.distance);
            if (pq.isEmpty()) {
                System.out.println(" ) ...queue is now empty, we're done!");
            } else
            {
                System.out.println(" ) ...greedily choose next (closest) node");
            }

            for (int neighborID : current.edges) {
                Vertex neighbor = graph.vertices[neighborID];
                double eWeight = current.euclideandistanceTo(neighbor);
                double tDistance = current.distance + eWeight;

                if (tDistance < neighbor.distance) {
                    neighbor.distance = tDistance;
                    previous[neighborID] = current.ID;
                    pq.add(neighbor);
                    System.out.print("lower " + neighborID + " to ");
                    System.out.printf(" ", tDistance);
                }
            }
        }
    }

    public List<Integer> getShortestPath(int destination) {
        List <Integer> path = new ArrayList<>();
        for (int at = destination; at != -1; at = previous[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws Exception {
        java.util.Scanner scanner = new java.util.Scanner(new java.io.File("/Users/aryanpillai2701/Library/On Disk/Files/CS3/Computer-Science-3-Labs/Lab22 - ShortestPath/src/usa.txt"));
        Graph g = new Graph(scanner);
        Dijkstra d = new Dijkstra(g);

        int source = 0;
        int destination = 5;

        double dist = d.distance(source, destination);
        System.out.println("Shortest path distance: " + dist);

        List<Integer> path = d.getShortestPath(destination);
        System.out.print("Shortest path: ");
        for (int node:path) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
