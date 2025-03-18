import java.io.*;
        import java.util.*;

public class HuffmanTree {
   
    private Node root;
    private Map<Integer, String> huffmanCodes;
    public static final int EOF = 256;

    // Build Huffman tree
    public HuffmanTree(int[] counts) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                pq.add(new Node(i, counts[i]));
            }
        }
        
        // Add the EOF, to stop decoding at 256.
        pq.add(new Node(EOF, 1));
        // Added that character

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(-1, left.frequency + right.frequency, left, right));
        }

        root = pq.poll();
        huffmanCodes = new HashMap<>();
        buildHuffmanCodes(root, "");
    }

    // Recursive create Huffman codes
    private void buildHuffmanCodes(Node node, String code) {
        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code);
        } else {
            buildHuffmanCodes(node.left, code + "0");
            buildHuffmanCodes(node.right, code + "1");
        }
    }

    // Write the character codes to the .code file
    public void write(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName)))
        {
            for (Map.Entry<Integer, String> entry : huffmanCodes.entrySet())
            {
                writer.println(entry.getKey());
                writer.println(entry.getValue());
            }
        }
    }

    // Encode into BIS
    public void encode(BitOutputStream out, String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter("");  
        // Read character by character

        while (scanner.hasNext()) {
            char ch = scanner.next().charAt(0);
            writeCode(out, huffmanCodes.get((int) ch));
        }

        // Write EOF
        writeCode(out, huffmanCodes.get(EOF));
        scanner.close();
    }

    private void writeCode(BitOutputStream out, String code) throws IOException {
        for (char bit : code.toCharArray()) {
            out.writeBit(bit == '1'? 1 : 0);
        }
    }

    // Write into the .code file
    public HuffmanTree(String codeFile) throws IOException {
        root = new Node(-1, -1);
        try (Scanner scanner = new Scanner(new File(codeFile))) {
            while (scanner.hasNextLine()) {
                int character = Integer.parseInt(scanner.nextLine());
                String code = scanner.nextLine();
                addCode(character, code);
            }
        }
    }

    // Insert character and code into tree
    private void addCode(int character, String code) {
        Node current = root;
        for (char bit : code.toCharArray()) {
            if (bit == '0') {
                if (current.left == null) {
                    current.left = new Node(-1, -1);
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(-1, -1);
                }
                current = current.right;
            }
        }
        current.character = character;
    }

    // Decode from BIS to text file
    public void decode(BitInputStream in, String outFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outFile))) {
            while (true) {
                int character =decodeNextCharacter(in);
                if (character ==EOF) {
                    break;
                }
                writer.print((char) character);
            }
        }
    }

    private int decodeNextCharacter(BitInputStream in) throws IOException {
        Node current = root;
        while (!current.isLeaf()) {
            int bit = in.readBit();
            if (bit==-1) throw new IOException("Unexpected end of stream");
            current = (bit==0) ? current.left : current.right;
        }
        return current.character;
    }
}
