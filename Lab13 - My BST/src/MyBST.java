public class MyBST {
    private Node root;
    private int size;

    private static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    public MyBST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            size++;
            return new Node(value);
        }
        if (value< node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        } else if (value < node.value) {
            return contains(node.left, value);
        } else {
            return contains(node.right, value);
        }
    }

    public int getMax() {
        if (root == null) throw new IllegalStateException("empty tree");
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public int getMin() {
        if (root == null) throw new IllegalStateException("empty tree");
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public void print() {
        print(root, 0);
    }

    private void print(Node node, int depth) {
        if (node != null) {
            print(node.right, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print(" ");
            }
            System.out.println(node.value);
            print(node.left, depth + 1);
        }
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            node.left =
                    delete(node.left, value);
        } else if (value > node.value) {
            node.right =
                    delete(node.right, value);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            } else {
                Node minLargerNode = findMin(node.right);
                node.value = minLargerNode.value;
                node.right = delete(node.right, minLargerNode.value);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}