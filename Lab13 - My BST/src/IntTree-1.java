class TreeNode {           //node class, not an inner class for ease of testing
	int      data;         //data stored in this node
	TreeNode left, right;  //links to the sub-trees

	public TreeNode(int data) { this.data = data; }

	@Override
	public String toString() { return " " + this.data; }
}

public class IntTree
{
	TreeNode overallRoot;

	public IntTree(TreeNode overallRoot) {
		this.overallRoot = overallRoot;
	}

	public int size() {
		return size(overallRoot);
	}
	private int size(TreeNode root) {
		if (root == null) return 0;
		return 1 + size(root.left) +
				size(root.right);
	}

	public int countLeaves() {
		return countLeaves(overallRoot);
	}
	private int countLeaves(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null &&
				root.right == null) return 1;
		return countLeaves(root.left) +
				countLeaves(root.right);
	}

	public int sum() {
		return sum(overallRoot);
	}
	private int sum(TreeNode root) {
		if (root == null) return 0;
		return root.data + sum(root.left) +
				sum(root.right);
	}

	public int depthSum() {
		return depthSum(overallRoot, 1);
	}
	private int depthSum(TreeNode root, int depth) {
		if (root == null) return 0;
		return depth * root.data + depthSum(root.left, depth + 1) +
				depthSum(root.right, depth + 1);
	}

	public int numEmpty() {
		return numEmpty(overallRoot);
	}
	private int numEmpty(TreeNode root) {
		if (root == null)
			return 1;
		return numEmpty(root.left) +
				numEmpty(root.right);
	}

	public int height() {
		return height(overallRoot);
	}
	private int height(TreeNode root) {
		if (root == null) return 0;
		return 1 + Math.max(height(root.left), height(root.right));
	}

	public void printLevel(int n) {
		printLevel(overallRoot, n, 1);
	}
	private void printLevel(TreeNode root, int n, int currentLevel) {
		if (root == null) return;
		if (currentLevel == n) {
			System.out.print(root.data + "  ");
			return;
		}
		printLevel(root.left, n, currentLevel + 1);
		printLevel(root.right, n, currentLevel + 1);
	}

	public void tighten() {
		overallRoot = tighten(overallRoot);
	}
	private TreeNode tighten(TreeNode root) {
		if (root == null) return null;
		if (root.left == null &&
				root.right == null) return root;
		root.left = tighten(root.left);
		root.right = tighten(root.right);
		if (root.left == null)
			return root.right;
		if (root.right == null)
			return root.left;
		return root;
	}
}