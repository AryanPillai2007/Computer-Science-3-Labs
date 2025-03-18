import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameTree {
	private Node root;
	private Node currentNode;

	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName this is the name of the file we need to import the game questions
	 * and answers from.
	 */
	public GameTree(String fileName) {
		try {
			Scanner fileScanner = new Scanner(new File(fileName));
			root = buildTree(fileScanner);
			currentNode = root;
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		}
	}

	private Node buildTree(Scanner scanner) {
		if (!scanner.hasNextLine()) return null;
		String currentLine = scanner.nextLine().trim();
		if (currentLine.endsWith("?")) {
			String question = currentLine;
			Node questionNode = new Node(question);
			questionNode.left = buildTree(scanner);
			questionNode.right = buildTree(scanner);
			return questionNode;
		} else {
			return new Node(currentLine);
		}
	}

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ The question to add where the old answer was.
	 * @param newA The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA) {
		Node newQuestionNode = new Node(newQ);
		newQuestionNode.left = new Node(newA);
		newQuestionNode.right = currentNode;
		currentNode = newQuestionNode;
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer() {
		return currentNode != null && currentNode.left == null && currentNode.right == null;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer. Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent() {
		return currentNode != null ? currentNode.data : "";
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo) {
		if (yesOrNo == Choice.Yes) {
			currentNode = currentNode.left;
		} else {
			currentNode = currentNode.right;
		}
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart() {
		currentNode = root;
	}

	@Override
	public String toString() {
		return toStringHelper(root, 0);
	}

	private String toStringHelper(Node node, int level) {
		if (node == null) return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) sb.append("-");
		sb.append(node.data).append("\n");
		if (node.left != null || node.right != null) {
			sb.append(toStringHelper(node.left, level + 1));
			sb.append(toStringHelper(node.right, level + 1));
		}
		return sb.toString();
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 */
	public void saveGame() {
		try (PrintWriter writer = new PrintWriter(new File("game_state.txt"))) {
			saveGameHelper(writer, root);
		} catch (FileNotFoundException e) {
			System.out.println("Error saving the game.");
		}
	}

	private void saveGameHelper(PrintWriter writer, Node node) {
		if (node == null) return;
		writer.println(node.data);
		if (node.left != null || node.right != null) {
			saveGameHelper(writer, node.left);
			saveGameHelper(writer, node.right);
		}
	}

	private static class Node {
		String data;
		Node left;
		Node right;

		Node(String data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
}
