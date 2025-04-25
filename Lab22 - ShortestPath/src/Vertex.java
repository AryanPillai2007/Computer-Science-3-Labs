import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    private final int x;
    private final int y;
    public int ID;
    public List<Integer> edges;
    public boolean visited;
    public double distance;

    public Vertex(int ID, int x, int y) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
        this.visited = false;
        this.distance = Double.POSITIVE_INFINITY;
    }

    public double euclideandistanceTo(Vertex other) {
        // square root ((x2 - x1)^2 + (y2 - y1)^2)
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public int compareTo(Vertex other) {
        // Compare vertices based on their distance from source
        return Double.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return "Vertex"
                + "{"
                + "ID= " + ID
                + ", " + "x= " + x
                + ", y= " + y
                + ", edges = " + edges
                + ", visited = " + visited
                + ", " + "distance = "
                + '}';
    }
}