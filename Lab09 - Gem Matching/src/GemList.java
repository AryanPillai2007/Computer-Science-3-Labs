class GemList {
	private Node head;
	private int size;

	private class Node {
		private Gem gem;
		private Node next;

		private Node(Gem gem) {
			this.gem = gem;
			this.next = null;
		}
	}

	public GemList() {
		this.head = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public void insertBefore(Gem gem, int index) {
		Node newNode = new Node(gem);

		if (head == null || index <= 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node current = head;
			Node previous = null;
			int pos = 0;

			while (current != null && pos < index) {
				previous = current;
				current = current.next;
				pos++;
			}

			newNode.next = current;
			if (previous != null) {
				previous.next = newNode;
			}
		}
		size++;
	}

	public int score() {
		if (head == null)
			return 0;

		int totalScore = 0;
		Node current = head;
		GemType currentType = current.gem.getType();
		int blockScore = 0;
		int blockSize = 0;

		while (current != null) {
			if (current.gem.getType() == currentType) {
				blockScore += current.gem.getPoints();
				blockSize++;
			} else {
				totalScore += blockSize * blockScore;
				currentType = current.gem.getType();
				blockScore = current.gem.getPoints();
				blockSize = 1;
			}
			current = current.next;
		}

		totalScore += blockSize * blockScore;
		return totalScore;
	}

	public void draw(double y) {
		Node current = head;
		int index = 0;
		double x = 0.05;
		double space = 0.07;

		while (current != null) {
			current.gem.draw(x + index * space, y);
			current = current.next;
			index++;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node current = head;
		while (current != null) {
			builder.append(current.gem);
			if (current.next != null)
				builder.append(" -> ");
			current = current.next;
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);

		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);

		list.insertBefore(new Gem(GemType.BLUE, 20), 99);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);

		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);

		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);

		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);

		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);
	}
}
