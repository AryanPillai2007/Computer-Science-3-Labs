import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    // Main attributes
    public int x;
    public int y;
    public int ID;

    //Other attributes
    public List<Integer> edges;
    public boolean visited;
    public double distance;

    public Vertex(int x, int y, int ID) {

        this.x = x;
        this.y = y;
        this.ID = ID;
        this.edges = new ArrayList<>();
        this.visited = false;
        this.distance = Double.POSITIVE_INFINITY;
    }

    public double euclideandistance(Vertex other) {
        int otherx = this.x - other.x;
        int othery = this.y - other.y;
        return Math.sqrt(otherx * otherx + othery * othery);
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return String.format("Vertex: , Distance: , Edges: ", ID, x, y, distance, edges.toString());
    }
}
