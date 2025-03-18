public class Node implements Comparable<Node> {
    // ASCII value
    int character;
    // Frequency Value
    int frequency;
    // left child (Node object) — stored left
    Node left,
    // right child (Node object) — stored right
    right;

    public Node(int character, int frequency) {
        this(character, frequency, null, null);
    }

    public Node(int character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}
