import java.util.*;

public class BoggleSolver {
	private final Set<String> dictionary;

	// Initializes the data structure using the given dictionary file name.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName) {
		dictionary = new HashSet<>();
		try (Scanner scanner1 = new Scanner(new java.io.File(dictionaryName))) {
			while (scanner1.hasNext()) {
				dictionary.add(scanner1.next());
			}
		} catch (java.io.FileNotFoundException e) {
			throw new IllegalArgumentException("File not found:  " + dictionaryName);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		Set<String> validWords = new HashSet<>();
		boolean[][] visited = new boolean[board.rows()][board.cols()];

		for (int row = 0; row < board.rows(); row++) {
			for (int col = 0; col < board.cols(); col++) {
				search(board, row, col, "", visited, validWords);
			}
		}
		return validWords;
	}

	private void search(BoggleBoard board, int row, int col, String prefix, boolean[][] visited, Set<String> validWords) {
		if (row < 0 || row >= board.rows() || col < 0 || col >= board.cols() || visited[row][col]) {
			return;
		}

		char letter = board.getLetter(row, col);
		String word = prefix + (letter == 'Q'?"QU":letter);

		if (!hasPrefix(word)) return;

		if (word.length() >= 3 && dictionary.contains(word)) {
			validWords.add(word);
		}

		visited[row][col] = true;
		for (int x = -1; x <= 1; x++) {
			for (int dc = -1; dc <= 1; dc++) {
				if (x != 0 || dc != 0) {
					search(board, row + x, col + dc, word, visited, validWords);
				}
			}
		}
		visited[row][col] = false;
	}

	private boolean hasPrefix(String prefix) {
		for (String word : dictionary) {
			if (word.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word) {
		if (!dictionary.contains(word)) return 0;
		int length = word.length();
		if (length <= 2) return 0;
		if (length <= 4) return 1;
		if (length == 5) return 2;
		if (length == 6) return 3;
		if (length == 7) return 5;
		return 11;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH = "./data/";
		BoggleBoard board = new BoggleBoard(PATH + "board-estrangers.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");
		int totalPoints = 0;
		for (String s:solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); // should print 84

//		 Uncomment below for an interactive game.
		 new BoggleGame(4, 4);
	}
}